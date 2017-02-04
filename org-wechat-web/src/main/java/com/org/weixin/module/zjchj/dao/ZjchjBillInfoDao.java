package com.org.weixin.module.zjchj.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.wechat.framework.dao.jpa.EntityJpaDao;

import com.org.weixin.module.zjchj.domain.ZjchjBillInfo;

/**
 * 订单表 JPA Dao
 * Date: 2016-09-26 15:18:38
 * @author Code Generator
 */
public interface ZjchjBillInfoDao extends EntityJpaDao<ZjchjBillInfo, Long>, ZjchjBillInfoDaoCustom {

	/**
	 * <p>Description: 获取业务流水号对应的菜品集合</p>
	 * @author Tangtao on 2016年9月26日
	 * @param serialid
	 * @return
	 */
	@Query("from ZjchjBillInfo where serialId = ?1 order by id desc")
	List<ZjchjBillInfo> getBySerialid(String serialid);

}