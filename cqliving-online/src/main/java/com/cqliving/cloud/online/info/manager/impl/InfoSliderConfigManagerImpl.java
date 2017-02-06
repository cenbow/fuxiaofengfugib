package com.cqliving.cloud.online.info.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.online.info.dao.InfoSliderConfigDao;
import com.cqliving.cloud.online.info.domain.InfoSliderConfig;
import com.cqliving.cloud.online.info.dto.InfoSliderConfigDto;
import com.cqliving.cloud.online.info.manager.InfoSliderConfigManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.redis.base.AbstractRedisClient;

@Service("infoSliderConfigManager")
public class InfoSliderConfigManagerImpl extends EntityServiceImpl<InfoSliderConfig, InfoSliderConfigDao> implements InfoSliderConfigManager {
    @Autowired
    private AbstractRedisClient abstractRedisClient;

    /**
     * 通过appId查询轮播图配置列表信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午2:08:49
     */
    @Override
    public List<InfoSliderConfigDto> getListByAppId(Long appId) {
        return this.getEntityDao().getListByAppId(appId);
    }

    /**
     * 保存轮播图配置
     * @Description 
     * @Company 
     * @parameter 
     * <p>appId：配置所属app</p>
     * <p>columnsId：配置所属栏目</p>
     * <p>value：配置值</p>
     * <p>userId：操作人Id</p>
     * <p>userNmae：操作人名称</p>
     * @return
     * @author huxiaoping 2016年8月30日下午4:00:54
     */
    @Override
    public void save(Long appId, Long[] ids, Long[] columnsIds, Integer[] values, Long userId, String userNmae) {
        //构建对象
        List<InfoSliderConfig> sliderConfigList = createObject(appId, ids, columnsIds, values, userId, userNmae);
        //保存数据
        saveInfoSliderConfig(sliderConfigList);
        //修改缓存
        saveCache(sliderConfigList, appId);
    }
    
    /**
     * 构建轮播图配置对象
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午4:30:16
     */
    private List<InfoSliderConfig> createObject(Long appId, Long[] ids, Long[] columnsIds, Integer[] values, Long userId, String userNmae) {
        List<InfoSliderConfig> sliderConfigList = null;
        if(null!=ids &&ids.length>0){
            sliderConfigList = new ArrayList<InfoSliderConfig>();
            InfoSliderConfig sliderConfig ;
            Date now = new Date();
            //获取所有已经存在的配置信息
            List<InfoSliderConfigDto> sliderConfigDtoList = this.getEntityDao().getListByAppId(appId);
            for (int i = 0; i < ids.length; i++) {
                sliderConfig = null;
                //比较配置是否存在，若存在，需要设置id，以防重新增加新数据
                for (InfoSliderConfigDto dto : sliderConfigDtoList) {
                    if(dto.getColumnsId().equals(columnsIds[i])){
                        sliderConfig = new InfoSliderConfig();
                        sliderConfig.setId(dto.getId());
                        sliderConfig.setCreateTime(dto.getCreateTime());
                        sliderConfig.setCreatorId(dto.getCreatorId());
                        sliderConfig.setCreator(dto.getCreator());
                        break;
                    }
                }
                sliderConfig = sliderConfig==null?new InfoSliderConfig():sliderConfig;
                sliderConfig.setAppId(appId);
                sliderConfig.setColumnsId(columnsIds[i]);
                sliderConfig.setValue(values[i]);
                sliderConfig.setCreateTime(null==sliderConfig.getCreateTime()?now:sliderConfig.getCreateTime());
                sliderConfig.setCreatorId(null==sliderConfig.getCreatorId()?userId:sliderConfig.getCreatorId());
                sliderConfig.setCreator(StringUtils.isBlank(sliderConfig.getCreator())?userNmae:sliderConfig.getCreator());
                sliderConfig.setUpdateTime(now);
                sliderConfig.setUpdator(userNmae);
                sliderConfig.setUpdatorId(userId);
                sliderConfigList.add(sliderConfig);
            }
        }
        return sliderConfigList;
    }
    
    /**
     * 数据库保存配置信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午4:52:42
     */
    private void saveInfoSliderConfig(List<InfoSliderConfig> sliderConfigList){
        if(null!=sliderConfigList&&sliderConfigList.size()>0){
            for (InfoSliderConfig sliderConfig : sliderConfigList) {
                super.save(sliderConfig);
            }
        }
    }
    
    /**
     * 保存缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午5:34:53
     */
    @Override
    public void saveCache(List<InfoSliderConfig> sliderConfigList,Long appId) {
        if(null!=sliderConfigList&&sliderConfigList.size()>0){
            //删除所有轮播图配置
            abstractRedisClient.delHSet(CacheConstant.INFO_SLIDER_CONFIG,appId+"_*");
            for (InfoSliderConfig sliderConfig : sliderConfigList) {
                //循环保存轮播图配置缓存
                abstractRedisClient.setHSet(CacheConstant.INFO_SLIDER_CONFIG, appId+"_"+sliderConfig.getColumnsId(), sliderConfig.getValue()+"");
            }
        }
    }
    
    /**
     * 获取轮播图缓存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年8月30日下午5:34:53
     */
    @Override
    public Integer getCache(Long appId,Long columnsId) {
        String val = abstractRedisClient.getHSet(CacheConstant.INFO_SLIDER_CONFIG, appId+"_"+columnsId);
        if(StringUtils.isBlank(val)){
            Integer valInt = 5;
            List<InfoSliderConfigDto> sliderConfigDtoList = this.getEntityDao().getListByAppId(appId);
            if(null!=sliderConfigDtoList && sliderConfigDtoList.size()>0){
                InfoSliderConfig sliderConfig ;
                List<InfoSliderConfig> sliderConfigList = new ArrayList<InfoSliderConfig>();
                for (InfoSliderConfigDto dto : sliderConfigDtoList) {
                    if(columnsId!=null&&columnsId.equals(dto.getColumnsId())){
                        valInt = dto.getValue();
                    }
                    sliderConfig = new InfoSliderConfig();
                    sliderConfig.setColumnsId(dto.getColumnsId());
                    sliderConfig.setValue(dto.getValue());
                    sliderConfigList.add(sliderConfig);
                    //保存缓存
                    saveCache(sliderConfigList, appId);
                }
            }
            return valInt;
        }else{
            return Integer.valueOf(val);
        }
    }
}