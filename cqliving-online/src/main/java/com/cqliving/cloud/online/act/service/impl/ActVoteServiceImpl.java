package com.cqliving.cloud.online.act.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActVote;
import com.cqliving.cloud.online.act.dto.ActVoteDto;
import com.cqliving.cloud.online.act.manager.ActVoteManager;
import com.cqliving.cloud.online.act.service.ActVoteService;
import com.cqliving.cloud.online.info.domain.InfoFile;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actVoteService")
@ServiceHandleMapping(managerClass = ActVoteManager.class)
public class ActVoteServiceImpl implements ActVoteService {

	//private static final Logger logger = LoggerFactory.getLogger(ActVoteServiceImpl.class);
	
	@Autowired
	private ActVoteManager actVoteManager;
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动投票表失败")})
	public Response<PageInfo<ActVote>> queryForPage(PageInfo<ActVote> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动投票表失败")})
	public Response<ActVote> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动投票表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动投票表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动投票表失败")})
	public Response<Void> save(ActVote actVote) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.service.ActVoteService#saveActVoteDto(com.cqliving.cloud.online.act.dto.ActVoteDto)
	 */
	@Override
	@ServiceMethodHandle
	public Response<ActVoteDto> saveActVoteDto(ActVoteDto actVoteDto,InfoFile infoFile) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.service.ActVoteService#findDetailById(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<ActVoteDto> findDetailById(Long id) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.service.ActVoteService#findByActInfoListId(java.lang.Long)
	 */
	@Override
	@ServiceMethodHandle
	public Response<ActVote> findByActInfoListId(Long actInfoListId) {
		return null;
	}
}
