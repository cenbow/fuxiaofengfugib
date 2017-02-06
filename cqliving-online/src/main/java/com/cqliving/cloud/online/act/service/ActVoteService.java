package com.cqliving.cloud.online.act.service;

import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.cloud.online.act.dto.ActVoteDto;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动投票表 Service
 * Date: 2016-06-07 09:23:04
 * @author Code Generator
 */
public interface ActVoteService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActVote>> queryForPage(PageInfo<ActVote> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActVote> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	public Response<Void> save(ActVote domain);
	/** @author Code Generator *****end*****/

	//修改保存投票
	public Response<ActVoteDto> saveActVoteDto(ActVoteDto actVoteDto,InfoFile infoFile);
	
	public Response<ActVoteDto> findDetailById(Long id);
	//根据活动集合ID查找投票
	public Response<ActVote> findByActInfoListId(Long actInfoListId);
}
