/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.cloud.online.act.dao;

import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.cloud.online.act.dto.ActVoteDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年6月25日
 */
public interface ActVoteDaoCustom {

	public ActVoteDto findDetailById(Long id);
	
	public ActVote findByVoteClassifyId(Long voteClassifyId);
}
