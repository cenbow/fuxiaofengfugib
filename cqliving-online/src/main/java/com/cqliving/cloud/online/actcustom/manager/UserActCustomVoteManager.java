package com.cqliving.cloud.online.actcustom.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.actcustom.domain.UserActCustomVote;

/**
 * 用户自定义投票活动表 Manager
 * Date: 2017-01-03 10:31:16
 * @author Code Generator
 */
public interface UserActCustomVoteManager extends EntityService<UserActCustomVote> {

	Byte addActeVote(String actQrcodeCode,String token, String sessionId, UserActCustomVote userActCustomVote);
}
