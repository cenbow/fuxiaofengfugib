package com.org.weixin.module.zjchj.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.zjchj.domain.ZjchjUserGoodsInfo;

/**
 * 用户点亮菜品表 JPA Dao
 * Date: 2016-09-26 15:18:51
 * @author Code Generator
 */
public interface ZjchjUserGoodsInfoDao extends EntityJpaDao<ZjchjUserGoodsInfo, Long>, ZjchjUserGoodsInfoDaoCustom {

	/**
	 * <p>Description: 获取用户已点亮的菜品</p>
	 * @author Tangtao on 2016年9月26日
	 * @param userId
	 * @return
	 */
	@Query("from ZjchjUserGoodsInfo where userId = ?1 order by id")
	List<ZjchjUserGoodsInfo> getByUserId(Long userId);

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年11月2日
	 * @return
	 */
	@Query("select count(distinct userId) from ZjchjUserGoodsInfo")
	Long getTotalPeople();

}