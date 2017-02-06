package com.cqliving.cloud.online.info.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InfoClassifyComment;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯栏目推荐表 JPA Dao
 * Date: 2016-04-15 09:44:31
 * @author Code Generator
 */
public interface InfoClassifyCommentDao extends EntityJpaDao<InfoClassifyComment, Long> {

	/**
	 * <p>Description: 修改状态</p>
	 * @author Tangtao on 2016年5月11日
	 * @param id
	 * @param status
	 */
	@Modifying
	@Query("update InfoClassifyComment set status = ?2 where id = ?1")
	void changeStatus(Long id, Byte status);

}
