package com.cqliving.cloud.online.act.manager.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.act.dao.ActCollectInfoDao;
import com.cqliving.cloud.online.act.dao.ActCollectOptionDao;
import com.cqliving.cloud.online.act.domain.ActCollectInfo;
import com.cqliving.cloud.online.act.domain.ActCollectOption;
import com.cqliving.cloud.online.act.dto.ActCollectInfoDto;
import com.cqliving.cloud.online.act.manager.ActCollectInfoManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Service("actCollectInfoManager")
public class ActCollectInfoManagerImpl extends EntityServiceImpl<ActCollectInfo, ActCollectInfoDao> implements ActCollectInfoManager {
    
    @Autowired
    private ActCollectOptionDao actCollectOptionDao;
    
	@Override
	public List<ActCollectInfo> getByApp(Long appId) {
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_appId", appId);
		Map<String, Boolean> sortMap = Maps.newHashMap();
		return this.query(map, sortMap);
	}
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
	@Override
    public PageInfo<ActCollectInfoDto> queryPage(PageInfo<ActCollectInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap){
        return this.getEntityDao().queryPage(pageInfo, map, orderMap);
    }
    
    /**
     * 通过id查询单个信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
	@Override
    public ActCollectInfoDto findById(Long id){
	    ActCollectInfoDto actCollect = this.getEntityDao().findById(id);
	    if(null!=actCollect){
	        List<ActCollectOption> optionList = actCollectOptionDao.findByActCollectInfoId(id);
	        actCollect.setOptionList(optionList);
	    }
	    return actCollect;
    }

	/**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void saveInfo(ActCollectInfo domain, String[] value) {
        //获得当前时间
        Date now = DateUtil.now();
        //修改，删除从表数据
        if(null!=domain.getId()){
            actCollectOptionDao.delByActCollectInfoId(domain.getId());
            domain.setUpdateTime(now);
        }else{
            domain.setCreateTime(now);
            domain.setUpdateTime(now);
        }
        //保存
        this.save(domain);
        //保存从表
        if(null!=value&&value.length>0){
            ActCollectOption option ;
            List<ActCollectOption> optionList = new ArrayList<ActCollectOption>();
            for (String val : value) {
                if(StringUtils.isNotBlank(val)){
                    option = new ActCollectOption();
                    option.setCreateTime(now);
                    option.setUpdateTime(now);
                    option.setActCollectInfoId(domain.getId());
                    option.setCreator(domain.getCreator());
                    option.setUpdator(domain.getUpdator());
                    option.setCreatorId(domain.getCreatorId());
                    option.setUpdatorId(domain.getUpdatorId());
                    option.setValue(val);
                    optionList.add(option);
                }
            }
            if(optionList.size()>0){
                actCollectOptionDao.saves(optionList);
            }
        }
    }

    /**
     * 通过id删除
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月13日上午10:19:18
     */
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void delById(Long... ids) {
        this.removes(ids);
        List<Long> idList = Arrays.asList(ids);
        for (Long id : idList) {
            actCollectOptionDao.delByActCollectInfoId(id);
        }
    }
}