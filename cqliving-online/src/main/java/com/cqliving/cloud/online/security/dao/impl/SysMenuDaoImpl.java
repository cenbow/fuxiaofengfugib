/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.security.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.security.dao.SysMenuDaoCustom;
import com.cqliving.cloud.online.security.dto.MenuView;
import com.cqliving.cloud.online.security.dto.SysMenuDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.google.common.collect.Lists;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年4月20日
 */
public class SysMenuDaoImpl implements SysMenuDaoCustom {

	@Autowired
	MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
	
	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.dao.SysMenuDaoCustom#findAllSysMenu()
	 */
	@Override
	public List<SysMenuDto> findAllSysMenu() {
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from sys_menu ORDER BY sort_num ");

		List<SysMenuDto> origionSysMenu = mysqlPagedJdbcTemplateV2.queryForList(sql.toString(), SysMenuDto.class);
		
		if(CollectionUtils.isEmpty(origionSysMenu)){
			return null;
		}
		
		this.wrapper(origionSysMenu);
		
		return origionSysMenu;
	}
	
	public void wrapper(List<SysMenuDto> list){
		
		if(CollectionUtils.isEmpty(list)) return;
		Iterator<SysMenuDto> it = list.listIterator();
		
		while(it.hasNext()){
			SysMenuDto sysMenuDto = it.next();
			if(null != sysMenuDto){
				
				Set<SysMenuDto> submenu = new TreeSet<SysMenuDto>();
				for(SysMenuDto sysMenu : list){
					if(null != sysMenu.getParentId() && sysMenu.getParentId().longValue() == sysMenuDto.getId().longValue()){
						submenu.add(sysMenu);
					}
				}
				if(!CollectionUtils.isEmpty(submenu))
				sysMenuDto.setSubSysMenu(submenu);
			}
		}
		//删除子，只保留父，因为子已放在父的子集里面了
		it = list.listIterator();
		while(it.hasNext()){
			SysMenuDto sysMenuDto = it.next();
			
			if(null != sysMenuDto && sysMenuDto.getParentId() != null && sysMenuDto.getParentId() != 0l){
				it.remove();
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.dao.SysMenuDaoCustom#queryAuthorisedMenusByUserName(java.lang.String)
	 */
	@Override
	public List<MenuView> queryAuthorisedMenusByUserName(String userName) {

        StringBuilder sql = new StringBuilder();
        
        sql.append("select * from (select sm.ID,sm.PARENT_ID pid,sm.ICON icon_Cls,sm.SHOW_MODE,sm.TITLE,sm.TITLE_FIRST_SPELL,sr.RES_STRING url,sm.sort_num ");
        sql.append("from sys_menu sm,sys_resource sr,sys_user su,");
        sql.append("sys_user_role sur,sys_role_resc srr ");
        sql.append("where sm.RESOURCE_ID=sr.ID and su.ID=sur.USER_ID AND ");
        sql.append("sur.ROLE_ID=srr.ROLE_ID and srr.RESC_ID=sr.ID ");
        sql.append("and su.USERNAME = ? ");
        //查询根菜单
        sql.append("union ");
        sql.append("select sm.ID,sm.PARENT_ID pid,sm.ICON icon_Cls,sm.SHOW_MODE,sm.TITLE,sm.TITLE_FIRST_SPELL,1 url,sm.sort_num ");
        sql.append("from sys_menu sm where sm.PARENT_ID=0 ) sm order by sm.sort_num ");
        
        List<MenuView> data = mysqlPagedJdbcTemplateV2.queryForList(MenuView.class, sql.toString(), new Object[]{userName});
        
        return wrapperMenuView(data);
        
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.dao.SysMenuDaoCustom#queryAuthorisedMenusByRole(java.lang.Long)
	 */
	@Override
	public List<MenuView> queryAuthorisedMenusByRole(Long roleId) {
		 StringBuilder sql = new StringBuilder();
		 
		 sql.append("select sm.ID,sm.PARENT_ID pid,sm.ICON icon_Cls,sm.SHOW_MODE,sm.TITLE,sm.TITLE_FIRST_SPELL,sr.RES_STRING url ");
		 sql.append("from sys_menu sm,sys_role_resc srr,sys_resource sr ");
		 sql.append("where sm.RESOURCE_ID=srr.RESC_ID and srr.RESC_ID=sr.ID ");
		 sql.append("and srr.ROLE_ID = ? ");
		//查询根菜单
        sql.append("union ");
        sql.append("select sm.ID,sm.PARENT_ID pid,sm.ICON icon_Cls,sm.SHOW_MODE,sm.TITLE,sm.TITLE_FIRST_SPELL,1 url ");
        sql.append("from sys_menu sm where sm.PARENT_ID=0 ");
        
        List<MenuView> data = mysqlPagedJdbcTemplateV2.queryForList(MenuView.class, sql.toString(), new Object[]{roleId});

        return wrapperMenuView(data);
	}
	
	
	//去重复
	public List<MenuView> uniqueById(List<MenuView> list){
		
        if(CollectionUtils.isEmpty(list)) return null;
		
		//根据资源ID排重
		for(int i=0;i<list.size();i++){//list的大小每次都重新取
			
			MenuView dto1 = list.get(i);
			for(int j=list.size()-1;j>=i+1;j--){//倒着取，取到i的前一个截止
				MenuView dto2 = list.get(j);
				if(dto1.getId() == dto2.getId()){//重复
					list.remove(dto2);
				}
			}
		}
		return list;
	}
	
      public List<MenuView> wrapperMenuView(List<MenuView> list){
		
		if(CollectionUtils.isEmpty(list)) return null;
		
		list = uniqueById(list);
		
		Iterator<MenuView> it = list.listIterator();
		//组装父子关系
		while(it.hasNext()){
			MenuView menuView = it.next();
			if(null != menuView){
				
				List<MenuView> submenu = Lists.newArrayList();
				for(MenuView sysMenu : list){
					if(sysMenu.getPid() == menuView.getId()){
						submenu.add(sysMenu);
					}
				}
				if(!CollectionUtils.isEmpty(submenu))
				   menuView.setChildren(submenu);
			}
		}
		//删除子，只保留父，因为子已放在父的子集里面了
		it = list.listIterator();
		while(it.hasNext()){
			MenuView menuView = it.next();
			
			if((null != menuView && menuView.getPid() != 0l) || 
					(null != menuView && menuView.getPid() == 0 && CollectionUtils.isEmpty(menuView.getChildren()))){
				it.remove();
			}
			
		}
		return list;
	}
}
