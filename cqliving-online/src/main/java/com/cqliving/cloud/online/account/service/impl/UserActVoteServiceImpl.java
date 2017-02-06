package com.cqliving.cloud.online.account.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserActList;
import com.cqliving.cloud.online.account.domain.UserActVote;
import com.cqliving.cloud.online.account.manager.UserActVoteManager;
import com.cqliving.cloud.online.account.service.UserActVoteService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("userActVoteService")
@ServiceHandleMapping(managerClass = UserActVoteManager.class)
public class UserActVoteServiceImpl implements UserActVoteService {

	//private static final Logger logger = LoggerFactory.getLogger(UserActVoteServiceImpl.class);
	
	@Autowired
	private UserActVoteManager userActVoteManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询用户_活动投票表失败")})
	public Response<PageInfo<UserActVote>> queryForPage(PageInfo<UserActVote> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询用户_活动投票表失败")})
	public Response<UserActVote> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除用户_活动投票表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存用户_活动投票表失败")})
	public Response<Void> save(UserActVote userActVote) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserActVoteService#saveUserVote(com.cqliving.cloud.online.account.domain.UserActVote, java.lang.String, java.lang.String)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Void> saveUserVote(UserActVote userActVote,UserActList userActList,Long[] itemIds, String sessionCode, String token) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserActVoteService#findJoinTotalByVoteId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Long> findJoinTotalByVoteId(Long voteId) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.account.service.UserActVoteService#surplusVote(java.lang.String, java.lang.String)
	 */
	@Override
	@ServiceMethodHandle
	public Response<Integer> surplusVote(String sessionCode, String token,Long voteClassifyId) {
		return null;
	}
}
