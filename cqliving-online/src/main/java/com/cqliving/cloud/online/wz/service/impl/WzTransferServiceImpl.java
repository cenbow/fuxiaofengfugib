package com.cqliving.cloud.online.wz.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.wz.dao.WzUserDao;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.domain.WzTransfer;
import com.cqliving.cloud.online.wz.domain.WzTransferData;
import com.cqliving.cloud.online.wz.domain.WzTransferDto;
import com.cqliving.cloud.online.wz.manager.WzQuestionManager;
import com.cqliving.cloud.online.wz.manager.WzTransferManager;
import com.cqliving.cloud.online.wz.service.WzTransferService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("wzTransferService")
@ServiceHandleMapping(managerClass = WzTransferManager.class)
public class WzTransferServiceImpl implements WzTransferService {

	private static final Logger logger = LoggerFactory.getLogger(WzTransferServiceImpl.class);
	
	@Autowired
	private WzTransferManager wzTransferManager;
	@Autowired
	private WzQuestionManager wzQuestionManager;
	@Autowired
	private WzUserDao wzUserDao;
	
	@ServiceMethodHandle(managerMethodName="queryDtoForScrollPage",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询问题转交流程表失败")})
	public Response<PageInfo<WzTransferDto>> queryForPage(PageInfo<WzTransferDto> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询问题转交流程表失败")})
	public Response<WzTransfer> get(Long id) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="removes",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除问题转交流程表失败")})
	public Response<Void> delete(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="deleteLogic",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除问题转交流程表失败")})
	public Response<Void> deleteLogic(Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="updateStatus",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改状态失败")})
	public Response<Void> updateStatus(Byte status,Long... ids) {
		return null;
	}
	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存问题转交流程表失败")})
	public Response<Void> save(WzTransfer wzTransfer) {
		return null;
	}
	
	@Override
	@ServiceMethodHandle(managerMethodName="update",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="修改问题转交流程表失败")})
	public Response<Void> update(WzTransfer wzTransfer) {
	    return null;
	}

    @Override
    public Response<List<WzTransfer>> getCurrentTransferByUser(Long userId, Long questionId, List<Byte> status) {
        Response<List<WzTransfer>> rs = Response.newInstance();
        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("EQ_currentUserId", userId);
            map.put("EQ_questionId", questionId);
            map.put("IN_status", status);
            
            rs.setData(wzTransferManager.query(map, null));
        } catch (Exception e) {
            logger.error("获取我的问政列表失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取我的问政列表失败");
        }
        return rs;
    }
    
    
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Response<Void> replySave(Long id, String auditingDepartment, String content, Long userId) {
        Response<Void> rs = Response.newInstance();
        try {
            WzTransfer wzTransfer = wzTransferManager.get(id);
            if(wzTransfer == null || WzTransfer.STATUS99.equals(wzTransfer.getStatus())){
                throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
            }
            
            WzQuestion wzQuestion = wzQuestionManager.get(wzTransfer.getQuestionId());
            if(wzQuestion == null || !WzQuestion.STATUS4.equals(wzQuestion.getStatus())){
                throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
            }
            
            Date currentDate = new Date();
            
            wzTransfer.setStatus(WzTransfer.STATUS2);//状态修改为保存中
            wzTransfer.setReplayContent(content);
            wzTransferManager.update(wzTransfer);
            
            wzQuestion.setAuditingDepartment(auditingDepartment);//回复部门
            wzQuestion.setReplyContent(content);//回复内容
            wzQuestion.setReplyTime(currentDate);//回复时间
            wzQuestionManager.update(wzQuestion);
            
        } catch (BusinessException e) {
            logger.error("问政回复失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政回复失败");
            return rs;
        }
        return rs;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Response<Void> transferSave(Long id, Long currentUserId, String auditingDepartment, String description, Long userId, String nickName) {
        Response<Void> rs = Response.newInstance();
        try {
            
            Date currentDate = new Date();
            WzTransfer wzTransfer = wzTransferManager.get(id);
            if(wzTransfer == null){
                rs.setCode(ErrorCodes.FAILURE);
                rs.setMessage("问政不存在");
                return rs;
            }
            
            WzQuestion wzQuestion = wzQuestionManager.get(wzTransfer.getQuestionId());
            if(wzQuestion == null){
                rs.setCode(ErrorCodes.FAILURE);
                rs.setMessage("问政不存在");
                return rs;
            }
            
            wzQuestion.setTransferTime(currentDate);
            wzQuestion.setStatus(WzQuestion.STATUS4);
            wzQuestionManager.update(wzQuestion);
            
            
            WzTransfer newWzTransfer = new WzTransfer();
            newWzTransfer.setQuestionId(wzTransfer.getQuestionId());
            newWzTransfer.setStatus(WzTransfer.STATUS1);
            newWzTransfer.setCreateTime(new Date());
            newWzTransfer.setDescription(description);
            newWzTransfer.setCreator(nickName);
            newWzTransfer.setCreatorId(userId);
            newWzTransfer.setCurrentUserId(currentUserId);
            wzTransferManager.save(newWzTransfer);
            
            wzTransfer.setResult(WzTransfer.RESULT2);
            wzTransferManager.update(wzTransfer);
            
        } catch (BusinessException e) {
            logger.error("问政子帐号转交失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("问政子帐号转交失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政子帐号转交失败");
        }
        return rs;
    }

    @Override
    public Response<List<WzTransferData>> getByQuestion(Long id) {
        Response<List<WzTransferData>> rs = Response.newInstance();
        try {
            Map<String, Object> map = Maps.newHashMap();
            map.put("EQ_questionId", id);
            map.put("NOTEQ_status", WzTransfer.STATUS99);
            Map<String, Boolean> sortMap = Maps.newHashMap();
            sortMap.put("createTime", true);
            List<WzTransfer> list = wzTransferManager.query(map, sortMap);
            List<WzTransferData> listData = Lists.newArrayList();
            WzTransferData data = null;
            for(WzTransfer wtf : list){
            	data = new WzTransferData();
            	data.setId(wtf.getId());
            	data.setQuestionId(wtf.getQuestionId());
            	data.setStatus(wtf.getStatus());
            	data.setResult(wtf.getResult());
            	data.setCreateTime(wtf.getCreateTime());
            	data.setCreator(wtf.getCreator());
            	data.setCreatorId(wtf.getCreatorId());
            	data.setReplayContent(wtf.getReplayContent());
            	data.setDescription(wtf.getDescription());
            	data.setWzUser(wzUserDao.get(wtf.getCurrentUserId()));
            	listData.add(data);
            }
            rs.setData(listData);
        } catch (Exception e) {
            logger.error("问政查看失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政查看失败");
        }
        return rs;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Response<Void> submitBatchSave(Long[] ids) {
        Response<Void> rs = Response.newInstance();
        try {
            WzTransfer wzTransfer;
            WzQuestion wzQuestion;
            for(Long id : ids){
                 wzTransfer = wzTransferManager.get(id);
                 if(wzTransfer == null || WzTransfer.STATUS99.equals(wzTransfer.getStatus())){
                     throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
                 }
                 //只能提交保存中的
                 if(!wzTransfer.getStatus().equals(WzTransfer.STATUS2) && wzTransfer.getResult() != null){
                     continue ;
                 }
                 wzQuestion = wzQuestionManager.get(wzTransfer.getQuestionId());
                 if(wzQuestion == null || !WzQuestion.STATUS4.equals(wzQuestion.getStatus())){
                     throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
                 }
                 
                 wzTransfer.setStatus(WzTransfer.STATUS3);
                 wzTransfer.setResult(WzTransfer.RESULT3);
                 wzTransferManager.update(wzTransfer);
                 
                 wzQuestion.setStatus(WzQuestion.STATUS5);
                 wzQuestionManager.update(wzQuestion);
             }
        } catch (BusinessException e) {
            logger.error("问政子帐号批量提交失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("问政子帐号批量提交失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政子帐号批量提交失败");
        }
        return rs;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Response<Void> publishBatchSave(Long[] ids) {
        Response<Void> rs = Response.newInstance();
        try {
            WzTransfer wzTransfer;
            WzQuestion wzQuestion;
            for(Long id : ids){
                 wzTransfer = wzTransferManager.get(id);
                 if(wzTransfer == null || WzTransfer.STATUS99.equals(wzTransfer.getStatus())){
                     throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
                 }
                 //只能发布已处理且已提交
                 if(!wzTransfer.getStatus().equals(WzTransfer.STATUS2) && !wzTransfer.getResult().equals(WzTransfer.RESULT3)){
                     continue ;
                 }
                 wzQuestion = wzQuestionManager.get(wzTransfer.getQuestionId());
                 if(wzQuestion == null){
                     throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
                 }
                 wzTransfer.setStatus(WzTransfer.STATUS3);
                 wzTransfer.setResult(WzTransfer.RESULT4);
                 wzTransferManager.update(wzTransfer);
                 
                 wzQuestion.setStatus(WzQuestion.STATUS7);
                 wzQuestionManager.update(wzQuestion);
             }
        } catch (BusinessException e) {
            logger.error("问政子帐号批量发布失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("问政子帐号批量发布失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政子帐号批量发布失败");
        }
        return rs;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Response<Void> del(Long[] ids, boolean hasPublishAllowDel) {
        Response<Void> rs = Response.newInstance();
        try {
            WzTransfer wzTransfer;
            WzQuestion wzQuestion;
            List<WzTransfer> list = Lists.newArrayList();
            for(Long id : ids){
                wzTransfer = wzTransferManager.get(id);
                if(wzTransfer == null || wzTransfer.getStatus().equals(WzTransfer.STATUS99)){
                    throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
                }
                wzQuestion = wzQuestionManager.get(wzTransfer.getQuestionId());
                if(wzQuestion == null || wzQuestion.getStatus().equals(WzQuestion.STATUS99)){
                    throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
                }
                
                //发布状态下的问政不允许删除
                if(wzTransfer.getStatus().equals(WzTransfer.STATUS3) && wzTransfer.getResult().equals(WzTransfer.RESULT4) && hasPublishAllowDel){
                    throw new BusinessException(ErrorCodes.FAILURE, "不允许删除已经发布的问政。");
                }
                wzTransfer.setStatus(WzTransfer.STATUS99);
                list.add(wzTransfer);
            }
            for(WzTransfer wzT : list){
                wzTransferManager.update(wzT);
            }
        } catch (BusinessException e) {
            logger.error("问政（子帐号）删除失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("问政（子帐号）删除失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政（子帐号）删除失败");
        }
        return rs;
    }

	@Override
	public Response<Void> saveAfterPublisReply(Long id, String auditingDepartment, String content, Long updatorId, String updator) {
		Response<Void> rs = Response.newInstance();
		try {
			wzTransferManager.saveAfterPublisReply(id, auditingDepartment, content, updatorId, updator);
		} catch (BusinessException e) {
            logger.error("问政（子帐号）保存发布后的回复内容失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("问政（子帐号）保存发布后的回复内容失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政（子帐号）保存发布后的回复内容失败");
        }
		return rs;
	}
}
