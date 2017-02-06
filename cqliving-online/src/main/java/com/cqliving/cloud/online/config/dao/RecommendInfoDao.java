package com.cqliving.cloud.online.config.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.topic.domain.TopicInfo;

/**
 * recommend_info JPA Dao
 * Date: 2016-08-01 14:21:38
 * @author Code Generator
 */
public interface RecommendInfoDao extends EntityJpaDao<RecommendInfo, Long>, RecommendInfoDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update RecommendInfo set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	/**
	 * Title:根据sourceId修改状态
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月2日
	 * @param status
	 * @param sourceIds
	 * @return
	 */
	@Modifying
	@Query("update RecommendInfo set status = ?1 where sourceId in ?2")
	public int updateStatusBySourceId(Byte status,List<Long> sourceIds);

	/**
	 * <p>Description: 获取置顶话题列表</p>
	 * @author Tangtao on 2016年8月2日
	 * @param appId
	 * @param status
	 * @param sourceType
	 * @return
	 */
	@Query(""
			+ "select "
			+ "	b "
			+ "from "
			+ "	RecommendInfo a, "
			+ "	TopicInfo b "
			+ "where "
			+ "	a.sourceId = b.id "
			+ "	and a.appId = ?1 "
			+ "	and a.status = ?2 "
			+ "	and a.sourceType = ?3 "
			+ "	and b.status = 3 "
			+ "order by "
			+ "	a.sortNo, "
			+ "	a.updateTime desc")
	List<TopicInfo> getTopTopicList(Long appId, Byte status, Byte sourceType);
	
	/**
	 * <p>Description: 获取首页商情</p>
	 * @author Tangtao on 2016年12月7日
	 * @param appId
	 * @return
	 */
	@Query(""
			+ "select "
			+ "	b "
			+ "from "
			+ "	RecommendInfo a, "
			+ "	ShopInfo b "
			+ "where "
			+ "	a.sourceId = b.id "
			+ "	and a.appId = ?1 "
			+ "	and a.status = 3 "
			+ "	and a.sourceType = 3 "
			+ "	and b.status = 3 "
			+ "order by "
			+ "	a.sortNo, "
			+ "	a.updateTime desc")
	List<ShopInfo> getRecommendIndex(Long appId);

	/**
	 * Title:排序
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月3日
	 * @param id
	 * @param sortNo
	 * @return
	 */
	@Modifying
	@Query("update RecommendInfo set sortNo = ?2 where id = ?1")
	public int updateSort(Long id, Integer sortNo);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年8月17日
	 * @param ids
	 * @return
	 */
	@Query("FROM RecommendInfo WHERE id IN ?1")
	public List<RecommendInfo> getListByIds(List<Long> ids);
	
}