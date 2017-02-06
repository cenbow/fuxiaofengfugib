package com.cqliving.cloud.online.act.manager;

import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.cloud.online.act.dto.ActVoteDto;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动投票表 Manager
 * Date: 2016-06-07 09:23:04
 * @author Code Generator
 */
public interface ActVoteManager extends EntityService<ActVote> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	//修改及保存投票
	public ActVoteDto saveActVoteDto(ActVoteDto actVoteDto,InfoFile infoFile);
	
	public ActVoteDto findDetailById(Long id);
	
	public ActVote findByActInfoListId(Long actInfoListId);
}
