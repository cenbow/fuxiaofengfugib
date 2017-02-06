package com.cqliving.cloud.online.info.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InfoCorrelation;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 相关资讯表 JPA Dao
 * Date: 2016-04-15 09:44:43
 * @author Code Generator
 */
public interface InfoCorrelationDao extends EntityJpaDao<InfoCorrelation, Long>, InfoCorrelationDaoCustom {

	/**
	 * <p>Description: 清空排序</p>
	 * @author Tangtao on 2016年5月12日
	 * @param idList
	 */
	@Modifying
	@Query(value="update InfoCorrelation set sortNo = " + Integer.MAX_VALUE + " where id in ?1 ")
	void clearSortNo(List<Long> idList);

	/**
	 * <p>Description: 修改排序</p>
	 * @author Tangtao on 2016年5月12日
	 * @param id
	 * @param sortNo
	 */
	@Modifying
	@Query(value="update InfoCorrelation set sortNo = ?2 where id = ?1 ")
	void modifySortNo(Long id, Integer sortNo);

	/**
	 * Title:
	 * <p>Description:</p>
	 * @author fuxiaofeng on 2016年5月16日
	 * @param themeId
	 * @return
	 */
	public List<InfoCorrelation> findByThemeId(Long themeId);
	
	@Query(value="from InfoCorrelation where infoClassifyId= ?1 and refInfoClassifyId = ?2")
	public List<InfoCorrelation> findByInfoClssifyId(Long infoClassifyId,Long refInfoClassifyId);
	
	@Modifying
	@Query(value="delete from InfoCorrelation where infoClassifyId= ?1 and refInfoClassifyId = ?2")
	public int cancelCorrelation(Long infoClassifyId, Long refInfoClassifyId);

	/**
	 * <p>Description: 批量删除</p>
	 * @author Tangtao on 2016年5月25日
	 * @param ids
	 */
	@Modifying
	@Query("delete from InfoCorrelation where id in ?1")
	void delete(List<Long> ids);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月6日
	 * @param id
	 * @param status3
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 */
	@Modifying
	@Query("update InfoCorrelation set status = ?2, updateTime = ?3, updatorId = ?4, updator = ?5 where id = ?1")
	void changeStatus(Long id, Byte status, Date updateTime, Long updatorId, String updator);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年12月6日
	 * @param idList
	 * @param status
	 * @param updateTime
	 * @param updatorId
	 * @param updator
	 */
	@Modifying
	@Query("update InfoCorrelation set status = ?2, updateTime = ?3, updatorId = ?4, updator = ?5  where id in ?1")
	void changeStatusBatch(List<Long> idList, Byte status, Date updateTime, Long updatorId, String updator);

}