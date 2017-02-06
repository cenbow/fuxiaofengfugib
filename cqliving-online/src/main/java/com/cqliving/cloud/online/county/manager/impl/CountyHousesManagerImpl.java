package com.cqliving.cloud.online.county.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.support.json.JSONUtils;
import com.cqliving.basic.facade.PropertiesConfig;
import com.cqliving.cloud.common.constant.PropertyKey;
import com.cqliving.cloud.online.county.dao.CountyDao;
import com.cqliving.cloud.online.county.dao.CountyHousesDao;
import com.cqliving.cloud.online.county.domain.County;
import com.cqliving.cloud.online.county.domain.CountyHouses;
import com.cqliving.cloud.online.county.dto.CountyDto;
import com.cqliving.cloud.online.county.dto.CountyHousesDto;
import com.cqliving.cloud.online.county.dto.HousesDto;
import com.cqliving.cloud.online.county.manager.CountyHousesManager;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.http.HttpClientUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("countyHousesManager")
public class CountyHousesManagerImpl extends EntityServiceImpl<CountyHouses, CountyHousesDao> implements CountyHousesManager {
    private static final Logger logger = LoggerFactory.getLogger(CountyHousesManagerImpl.class);
    //计算请求次数
    //public static int no = 0;
    @Autowired
    private CountyDao countyDao;
    
    /**
     * 获取区县和楼盘信息并保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月5日上午10:15:54
     */
    @Override
    @Transactional(value="transactionManager")
    public void getAndSaveTask() {
        //获取区县
        List<County> countyList = getCounty();
        //保存区县
        if(CollectionUtils.isNotEmpty(countyList)){
            countyDao.saves(countyList);
        }
        //获取楼盘
        List<CountyHouses> countyHousesList = getCountyHouses(countyList);
        //logger.info("\n 请求次数：    \n"+no);
        //no = 0;
        //保存楼盘
        if(CollectionUtils.isNotEmpty(countyHousesList)){
            this.getEntityDao().saves(countyHousesList);
        }
    }
    
    /**
     * 修改状态为上线
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日上午10:21:13
     */
    @Override
    @Transactional(value="transactionManager")
    public void online(){
        //获取需要上线的记录数
        Long countyNo = countyDao.getCountByStatus(County.STATUS1);
        Long housesNo = this.getEntityDao().getCountByStatus(CountyHouses.STATUS1);
        
        //判断是否有待上线状态的数据，有的话才上线
        if(countyNo!=null&&housesNo!=null&&countyNo>0&&housesNo>0){
            
            //删除原有上线状态的数据
            countyDao.delByStatus(County.STATUS3);
            this.getEntityDao().delByStatus(CountyHouses.STATUS3);
            
            //上线
            countyDao.online(County.STATUS3,County.STATUS1);
            this.getEntityDao().online(CountyHouses.STATUS3,CountyHouses.STATUS1);
        }
    }
    
    /**
     * 获取区县
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月5日上午10:47:22
     */
    private List<County> getCounty(){
        String url = PropertiesConfig.getString(PropertyKey.COUNTY_REGISTER);
        logger.info("查询区县的接口地址：   "+url);
        if(StringUtils.isBlank(url)){
            throw new BusinessException(-1, "缓存中未获取到查询区县的接口地址");
        }
        logger.info("获取区县开始：    \n");
        String result = HttpClientUtils.get(url);
        logger.info("获取区县的结果：    \n"+result);
        if (StringUtils.isNotBlank(result)) {
            /**
             * 接口返回的成功失败数据不一样，所以要先转成map判断一次
             {"rtCode":"failure","rtMsg":"请求失败","rtData":{}}
             {"rtCode": "successful","rtMsg": "请求成功","rtData": []}
             */
            @SuppressWarnings("unchecked")
            Map<String, Object> jsonResult = (Map<String, Object>) JSONUtils.parse(result);
            if (!("successful".equals((String)jsonResult.get("rtCode")))) {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            CountyDto countyDto = null;
            try {
                countyDto = mapper.readValue(result, CountyDto.class);
            } catch (Exception e) {
                logger.error("区县对象转换错误.", e);
                throw new BusinessException(-1, "获取区县失败!");//失败了就抛出异常返回
            }
            if (null != countyDto && "successful".equals(countyDto.getRtCode()) && null != countyDto.getRtData()) {
                List<County> countyList = new ArrayList<County>();
                County county;
                Date now = new Date();
                if(CollectionUtils.isNotEmpty(countyDto.getRtData().getQu())){
                    for (String qu : countyDto.getRtData().getQu()) {
                        county = new County();
                        county.setName(qu);
                        county.setType(County.TYPE0);
                        county.setCreateTime(now);
                        county.setStatus(County.STATUS1);
                        countyList.add(county);
                    }
                }
                if(CollectionUtils.isNotEmpty(countyDto.getRtData().getXian())){
                    for (String xian : countyDto.getRtData().getXian()) {
                        county = new County();
                        county.setName(xian);
                        county.setType(County.TYPE1);
                        county.setCreateTime(now);
                        county.setStatus(County.STATUS1);
                        countyList.add(county);
                    }
                }
                return countyList;
            }
        }
        return null;
    }
    
    /**
     * 获取区县楼盘
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月5日上午10:47:38
     */
    private List<CountyHouses> getCountyHouses(List<County> countyList){
        try{
            if(CollectionUtils.isNotEmpty(countyList)){
                String url = PropertiesConfig.getString(PropertyKey.COUNTY_HOUSES_REGISTER);
                logger.info("\n 查询区县楼盘的接口地址：   "+url);
                if(StringUtils.isBlank(url)){
                    throw new BusinessException(-1, "缓存中未获取到查询区县楼盘的接口地址");
                }
                ObjectMapper mapper = new ObjectMapper();
                List<CountyHouses> countyHousesList = new ArrayList<CountyHouses>();
                for (County county : countyList) {
                    for (int i = 1; true; i++) {
                        String result = sendReset(i, county.getName(), url);
                        Boolean ret = addHousesList(result, countyHousesList, county, mapper);
                        //当组装数据的时候，返回对象的数据为空或失败，就跳出循环
                        if(!ret){
                            break;
                        }
                    }
                }
                return countyHousesList;
            }
        }catch(BusinessException e){
            throw e;
        }catch(Exception e){
            throw new BusinessException(-1, "获取区县楼盘失败");
        }
        return null;
    }
    
    /**
     * 组装楼盘对象，添加到集合
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月9日上午9:26:33
     */
    private Boolean addHousesList(String result,List<CountyHouses> countyHousesList,County county,ObjectMapper mapper) throws Exception{
        if(StringUtils.isBlank(result)){
            throw new BusinessException(-1, "请求获取区县楼盘失败");
        }
        CountyHousesDto countyHousesDto = mapper.readValue(result, CountyHousesDto.class);
        CountyHouses countyHouses;
        if(null!=countyHousesDto&&"successful".equals(countyHousesDto.getRtCode())&&null!=countyHousesDto.getRtData()&&
                CollectionUtils.isNotEmpty(countyHousesDto.getRtData().getLoadList())){
            for (HousesDto housesDto : countyHousesDto.getRtData().getLoadList()) {
                countyHouses = new CountyHouses();
                /** 区县表id */
                countyHouses.setCountyId(county.getId());
                /** 楼盘名称 */
                countyHouses.setName(housesDto.getDaikuanxiangmu());
                /** 楼盘地址 */
                countyHouses.setAddr(housesDto.getXiangmudizhi());
                /** 建设单位 */
                countyHouses.setUnit(housesDto.getJianshedanwei());
                /** 公积金贷款银行 */
                countyHouses.setBank(housesDto.getChengbanyinhang());
                /** 创建时间 */
                countyHouses.setCreateTime(county.getCreateTime());
                countyHouses.setStatus(CountyHouses.STATUS1);
                countyHousesList.add(countyHouses);
            }
        }else{
            return false;
        }
        return true;
    }
    
    /**
     * 发送重试
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月9日上午9:11:30
     * @throws Exception 
     */
    private String sendReset(int pageNo,String region,String url) throws Exception{
        String result = null;
        Map<String, Object> params ;
        //同一页数据三次重试机制
        for (int i = 0; i < 3; i++) {
            params = new HashMap<String,Object>();
            params.put("region", region);
            params.put("index", pageNo+"");
            logger.info("\n 请求查询区县楼盘开始：   参数 region:"+region+"  ,index:"+pageNo);
            Long a = new Date().getTime();
            result = HttpClientUtils.post(url, params);
            Long b = new Date().getTime();
            logger.info("\n 本次请求用时：  " + (b - a)+"\n 请求查询区县楼盘结果：  "+result);
            if (StringUtils.isBlank(result)) {
                return null;
            }
            @SuppressWarnings("unchecked")
            Map<String, Object> jsonResult = (Map<String, Object>) JSONUtils.parse(result);
            //查询不成功，continue重试
            if (!("successful".equals((String)jsonResult.get("rtCode")))) {
                continue;
            }
            //查询成功，返回查询的数据 
            //no++;
            return result;
        }
        return null;
    }

    /**
     * 滚动分页获取区县楼盘信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2017年1月6日下午2:29:21
     */
    @Override
    public ScrollPage<CountyHouses> getScrollPage(ScrollPage<CountyHouses> page, Map<String, Object> conditions,
            String sessionId, String token) {
        return this.getEntityDao().getScrollPage(page, conditions);
    }
}
