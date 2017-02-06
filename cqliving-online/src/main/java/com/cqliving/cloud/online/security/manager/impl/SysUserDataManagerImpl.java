package com.cqliving.cloud.online.security.manager.impl;


import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.security.dao.SysUserDataDao;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.dto.SysUserDataDto;
import com.cqliving.cloud.online.security.manager.SysUserDataManager;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.framework.utils.StringUtil;
import com.google.common.collect.Lists;

@Service("sysUserDataManager")
public class SysUserDataManagerImpl extends EntityServiceImpl<SysUserData, SysUserDataDao> implements SysUserDataManager {

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.manager.SysUserDataManager#findByUserId(java.lang.Long)
	 */
	@Override
	public List<SysUserData> findByUserId(Long userId,byte type) {
		
		return getEntityDao().findByUserId(userId,type);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.manager.SysUserDataManager#findAppIdsByUserId(java.lang.Long)
	 */
	@Override
	public Long[] findValueByUserId(Long userId,byte type) {

		List<SysUserData> list = this.findByUserId(userId,type);
		
		if(CollectionUtils.isEmpty(list))return null;
		
		Long[] arrAppId = new Long[list.size()];
		for(int i=0,m=list.size();i<m;i++){
			arrAppId[i] = list.get(i).getValue();
		}
		return arrAppId;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.manager.SysUserDataManager#saveAppColumnsData(java.lang.Long, java.lang.Long[])
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public List<SysUserData> saveSysUserDataByType(Long userId, Long[] value, byte type) {
		
		if(null == userId || StringUtil.isEmpty(value)){
			throw new BusinessException(ErrorCodes.FAILURE,"用户及权限信息不能为空");
		}
		//保存前先删除
	    this.deleteByUserIdAndType(userId, type);
		List<SysUserData>  list = Lists.newArrayList();
		for(Long id : value){
			SysUserData sysUserData = new SysUserData();
			sysUserData.setType(type);
			sysUserData.setUserId(userId);
			sysUserData.setValue(id);
			sysUserData = this.getEntityDao().saveAndFlush(sysUserData);
			list.add(sysUserData);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.manager.SysUserDataManager#findPage(com.cqliving.framework.common.dao.support.PageInfo, java.util.Map, java.util.Map)
	 */
	@Override
	public PageInfo<SysUserDataDto> findPage(PageInfo<SysUserDataDto> pageInfo, Map<String, Object> map,
			Map<String, Boolean> orderMap) {
		
		return this.getEntityDao().findPage(pageInfo, map, orderMap);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.manager.SysUserDataManager#deleteByUserIdAndType(java.lang.Long, byte)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void deleteByUserIdAndType(Long userId, byte type) {
		//this.getEntityDao().deleteData(userId, type);
		List<SysUserData> data = this.getEntityDao().findByUserId(userId, type);
		if(CollectionUtils.isNotEmpty(data)){
			for(SysUserData d : data){
				this.getEntityDao().remove(d);
			}
		}
	}

}
