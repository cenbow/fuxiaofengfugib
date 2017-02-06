package com.cqliving.cloud.online.security.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.security.domain.SysMenu;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 系统菜单表 JPA Dao
 *
 * Date: 2015-05-12 15:39:06
 *
 * @author Code Generator
 *
 */
public interface SysMenuDao extends EntityJpaDao<SysMenu, Long>,SysMenuDaoCustom {

    @Modifying
    @Query(value = "update SysMenu t set t.status=?2 where id=?1")
    public int updateStatusById(Long id, Integer status);


    /**
     * 通过用户名获取菜单
     * @param userName 用户名
     * @return
     */
    /*@Query("select c,d from SysResource c,SysRole r, SysMenu d left join r.users u left join r.rescs re where u.username = ?1 and c.id = re.id"
            + " and c.id=d.resourceId and d.status = 1 and c.status=1 order by d.parentId asc, d.sortNum asc")
    List<Object> queryAuthorisedMenusByUserName(String userName);*/

    /**
     * 通过角色获取菜单
     * @param roleId 角色名
     * @return
     */
   /* @Query("select c,d from SysResource c,SysRole r, SysMenu d left join r.rescs re where c.id = re.id"
            + " and c.id=d.resourceId and d.status = 1 and c.status=1 and r.id=?1 order by d.parentId asc, d.sortNum asc")
    List<Object> queryAuthorisedMenusByRole(Long roleId);*/

    @Modifying
    @Query(value = "delete from SysMenu where id=?1 or parentId=?1")
    int deleteTree(Long id);

    @Modifying
    @Query(value = "update SysMenu set sortNum=?2, parentId=?3 where id=?1")
    int sort(Long id, Integer sortNum, Long parentId);

}
