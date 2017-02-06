package com.cqliving.cloud.online.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 客户端 JPA Dao
 * Date: 2016-04-15 09:43:46
 * @author Code Generator
 */
public interface AppInfoDao extends EntityJpaDao<AppInfo, Long>,AppInfoDaoCustom {

	/**
	 * <p>Description: 获取客户端信息</p>
	 * @author Tangtao on 2016年4月27日
	 * @param code 客户端 code
	 * @return
	 */
	 @Query("from AppInfo where code = ?1 order by id desc")
	 List<AppInfo> getByCode(String code);

	 @Query("from AppInfo where id in (?1) order by id desc")
	 public List<AppInfo> findByIds(List<Long> ids);
	 
	 public List<AppInfo> findByAppDomain(String appDomain);
	 
	 public List<AppInfo> findByCmsDomain(String cmsdomain);
	 
	 @Query("from AppInfo where appDomain = ?1 and id != ?2 order by id desc")
	 public List<AppInfo> findByAppDomainNotId(String appDomain,Long id);
	 
	 @Query("from AppInfo where cmsDomain = ?1 and id != ?2 order by id desc")
	 public List<AppInfo> findByCmsDomainNotId(String cmsdomain,Long id);
	 
	 @Query("from AppInfo where cmsDomain = ?1 order by id desc")
     List<AppInfo> getByCmsDomain(String cmsDomain);
	 
	 @Query("from AppInfo where appDomain = ?1 order by id desc")
	 List<AppInfo> getByAppDomain(String appDomain);
	 
	 @Query("from AppInfo order by id desc")
     List<AppInfo> getAllIdDesc();
}
