/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.security.dao.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.security.dao.SysResourceDaoCustom;
import com.cqliving.cloud.online.security.dto.SysResourceDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年4月20日
 */
public class SysResourceDaoImpl implements SysResourceDaoCustom{

	@Autowired
	MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.dao.SysResourceDaoCustom#findAllRes()
	 */
	@Override
	public List<SysResourceDto> findByConditions(Map<String,Object> searchMap,Map<String,Boolean> sortMap) {

       StringBuilder sql = new StringBuilder();
       if(null == searchMap || searchMap.isEmpty() || null == searchMap.get("EQ_userId") || 0 ==Long.parseLong(String.valueOf(searchMap.get("EQ_userId")))){
    	   sql.append("select * from sys_resource ");
       }else{
    	   sql.append("select sr.*,sur.ROLE_ID,sur.USER_ID ");
    	   sql.append("from sys_resource sr LEFT JOIN sys_role_resc srr on sr.ID=srr.RESC_ID ");
    	   sql.append("LEFT JOIN sys_user_role sur on sur.ROLE_ID=srr.ROLE_ID  ${WHERE} ");
    	  // sql.append(" ORDER BY sr.SORT_NUM ");
       }
       List<SysResourceDto> list = mysqlPagedJdbcTemplateV2.queryForList(SysResourceDto.class, sql.toString(), searchMap, sortMap);
		return this.wrapper(list);
	}
	
	//去重复
	public List<SysResourceDto> uniqueById(List<SysResourceDto> list){
		
        if(CollectionUtils.isEmpty(list)) return null;
		
		//根据资源ID排重
		for(int i=0;i<list.size();i++){//list的大小每次都重新取
			
			SysResourceDto dto1 = list.get(i);
			for(int j=list.size()-1;j>=i+1;j--){//倒着取，取到i的前一个截止
				SysResourceDto dto2 = list.get(j);
				if(dto1.getId().longValue() == dto2.getId().longValue()){//重复
					list.remove(dto2);
				}
			}
		}
		return list;
	}
	
	
	public List<SysResourceDto> wrapper(List<SysResourceDto> list){
		
		if(CollectionUtils.isEmpty(list)) return null;
		
		//根据资源ID排重
		list = uniqueById(list);
		
		Iterator<SysResourceDto> it = list.listIterator();
		
		while(it.hasNext()){
			SysResourceDto sysResDto = it.next();
			if(null != sysResDto){
				
				Set<SysResourceDto> subSysRes = new TreeSet<SysResourceDto>();
				for(SysResourceDto sysRes : list){
					
					if(null != sysRes.getParentId() && sysRes.getParentId().longValue() == sysResDto.getId().longValue()){
						subSysRes.add(sysRes);
					}
				}
				if(!CollectionUtils.isEmpty(subSysRes))
				    sysResDto.setSubResource(subSysRes);
			}
		}
		//删除子，只保留父，因为子已放在父的子集里面了
		it = list.listIterator();
		while(it.hasNext()){
			SysResourceDto sysResDto = it.next();
			
			if(null != sysResDto && sysResDto.getParentId() != null && sysResDto.getParentId() != 0l){
				it.remove();
			}
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.dao.SysResourceDaoCustom#findLevelOneResource(java.lang.Long)
	 */
	@Override
	public SysResourceDto findLevelOneResource(Long resId) {

        StringBuilder sql = new StringBuilder();
		
		sql.append("select * from sys_resource where id = ? ");
		List<SysResourceDto> data = mysqlPagedJdbcTemplateV2.queryForList(SysResourceDto.class, sql.toString(), resId);
		
		if(CollectionUtils.isEmpty(data))return null;
		
		SysResourceDto sysResourceDto = data.get(0);
		
		if(null != sysResourceDto.getParentId() && 0 != sysResourceDto.getParentId().longValue()){
			return sysResourceDto;
		}
		
		this.findByParentId(sysResourceDto);
		
		return sysResourceDto;
	}
	
	public void findByParentId(SysResourceDto sysResourceDto){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from sys_resource where PARENT_ID = ? ");
		List<SysResourceDto> data = mysqlPagedJdbcTemplateV2.queryForList(SysResourceDto.class, sql.toString(), sysResourceDto.getId());
		
		if(CollectionUtils.isEmpty(data)){
			return;
		}
		sysResourceDto.setSubResource(new HashSet<SysResourceDto>(data));
		for(SysResourceDto dto : data){
			findByParentId(dto);
		}
	}
	
}
