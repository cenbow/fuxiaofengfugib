package com.cqliving.cloud.online.actcustom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cqliving.cloud.online.actcustom.domain.ActCustomVoteItem;
import com.cqliving.framework.common.dao.jpa.EntityJpaDao;

/**
 * 自定义活动投票选项表 JPA Dao
 * Date: 2017-01-03 10:19:03
 * @author Code Generator
 */
public interface ActCustomVoteItemDao extends EntityJpaDao<ActCustomVoteItem, Long>,ActCustomVoteItemDaoCustom {
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ActCustomVoteItem set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
	@Modifying
    @Query("from ActCustomVoteItem where actQrcodeCode=?1 and status=3")
    public List<ActCustomVoteItem> getColumnsListByQrcode(String actCode);
	@Modifying
    @Query("update ActCustomVoteItem set actValue =actValue+1 where id =?1")
	public int updateActValue(Long id);
}
