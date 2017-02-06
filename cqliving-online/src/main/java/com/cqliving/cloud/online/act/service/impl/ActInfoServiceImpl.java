package com.cqliving.cloud.online.act.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.dto.ActInfoDto;
import com.cqliving.cloud.online.act.manager.ActInfoManager;
import com.cqliving.cloud.online.act.service.ActInfoService;
import com.cqliving.cloud.online.interfacc.dto.ActExportDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("actInfoService")
@ServiceHandleMapping(managerClass = ActInfoManager.class)
public class ActInfoServiceImpl implements ActInfoService {
	
	@Autowired
	private ActInfoManager actInfoManager;

	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询活动表失败")})
	public Response<PageInfo<ActInfo>> queryForPage(PageInfo<ActInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动表失败")})
	public Response<ActInfo> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除活动表失败")})
	public Response<Void> deleteLogic(String updator,Long updateUserId,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,String updator,Long updateUserId,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存活动表失败")})
	public Response<Void> save(ActInfo actInfo) {
		return null;
	}
	
	/* (non-Javadoc)
     * @see com.cqliving.cloud.online.act.service.ActInfoService#findById(java.lang.Long)
     */
    @Override
    @ServiceMethodHandle
    public Response<ActInfoDto> findById(Long id) {
        return null;
    }
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月7日上午10:37:12
     */
    @Override
    @ServiceMethodHandle(managerMethodName="queryPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询活动列表失败")})
    public Response<PageInfo<ActInfoDto>> queryPage(PageInfo<ActInfoDto> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return null;
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.act.service.ActInfoService#saveOrUpdate(com.cqliving.cloud.online.act.dto.ActInfoDto)
	 */
	@Override
	@ServiceMethodHandle
	public Response<ActInfoDto> saveOrUpdate(ActInfoDto actInfoDto) {
		return null;
	}

	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    @Override
    @ServiceMethodHandle(managerMethodName="updateSortNo",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改排序号失败")})
    public Response<Void> updateSortNo(Integer sortNo, String updator, Long updateUserId, Long... ids) {
        return null;
    }

	@Override
	@ServiceMethodHandle(managerMethodName="findByActTest",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="获得活动信息失败")})
    public Response<ActInfoDto> findByActTest(Long actInfoListId) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="findByActInfoListId",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="获得活动信息失败")})
	public Response<ActInfoDto> findByActInfoListId(Long actInfoListId) {
		return null;
	}
	
	/**
     * Title:推荐到首页
     * <p>Description:</p>
     * @author huxiaoping on 2016年7月14日
     * @param ActInfo
     * @return
     */
	@Override
    @ServiceMethodHandle(managerMethodName="recommend",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="推荐到首页失败")})
    public Response<Void> recommend(ActInfo act) {
        return null;
    }

	/**
     * Title:取消推荐到首页
     * <p>Description:</p>
     * @author huxiaoping on 2016年7月14日
     * @param updator
     * @param updateUserId
     * @param ids
     * @return
     */
	@Override
    @ServiceMethodHandle(managerMethodName="cancelRecommend",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="取消推荐到首页失败")})
    public Response<Void> cancelRecommend(String updator, Long updateUserId, Long id) {
        return null;
    }
    /**
     *  Title:数据导出
     * <p>Description:</p>
     * @author FangHuiLin on 2016年12月7日
     * @param classfyId
     * @return
     */
	@Override
	@ServiceMethodHandle(managerMethodName="queryExportList",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询投票活动失败")})
	public Response<List<ActExportDto>> actExportList(Long classfyId) {
		return null;
	}
}
