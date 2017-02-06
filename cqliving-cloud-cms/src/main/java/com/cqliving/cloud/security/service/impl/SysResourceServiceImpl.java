package com.cqliving.cloud.security.service.impl;


import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.online.security.dao.SysResourceDao;
import com.cqliving.cloud.online.security.domain.SysResource;
import com.cqliving.cloud.online.security.dto.SysResourceDto;
import com.cqliving.cloud.security.cache.SecurityCache;
import com.cqliving.cloud.security.service.SysResourceService;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.redis.base.AbstractRedisClient;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("sysResourceService")
public class SysResourceServiceImpl extends EntityServiceImpl<SysResource, SysResourceDao> implements SysResourceService {

    private Logger logger = LoggerFactory.getLogger(SysResourceServiceImpl.class);

    @Autowired
    private SecurityCache securityCache;

    @Autowired
    private AbstractRedisClient abstractRedisClient;
    
    /**
     * 初始化资源数据，不需要事务，初始化失败不影响正常业务
     * @author yuwu 20160705
     */
    @Override
    public void initAllResourceCache(){
    	if(abstractRedisClient != null){
    		try{
    			logger.info("开始缓存所有的资源数据");
    			List<SysResource> list = this.getAll();
        		abstractRedisClient.del(CacheConstant.SYS_RESOURCE_URL);
        		abstractRedisClient.del(CacheConstant.SYS_RESOURCE_ID);
            	for(SysResource sysResource : list){
            		String sysResourceStr = JSON.toJSONString(sysResource);
            		abstractRedisClient.setHSet(CacheConstant.SYS_RESOURCE_URL, sysResource.getResString(), sysResourceStr);
            		abstractRedisClient.setHSet(CacheConstant.SYS_RESOURCE_ID, sysResource.getId().toString(), sysResourceStr);
            	}
            	/*String value = abstractRedisClient.getHSet(CacheConstant.CMS_RESOURCE_URL, "/module/security/sys_user_add.html");
            	SysResource temp = JSON.parseObject(value, SysResource.class);*/
    			logger.info("结束缓存所有的资源数据");
    		}catch(Exception e){
    			logger.error("获取所有资源放入缓存失败",e);
    		}
    	}
    }
    
    @Override
    public void update(SysResource o) throws BusinessException {
        super.update(o);
        securityCache.clearAllAuthorization();
        securityCache.clearAllMenu();
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public int updateStatusById(Long id, Byte status){
        return super.getEntityDao().updateStatusById(id, status);
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void sort(Long[] ids, Integer[] sortNums, Long[] parentIds,Long[] sysResTypeIds) {
        for(int j=0; j<ids.length; j++){
            super.getEntityDao().sort(ids[j], sortNums[j], parentIds[j],sysResTypeIds[j]);
        }
    }

    //递归查找
    /*private void recursionResc(Iterator<SysResource> it, String rescType, Byte status){
        while (it.hasNext()){
            SysResource resc = it.next();

            if((StringUtils.isNotBlank(rescType) && !rescType.equals(resc.getRestype()))
                    || (status!=null && status!=resc.getStatus())){
                it.remove();
            }else if(resc.getSubResource()!=null && resc.getSubResource().size()>=1){
                recursionResc(resc.getSubResource().iterator(), rescType, status);
            }
        }
    }*/

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.service.SysResourceService#findAllRes()
	 */
	@Override
	public List<SysResourceDto> findByConditions(Map<String,Object> searchMap,Map<String,Boolean> sortMap) {
		
		return this.getEntityDao().findByConditions(searchMap, sortMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.security.service.SysResourceService#findLevelOneResource(java.lang.Long)
	 */
	@Override
	public SysResourceDto findLevelOneResource(Long resId) {
		
		return this.getEntityDao().findLevelOneResource(resId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.security.service.SysResourceService#updateSysResTypeId(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void updateSysResTypeId(Long rescId) {

		SysResourceDto sysResourceDto = this.findLevelOneResource(rescId);
        
		this.updateSysType(sysResourceDto);
		
	}

	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
    private void updateSysType(SysResourceDto sysResourceDto){
		
		
		if(null == sysResourceDto || CollectionUtils.isEmpty(sysResourceDto.getSubResource()))
			return;
		
		Set<SysResourceDto> sub = sysResourceDto.getSubResource();
		
		for(SysResourceDto dto : sub){
			this.getEntityDao().updateSysType(dto.getId(),sysResourceDto.getSysResTypeId());
			updateSysType(dto);
		}
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.security.service.SysResourceService#findByGroupResType(java.util.Map, java.util.Map)
	 */
	@Override
	public Map<Long,List<SysResourceDto>> findByGroupResType(Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		List<SysResourceDto> list = this.findByConditions(searchMap, sortMap);
		
		if(CollectionUtils.isEmpty(list))return null;
		Map<Long,List<SysResourceDto>> groupMap = Maps.newHashMap();
		for(SysResourceDto dto : list){
			Long resTypeId = dto.getSysResTypeId();
			if(groupMap.containsKey(resTypeId)){
				List<SysResourceDto> groupRes = groupMap.get(resTypeId);
				groupRes.add(dto);
			}else{
				List<SysResourceDto> groupRes = Lists.newArrayList();
				groupRes.add(dto);
				groupMap.put(resTypeId,groupRes);
			}
			
		}
		
		return groupMap;
	}
}
