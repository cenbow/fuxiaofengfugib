package com.cqliving.cloud.online.tourism.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.tourism.domain.TourismSpecial;

/**
 * 旅游专题关系表 JPA Dao
 * Date: 2016-08-23 13:55:36
 * @author Code Generator
 */
public interface TourismSpecialDao extends EntityJpaDao<TourismSpecial, Long>,TourismSpecialDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update TourismSpecial set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Modifying
    @Query("update TourismSpecial set sortNo = ?2 where id = ?1")
	public int updateSortNo(Long id,Integer sortNo);
	
	@Query("from TourismSpecial where tourismId=?1 and refTourismId=?2")
	public List<TourismSpecial> findBySpecialIdRefId(Long specialId,Long refId);
	
	/**
	 * Title:根据专题id删除
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月25日
	 * @param tourismIds
	 * @return
	 */
	@Modifying
	@Query("DELETE FROM TourismSpecial WHERE tourismId IN(SELECT id FROM TourismInfo WHERE id IN (?1))")
	public int deleteByTourism(List<Long> tourismIds);
}
