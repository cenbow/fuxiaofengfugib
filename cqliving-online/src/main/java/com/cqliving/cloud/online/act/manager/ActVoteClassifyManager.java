package com.cqliving.cloud.online.act.manager;

import com.cqliving.cloud.online.act.domain.ActVoteClassify;
import com.cqliving.cloud.online.act.dto.ActVoteClassifyDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动投票分类表 Manager
 * Date: 2016-06-07 09:23:15
 * @author Code Generator
 */
public interface ActVoteClassifyManager extends EntityService<ActVoteClassify> {
	
	public ActVoteClassifyDto findByClassifyId(Long classifyId);
}
