package com.org.weixin.module.zjchj.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.zjchj.domain.ZjchjAward;

/**
 * 奖品表 JPA Dao
 * Date: 2016-09-26 15:17:36
 * @author Code Generator
 */
public interface ZjchjAwardDao extends EntityJpaDao<ZjchjAward, Long>, ZjchjAwardDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ZjchjAward set status = ?1 where id in ?2")
    public int updateStatus(Byte status, List<Long> ids);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年9月27日
	 * @param level
	 * @return
	 */
	@Query("select sum(virtualNum) from ZjchjAward where level = ?1 and status = 3 and actualNum > 0 and virtualNum > 0")
	Long getVirtualTotalCount(Byte level);

	/**
	 * <p>Description: 减少虚拟数量</p>
	 * @author Tangtao on 2016年9月27日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update ZjchjAward set virtualNum = virtualNum - 1 where id = ?1")
	int decreaseVirtualNum(Long id);
	
	/**
	 * <p>Description: 减少真实数量</p>
	 * @author Tangtao on 2016年9月27日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update ZjchjAward set actualNum = actualNum - 1 where id = ?1")
	int decreaseActualNum(Long id);

	/**
	 * <p>Description: 增加抽中数</p>
	 * @author Tangtao on 2016年9月27日
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("update ZjchjAward set awardNum = ifnull(awardNum, 0) + 1 where id = ?1")
	int increaceAwardNum(Long id);

}