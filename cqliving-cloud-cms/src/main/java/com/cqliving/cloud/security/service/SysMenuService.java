package com.cqliving.cloud.security.service;

import java.util.List;

import com.cqliving.cloud.online.security.domain.SysMenu;
import com.cqliving.cloud.online.security.dto.MenuView;
import com.cqliving.cloud.online.security.dto.SysMenuDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 系统菜单表 Service
 *
 * Date: 2015-05-12 15:39:06
 *
 * @author Code Generator
 *
 */
public interface SysMenuService extends EntityService<SysMenu> {

    public int updateStatusById(Long id, Integer status);

    /**
     * 通过用户名获取菜单
     * @param userName 用户名
     * @return
     */
    List<MenuView> queryAuthorisedMenus(String userName);


    /**
     * 通过角色获取菜单
     * @param roleId 角色ID
     * @return
     */
    List<MenuView> queryAuthorisedMenus(Long roleId);

    /**
     * 通过ID删除， 并删除子菜单
     * @param id
     * @return
     */
    public int deleteTree(long id);

    /**
     * 批量修改排序值
     * @param ids
     * @param sortNums
     * @param parentIds
     */
    public void sort(Long[] ids, Integer[] sortNums, Long[] parentIds);

    public List<SysMenuDto> findAllSysMenu();
}
