package com.cqliving.cloud.online.app.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 客户端栏目表 JPA Dao
 * Date: 2016-04-28 11:59:18
 * @author Code Generator
 */
public interface AppColumnsDao extends EntityJpaDao<AppColumns, Long>,AppColumnsDaoCustom {
    /**
     * 查询同意节点下，最大的排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月30日下午1:10:51
     */
    @Query(nativeQuery=true,value="SELECT MAX(a.sort_no) FROM app_columns a WHERE a.parent_id = ?1")
    public Integer getMaxSortNo(Long pid);
    
    /**
     * 查询同意节点下，最大的排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年4月30日下午1:10:51
     */
    @Query(nativeQuery=true,value="SELECT MAX(a.sort_no) FROM app_columns a where a.app_id=?1")
    public Integer getMaxSortNoByAppId(Long appId);

	/**
	 * Title: 获取需要更新的父栏目
	 * @author Tangtao on 2016年5月1日
	 * @param appId
	 * @param version
	 * @return
	 */
    @Query(""
    		+ "FROM "
    		+ "	AppColumns a "
    		+ "WHERE "
    		+ "	EXISTS ("
    		+ "		SELECT 1	FROM AppColumns b "
    		+ "		WHERE "
    		+ "			a.id = b.parentId "
    		+ "		AND b.versionNo > ?2 "
    		+ "		AND b.appId = ?1"
    		+ ") "
    		+ "ORDER BY "
    		+ "	a.id DESC")
	List<AppColumns> getUpdateParentCols(Long appId, Integer version);
    
    /**
     * 通过模板和app查询栏目
     */
    @Query("select a from AppColumns a,AppTemplet b where a.templetCode=b.templetCode and b.appId=?1 and b.templetCode=?2 ")
    List<AppColumns> findByTempletCodeAndAppId(Long appId, String templetCode);
    
    /**
     * 根据父id 查询
     */
    @Query("from AppColumns a where parentId = ?1 and status <> ?2")
    List<AppColumns> findByPid(Long parentId,Byte status);
    
    /**
     * 查询某个客户端下最大的版本号
     */
    @Query("select max(versionNo) from AppColumns a where appId=?1")
    Integer findVersionNoByAppId(Long appId);
    
    /**
     * <p>Description: 修改是否为叶子节点 </p>
     * @author huxiaoping on 2016年4月27日
     * @param id
     * @param leafType
     */
    @Modifying
    @Query(value = "update AppColumns t set t.leafType=?2 where t.id=?1")
    void changeLeafType(Long id, Byte leafType);
    
    /**
     * <p>Description: 修改版本号 </p>
     * @author huxiaoping on 2016年7月7日
     * @param id
     * @param versionNo
     */
    @Modifying
    @Query(value = "update AppColumns t set t.versionNo=?2 where t.id=?1")
    void changeVersionNo(Long id, Integer versionNo);
    
    /**
     * <p>Description: 通过appId修改状态 </p>
     * @author huxiaoping on 2016年6月21日
     * @param status，要修改的状态
     * @param appId ，客户端id
     * @param status88 ，需要修改状态的记录
     */
    @Modifying
    @Query(value = "update AppColumns t set t.status=?1 where t.appId = ?2 and t.status = ?3")
    void sendByAppId(Byte status, Long appId ,Byte status88);
    
    /**
     * <p>Description: 修改状态 </p>
     * @author huxiaoping on 2016年5月2日
     * @param id
     * @param status
     */
    @Modifying
    @Query(value = "update AppColumns set status=?2,updatorId=?3,updator=?4,updateTime=?5,versionNo=?6 where id=?1")
    void updateStatus(Long id, Byte status, Long updatorId, String updator, Date updateTime,Integer versionNo);
    
    @Modifying
    @Query(value = "update AppColumns set sortNo=?2, parentId=?3 where id=?1")
    int sort(Long id, Integer sortNum, Long parentId);
}