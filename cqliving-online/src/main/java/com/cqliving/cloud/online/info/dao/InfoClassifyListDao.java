package com.cqliving.cloud.online.info.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.info.domain.InfoClassifyList;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 资讯栏目列表图片表 JPA Dao
 * Date: 2016-04-15 09:44:40
 * @author Code Generator
 */
public interface InfoClassifyListDao extends EntityJpaDao<InfoClassifyList, Long> {

	public List<InfoClassifyList> findByInformationIdOrderBySortNoAsc(Long infoId);

	/**
	 * <p>Description: 获取列表图片</p>
	 * @author Tangtao on 2016年5月12日
	 * @param id
	 * @return
	 */
	@Query("from InfoClassifyList where classifyId = ?1 order by sortNo")
	List<InfoClassifyList> getByInfoClassify(Long id);
	
	@Modifying
	@Query(value="delete from InfoClassifyList where classifyId = ?1")
	public int deleteClassifyList(Long infoClassifyId);
}
