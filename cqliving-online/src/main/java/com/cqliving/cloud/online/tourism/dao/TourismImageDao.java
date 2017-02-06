package com.cqliving.cloud.online.tourism.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.tourism.domain.TourismImage;

/**
 * 旅游图片表 JPA Dao
 * Date: 2016-08-23 13:55:07
 * @author Code Generator
 */
public interface TourismImageDao extends EntityJpaDao<TourismImage, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update TourismImage set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * Title:根据旅游id删除
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月24日
	 * @param tourismId
	 * @return
	 */
	@Modifying
	@Query("delete from TourismImage where tourismId=?1")
	public int deleteByTourismId(Long tourismId);
	
	public List<TourismImage> findByTourismId(Long tourismId);
}
