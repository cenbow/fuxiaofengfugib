/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.app.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.app.dao.AppColumnsDaoCustom;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.app.dto.TreeNodeState;
import com.cqliving.cloud.online.interfacc.dto.GetColumnsData;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.tool.common.util.StringUtil;

/**
 * Title:
 * <p>
 * Description:
 * </p>
 * Copyright (c) CQLIVING 2016
 * 
 * @author fuxiaofeng on 2016年4月29日
 */
public class AppColumnsDaoImpl implements AppColumnsDaoCustom {

	@Autowired
	private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;

	@Override
	public List<AppColumnsDto> getByConditions(Map<String, Object> conditions) {
		StringBuilder sql = new StringBuilder();

		Object columnsType = conditions.get("EQ_columnsType");
		conditions.remove("EQ_columnsType");
		Long[] sysUserDataValue = (Long[]) conditions.get("sysUserDataValue");
		conditions.remove("sysUserDataValue");
		Object filter_sysUserDataValue = conditions.get("filter_sysUserDataValue");
		conditions.remove("filter_sysUserDataValue");
		sql.append("select ac.*,ac.name text from app_columns ac ");
        sql.append(" where ac.status <> 99 ");
        sql.append(" ${WHERE} ");
        sql.append(" order by ac.sort_no asc,id asc");
        
        List<AppColumnsDto> list = mysqlPagedJdbcTemplateV2.queryForList(AppColumnsDto.class, sql.toString(), conditions);
		return this.wrapper(list,columnsType,sysUserDataValue,filter_sysUserDataValue);
	}
	
	@Override
	public List<AppColumnsDto> getList(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
	    StringBuilder sql = new StringBuilder();
	    
	    sql.append("select ac.*,ac.name text from app_columns ac ");
	    
	    Object columnsType = conditions.get("EQ_columnsType");
	    conditions.remove("EQ_columnsType");
	    Object sysUserDataValue = conditions.get("sysUserDataValue");
		conditions.remove("sysUserDataValue");
		Object filter_sysUserDataValue = conditions.get("filter_sysUserDataValue");
		conditions.remove("filter_sysUserDataValue");
		List<AppColumnsDto> list = mysqlPagedJdbcTemplateV2.queryForList(AppColumnsDto.class, sql.toString(), conditions , orderMap);
	    return this.wrapper(list,columnsType,sysUserDataValue,filter_sysUserDataValue);
	}

	// 去重复
	public List<AppColumnsDto> uniqueById(List<AppColumnsDto> list) {

		if (CollectionUtils.isEmpty(list))
			return null;

		// 根据资源ID排重
		for (int i = 0; i < list.size(); i++) {// list的大小每次都重新取

			AppColumnsDto dto1 = list.get(i);
			for (int j = list.size() - 1; j >= i + 1; j--) {// 倒着取，取到i的前一个截止
				AppColumnsDto dto2 = list.get(j);
				if (dto1.getId().longValue() == dto2.getId().longValue()) {// 重复
					list.remove(dto2);
				}
			}
		}
		return list;
	}

	//回显功能，有这个权限的选中状态
	private AppColumnsDto handleSysUserData(AppColumnsDto appColumnsDto,Object sysUserDataValue){
		
		if(null == appColumnsDto || null == sysUserDataValue){
			return appColumnsDto;
		}
		Long[] sysUserDataValues = (Long[])sysUserDataValue;
		TreeNodeState treeNodeState = appColumnsDto.getState();
		for(Long columnId : sysUserDataValues){
			if(columnId.longValue() == appColumnsDto.getId().longValue()){
				treeNodeState.setSelected(true);
			}
		}
		return appColumnsDto;
	}
	
	
	public List<AppColumnsDto> handleAppColumnDto(List<AppColumnsDto> list,Object columnsType){
		
		if(null == columnsType){
			return list;
		}
		if(CollectionUtils.isEmpty(list))return null;
		Set<AppColumnsDto> data = new TreeSet<AppColumnsDto>();
		Iterator<AppColumnsDto> it = list.iterator();
		while(it.hasNext()){
			AppColumnsDto appColumnsDto = it.next();
			//节点不是指定栏目类型
			if(null != columnsType && (byte) columnsType != appColumnsDto.getColumnsType().byteValue()){
				appColumnsDto.setSelectable(false);
			}
			if(appColumnsDto.getParentId().longValue() == 0)data.add(appColumnsDto);
			
			String code = appColumnsDto.getCode();
			if(appColumnsDto.getColumnsType().byteValue() == (byte)columnsType){
				String[] arrCode = code.split("\\.");
				for(String s : arrCode){
					for(AppColumnsDto dto : list){
						if(StringUtil.stringToLong(s) == dto.getId().longValue())data.add(dto);
					}
				}
			}
		}
		return new ArrayList<AppColumnsDto>(data);
	}
	
	private List<AppColumnsDto> filterSysUserDataValue(Object filter_sysUserDataValue,List<AppColumnsDto> list){
		
		if(null == filter_sysUserDataValue){
			return list;
		}
		if(CollectionUtils.isEmpty(list))return null;
		Long[] haspermissonColumnIds = (Long[]) filter_sysUserDataValue;
		String columnIdsStr = Arrays.toString(haspermissonColumnIds);
		Set<AppColumnsDto> data = new TreeSet<AppColumnsDto>();
		Iterator<AppColumnsDto> it = list.iterator();
		while(it.hasNext()){
			AppColumnsDto appColumnsDto = it.next();
			if(appColumnsDto.getParentId().longValue() == 0)data.add(appColumnsDto);
			String code = appColumnsDto.getCode();
			if(columnIdsStr.contains(String.valueOf(appColumnsDto.getId()))){
				String[] arrCode = code.split("\\.");
				for(String s : arrCode){
					for(AppColumnsDto dto : list){
						if(StringUtil.stringToLong(s) == dto.getId().longValue())data.add(dto);
					}
				}
			}
		}
		return new ArrayList<AppColumnsDto>(data);
	}
	
	public List<AppColumnsDto> wrapper(List<AppColumnsDto> list,Object columnsType,Object sysUserDataValue,Object filter_sysUserDataValue) {

		if (CollectionUtils.isEmpty(list))
			return null;
		
		//过滤栏目类型
		list = handleAppColumnDto(list,columnsType);
		//过滤没有栏目权限的数据
		list = filterSysUserDataValue(filter_sysUserDataValue,list);
		// 根据资源ID排重
		list = uniqueById(list);

		Iterator<AppColumnsDto> it = list.listIterator();

		while (it.hasNext()) {
			AppColumnsDto appColumnsDto = it.next();
			//顶级节点不能选择
			/*if(appColumnsDto.getParentId().longValue() == 0){
				appColumnsDto.setSelectable(false);
			}*/
			appColumnsDto = handleSysUserData(appColumnsDto,sysUserDataValue);
			TreeSet<AppColumnsDto> subAppColumnsDto = new TreeSet<AppColumnsDto>();
			for (AppColumnsDto dto : list) {
				
				if (null != dto.getParentId()
						&& dto.getParentId().longValue() == appColumnsDto.getId().longValue()) {
					subAppColumnsDto.add(dto);
				}
			}
			if (!CollectionUtils.isEmpty(subAppColumnsDto)){
			    appColumnsDto.setSubs(subAppColumnsDto);
			    appColumnsDto.setNodes(subAppColumnsDto);
            }
		}
		// 删除子，只保留父，因为子已放在父的子集里面了
		it = list.listIterator();
		while (it.hasNext()) {
			AppColumnsDto appColumnsDto = it.next();

			if ((null != appColumnsDto && appColumnsDto.getParentId() != null && appColumnsDto.getParentId() != 0l) ||
					(appColumnsDto.getParentId().longValue() == 0 && CollectionUtils.isEmpty(appColumnsDto.getNodes())
					&& null != columnsType && (byte)columnsType != appColumnsDto.getColumnsType().byteValue())) {
				it.remove();
			}
		}
		return list;
	}
	
    @Override
    public List<GetColumnsData> getByAppId(Map<String, Object> conditions, Map<String, Boolean> orderMap) {
        StringBuilder sql = new StringBuilder();
        //sql.append("SELECT a.id,a.name,a.templet_code,a.parent_code,a.code,a.sort_no,a.image_url,a.columns_type,a.columns_url FROM app_columns a ");
        sql.append("SELECT a.id,a.name,a.templet_code,a.parent_code,a.code,a.sort_no,a.image_url,a.columns_type,a.columns_url,a.app_id,a.status, a.view_count_type, a.view_date date_type, a.view_reply_count reply_count_type FROM app_columns a ");
        sql.append(" where a.pl_view_type <> ").append(AppColumns.PLVIEWTYPE4);
        return mysqlPagedJdbcTemplateV2.queryForList(GetColumnsData.class, sql.toString(), conditions , orderMap);
    }
}