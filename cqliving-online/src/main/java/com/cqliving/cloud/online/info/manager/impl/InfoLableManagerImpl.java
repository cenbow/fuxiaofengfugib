package com.cqliving.cloud.online.info.manager.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.info.dao.InfoLableDao;
import com.cqliving.cloud.online.info.domain.InfoLable;
import com.cqliving.cloud.online.info.dto.InfoLableDto;
import com.cqliving.cloud.online.info.manager.InfoLableManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("infoLableManager")
public class InfoLableManagerImpl extends EntityServiceImpl<InfoLable, InfoLableDao> implements InfoLableManager {

	@Override
	public List<InfoLable> findByAppId(Long appId, Byte sourceType) {
		
		return this.getEntityDao().findByAppIdAndSourceType(appId, sourceType);
	}
	
	/**
     * 查询单条记录
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    @Override
    public InfoLableDto getById(Long id) {
        return this.getEntityDao().getById(id);
    }
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    @Override
    public PageInfo<InfoLableDto> queryInfoLabelDtoPage(PageInfo<InfoLableDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return this.getEntityDao().queryInfoLabelDtoPage(pageInfo, map, orderMap);
    }
    
    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月6日下午5:13:06
     */
    @Override
    public void saveLable(InfoLable infoLable){
        if(null!=infoLable.getId()){
            List<InfoLable> lableList = this.getEntityDao().findByAppIdAndSourceTypeAndNameAndNotId(infoLable.getAppId(), infoLable.getSourceType(),infoLable.getName(),infoLable.getId());
            if(null!=lableList&&lableList.size()>0){
                throw new BusinessException("标签已存在！");
            }
            this.update(infoLable);
        }else{
            List<InfoLable> lableList = this.getEntityDao().findByAppIdAndSourceTypeAndName(infoLable.getAppId(), infoLable.getSourceType(),infoLable.getName());
            if(null!=lableList&&lableList.size()>0){
                throw new BusinessException("标签已存在！");
            }
            this.save(infoLable);
        }
                
    }
}
