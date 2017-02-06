package com.cqliving.cloud.online.shopping.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 商品表 JPA Dao
 * Date: 2016-11-17 15:41:33
 * @author Code Generator
 */
public interface ShoppingGoodsDao extends EntityJpaDao<ShoppingGoods, Long>, ShoppingGoodsDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ShoppingGoods set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);

	/**
	 * <p>Description: 递增收藏数</p>
	 * @author Tangtao on 2016年11月21日
	 * @param id
	 */
	@Modifying
	@Query("update ShoppingGoods set collectCount = ifnull(collectCount, 0) + 1 where id = ?1")
	void increaseCollectedCount(Long id);

	/**
	 * <p>Description: 递减收藏数</p>
	 * @author Tangtao on 2016年11月21日
	 * @param id
	 */
	@Modifying
	@Query("update ShoppingGoods set collectCount = ifnull(collectCount, 1) - 1 where id = ?1")
	void decreaseCollectedCount(Long id);
	
	/**
	 * Title:商品库存修改
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年11月25日
	 * @param id
	 * @param stock
	 * @return
	 */
	@Modifying
	@Query("update ShoppingGoods set stock = stock + ?1 where id = ?2")
	public int updateStock(Integer stock, Long id);
	
	/**
	 * 递增评论量
	 * @Description 
	 * @Company 
	 * @parameter 
	 * @return
	 * @author huxiaoping 2016年11月29日下午2:39:30
	 */
	@Modifying
	@Query("update ShoppingGoods set praiseCount = ifnull(praiseCount, 0) + 1 where id = ?1")
	void increasePraiseCount(Long id);
	
	/**
	 * <p>Description: 递增评论数</p>
	 * @author Tangtao on 2016年12月12日
	 * @param id
	 */
	@Modifying
	@Query("update ShoppingGoods set replyCount = ifnull(replyCount, 0) + 1 where id = ?1")
	void increaseReplyCount(Long id);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Modifying
    @Query("update ShoppingGoods set sortNo=?1 where id = ?2")
    public void updateSortNo(Integer sortNo,Long id);
    
    /**
     * 修改推荐到首页
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Modifying
    @Query("update ShoppingGoods set isRecommemdIndex=?1 where id = ?2")
    public void updateIsRecommemdIndex(Byte isRecommemdIndex,Long id);
    
    /**
     * 修改推荐到轮播
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @Modifying
    @Query("update ShoppingGoods set isRecommendCarousel=?1 where id = ?2")
    public void updateIsRecommendCarousel(Byte isRecommendCarousel,Long id);
    

    /**
     * Title:增加、减少库存和销量
     * <p>Description:
     * </p>
     * @author DeweiLi on 2017年1月10日
     * @param salesVolume
     * @param id
     * @return
     */
    @Modifying
    @Query("update ShoppingGoods set stock = stock + ?1, salesVolume = salesVolume + ?2 where id = ?3")
    public int updateStockAndSalesVolume(Integer stock, Integer salesVolume, Long id);
}
