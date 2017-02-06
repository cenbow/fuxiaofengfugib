package com.cqliving.cloud.online.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * APP七牛云服务配置 JPA Dao
 * Date: 2016-05-24 17:03:56
 * @author Code Generator
 */
public interface AppQiniuDao extends EntityJpaDao<AppQiniu, Long> {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update AppQiniu set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	
	@Query("from AppQiniu where appId = ?1 order by createTime desc")
	public List<AppQiniu> findByAppId(Long appId);
	
	@Query("from AppQiniu where isDefault = ?1 order by createTime desc")
	public List<AppQiniu> findByDefault(Byte isDefault);
}
