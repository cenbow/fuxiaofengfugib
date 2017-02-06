package com.cqliving.cloud.online.wz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.account.domain.UserOtherAccount;
import com.cqliving.cloud.online.account.domain.UserSession;
import com.cqliving.cloud.online.account.manager.UserAccountManager;
import com.cqliving.cloud.online.account.manager.UserSessionManager;
import com.cqliving.cloud.online.interfacc.dto.CommonListResult;
import com.cqliving.cloud.online.interfacc.dto.LoginResult;
import com.cqliving.cloud.online.interfacc.dto.WzCollectInfoData;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionDetailResult;
import com.cqliving.cloud.online.interfacc.dto.WzQuestionResult;
import com.cqliving.cloud.online.wz.domain.WzAppAuthority;
import com.cqliving.cloud.online.wz.domain.WzAuthority;
import com.cqliving.cloud.online.wz.domain.WzCollectInfo;
import com.cqliving.cloud.online.wz.domain.WzQuestion;
import com.cqliving.cloud.online.wz.domain.WzTransfer;
import com.cqliving.cloud.online.wz.dto.WzQuestionData;
import com.cqliving.cloud.online.wz.dto.WzQuestionDto;
import com.cqliving.cloud.online.wz.dto.WzQuestionExcelDownload;
import com.cqliving.cloud.online.wz.manager.WzAppAuthorityManager;
import com.cqliving.cloud.online.wz.manager.WzAuthorityManager;
import com.cqliving.cloud.online.wz.manager.WzCollectInfoManager;
import com.cqliving.cloud.online.wz.manager.WzQuestionManager;
import com.cqliving.cloud.online.wz.manager.WzTransferManager;
import com.cqliving.cloud.online.wz.service.WzQuestionService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("wzQuestionService")
@ServiceHandleMapping(managerClass = WzQuestionManager.class)
public class WzQuestionServiceImpl implements WzQuestionService {

    private static final Logger logger = LoggerFactory.getLogger(WzQuestionServiceImpl.class);

    @Autowired
    private WzQuestionManager wzQuestionManager;
    @Autowired
    private UserSessionManager userSessionManager;
    @Autowired
    private WzAuthorityManager wzAuthorityManager;
    @Autowired
    private WzAppAuthorityManager wzAppAuthorityManager;
    @Autowired
    private WzCollectInfoManager wzCollectInfoManager;
    @Autowired
    private WzTransferManager wzTransferManager;
    @Autowired
    private UserAccountManager userAccountManager;

    @ServiceMethodHandle(managerMethodName = "query", exceptionClass = {
            @ExceptionHandle(exception = BusinessException.class, errorCode = ErrorCodes.FAILURE, errorMsg = "分页查询问政问题表失败") })
    public Response<PageInfo<WzQuestion>> queryForPage(PageInfo<WzQuestion> pageInfo, Map<String, Object> map,
            Map<String, Boolean> orderMap) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName = "get", exceptionClass = {
            @ExceptionHandle(exception = BusinessException.class, errorCode = ErrorCodes.FAILURE, errorMsg = "查询问政问题表失败") })
    public Response<WzQuestion> get(Long id) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName = "removes", exceptionClass = {
            @ExceptionHandle(exception = BusinessException.class, errorCode = ErrorCodes.FAILURE, errorMsg = "删除问政问题表失败") })
    public Response<Void> delete(Long... ids) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName = "deleteLogic", exceptionClass = {
            @ExceptionHandle(exception = BusinessException.class, errorCode = ErrorCodes.FAILURE, errorMsg = "删除问政问题表失败") })
    public Response<Void> deleteLogic(Long... ids) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName = "updateStatus", exceptionClass = {
            @ExceptionHandle(exception = BusinessException.class, errorCode = ErrorCodes.FAILURE, errorMsg = "修改状态失败") })
    public Response<Void> updateStatus(Byte status, Long... ids) {
        return null;
    }

    @Override
    @ServiceMethodHandle(managerMethodName = "save", exceptionClass = {
            @ExceptionHandle(exception = BusinessException.class, errorCode = ErrorCodes.FAILURE, errorMsg = "保存问政问题表失败") })
    public Response<Void> save(WzQuestion wzQuestion) {
        return null;
    }

    @Override
    public Response<List<WzQuestionData>> queryScrollPage(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page) {
        Response<List<WzQuestionData>> rs = Response.newInstance();
        try {
            ScrollPage<WzQuestionDto> scrollPage = wzQuestionManager.queryScrollPage(conditions, page);
            List<WzQuestionDto> list = scrollPage.getPageResults();
            List<WzQuestionData> resultList = Lists.newArrayList();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            WzQuestionData wzQuestionData = null;
            for(WzQuestionDto dto : list){
                wzQuestionData = new WzQuestionData();
                wzQuestionData.setId(dto.getId());
                wzQuestionData.setReplyCount(dto.getReplyCount() == null || dto.getReplyCount() < 1 ? 0 : dto.getReplyCount());
                wzQuestionData.setViewCount(dto.getViewCount() == null || dto.getViewCount() < 1 ? 0 : dto.getViewCount());
                wzQuestionData.setCreateTime(sdf.format(dto.getCreateTime()));
                wzQuestionData.setTitle(dto.getTitle());
                wzQuestionData.setContent(dto.getContent());
                
                wzQuestionData.setStatus(WzQuestion.allStatussFront.get(dto.getStatus()));
                resultList.add(wzQuestionData);
            }
            rs.setData(resultList);
        } catch (Exception e) {
            logger.error("获取问政列表失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取问政列表失败");
        }
        return rs;
    }

    @Override
    public Response<List<WzQuestionDto>> getMyQuestionByPage(Long appId, String sessionId, String token, String lastId, String query) {
        Response<List<WzQuestionDto>> rs = Response.newInstance();
        try {
            rs.setData(wzQuestionManager.getMyQuestionByPage(appId, sessionId, token, lastId, query));
        } catch (Exception e) {
            logger.error("获取我的问政列表失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取我的问政列表失败");
        }
        return rs;
    }

    @Override
    public Response<WzQuestionDto> getQuestionDetails(Long questionId) {
        Response<WzQuestionDto> rs = Response.newInstance();
        try {
            WzQuestionDto dto = wzQuestionManager.getQuestionDetails(questionId, true);
            rs.setData(dto);
        } catch (BusinessException e) {
            logger.error("获取问政详情失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("获取问政详情失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取问政详情失败");
        }
        return rs;
    }

    @Override
    public Response<WzQuestionDto> getMyQuestionDetails(Long appId, String sessionId, String token, Long questionId) {
        Response<WzQuestionDto> rs = Response.newInstance();
        try {
            UserSession userSession = userSessionManager.get(sessionId, token);
            if (userSession == null) {
                rs.setCode(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getCode());
                rs.setMessage(ErrorCodes.CommonErrorEnum.USER_NOT_EXIST.getDesc());
                return rs;
            }
            rs.setData(wzQuestionManager.getQuestionDetails(questionId, false));
        } catch (BusinessException e) {
            logger.error("获取问政详情失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("获取问政详情失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取问政详情失败");
        }
        return rs;
    }

    @Override
    public Response<Integer> saveOrModify(Long appId, String sessionId, String token, WzQuestion params, String[] photos, Long[] collectId, String[] collectValue) {
        Response<Integer> rs = Response.newInstance();
        try {
            rs.setData(wzQuestionManager.saveOrModify(appId, sessionId, token, params, photos, collectId, collectValue));
        } catch (BusinessException e) {
            logger.error("问政保存失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("问政保存失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政保存失败");
        }
        return rs;
    }

    @Override
    public Response<Void> deleteMyQuestion(Long appId, String sessionId, String token, Long questionId) {
        Response<Void> rs = Response.newInstance();
        try {
            wzQuestionManager.deleteMyQuestion(appId, sessionId, token, questionId);
        } catch (BusinessException e) {
            logger.error("我的问政删除失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("我的问政删除失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("我的问政删除失败");
        }
        return rs;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Response<Void> check(Long appId, Long userId, String userName, Long[] questionIds, Byte status, String content, String nickName) {
        Response<Void> rs = Response.newInstance();
        try {
            WzQuestion wzQuestion;
            Date currentDate = new Date();
            
            //查询问政审核是否自动回复
            String replyContent = this.getAutoReplyContent(appId);
            for(Long id : questionIds){
                wzQuestion = wzQuestionManager.get(id);
                if(wzQuestion == null || !WzQuestion.STATUS2.equals(wzQuestion.getStatus())){
                    throw new BusinessException(-2, "问政不存在");
                }
                //只处理未审核的数据
                if(!wzQuestion.getStatus().equals(WzQuestion.STATUS2)){
                    continue;
                }
                
                wzQuestion.setAuditingId(userId);
                wzQuestion.setAuditingtor(userName);
                wzQuestion.setAuditingTime(new Date());
                wzQuestion.setStatus(status);
                wzQuestion.setAuditingDepartment(nickName);
                if(status.equals(WzQuestion.STATUS3)){
                    wzQuestion.setReplyContent(content);
                    wzQuestion.setReplyTime(currentDate);
                    //自动回复 自动回复后问政的流程就算是走完了。
                    if(StringUtils.isNotBlank(replyContent)){
                        wzQuestion.setReplyContent(replyContent); 
                        wzQuestion.setStatus(WzQuestion.STATUS7);
                    }
                }else if(status.equals(WzQuestion.STATUS_1)){
                    wzQuestion.setRejectContent(content);//拒绝内容
                    wzQuestion.setReplyContent(content);
                }
                wzQuestionManager.update(wzQuestion);
            }
        } catch (BusinessException e) {
            logger.error("审核失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("审核失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("审核失败");
        }
        return rs;
    }

    @Override
    public String getAutoReplyContent(Long appId) {
        String replyContent = "";
        WzAuthority wzAuthority = wzAuthorityManager.getByNameOne(WzAuthority.NAME_AUTO_REPLY);
        if(wzAuthority != null){
            WzAppAuthority wzAppAuthority = wzAppAuthorityManager.getByAuthorityId(appId, wzAuthority.getId());
            if(wzAppAuthority != null && wzAppAuthority.getValue().equals(WzAppAuthority.VALUE1)){
                List<WzCollectInfo> list = wzCollectInfoManager.getByAppAuthority(appId, wzAppAuthority.getId());
                if(list.size() > 0){
                    replyContent = list.get(0).getName();
                }
            }
        }
        return replyContent;
    }

    @Override
    public Response<Void> update(WzQuestion wzQuestion) {
        Response<Void> rs = Response.newInstance();
        try {
            wzQuestionManager.update(wzQuestion);
        } catch (Exception e) {
            logger.error("问政修改失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政修改失败");
        }
        return rs;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Response<Void> transferSave(Long questionId, Long currentUserId, String auditingDepartment, String description, Long userId, String nickName) {
        Response<Void> rs = Response.newInstance();
        try {
            
            Date currentDate = new Date();
            WzQuestion wzQuestion = wzQuestionManager.get(questionId);
            if(wzQuestion == null || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
                rs.setCode(ErrorCodes.FAILURE);
                rs.setMessage("问政不存在");
                return rs;
            }
            wzQuestion.setTransferTime(currentDate);
            wzQuestion.setStatus(WzQuestion.STATUS4);
            wzQuestionManager.update(wzQuestion);
        
            WzTransfer wzTransfer = new WzTransfer();
            wzTransfer.setQuestionId(questionId);
            wzTransfer.setStatus(WzTransfer.STATUS1);
            wzTransfer.setCreateTime(new Date());
            wzTransfer.setDescription(description);
            wzTransfer.setCreator(nickName);
            wzTransfer.setCreatorId(userId);
            wzTransfer.setCurrentUserId(currentUserId);
            wzTransferManager.save(wzTransfer);
        } catch (BusinessException e) {
            logger.error("问政（"+questionId+"）回复转交：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("问政转交失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政转交失败");
        }
        return rs;
    }
    
    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Response<Void> replySave(Long appId, Long questionId, String auditingDepartment, String content, Long userId) {
        Response<Void> rs = Response.newInstance();
        try {
            WzQuestion wzQuestion = wzQuestionManager.get(questionId);
            if(wzQuestion == null || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
                throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
            }
            Date currentDate = new Date();
            
            List<Byte> statusList = Lists.newArrayList();
            statusList.add(WzTransfer.STATUS1);
            statusList.add(WzTransfer.STATUS2);
            List<WzTransfer> list = wzTransferManager.getCurrentTransferByUser(userId, questionId, statusList);
            if(list != null && list.size() > 0){//是子帐号回复
                WzTransfer wzTransfer = list.get(0);
                wzTransfer.setStatus(WzTransfer.STATUS2);//状态修改为保存中
                wzTransfer.setReplayContent(content);
                wzTransferManager.update(wzTransfer);
            }else{//由管理员直接回复
                wzQuestion.setStatus(WzQuestion.STATUS5);
            }
            wzQuestion.setAuditingDepartment(auditingDepartment);//回复部门
            wzQuestion.setReplyContent(content);//回复内容
            wzQuestion.setReplyTime(currentDate);//回复时间
            wzQuestionManager.update(wzQuestion);
            
        } catch (BusinessException e) {
            logger.error("问政（"+questionId+"）回复失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("转交失败");
            return rs;
        }
        return rs;
    }

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public Response<Void> publishSave(Long[] ids) {
        Response<Void> rs = Response.newInstance();
        try {
            WzQuestion wzQuestion;
            List<WzQuestion> wzQuestionList = Lists.newArrayList();
            List<WzTransfer> wzTransferList = Lists.newArrayList();
            List<WzTransfer> tmp;
            Map<String, Object> map = Maps.newHashMap();
            map.put("EQ_status", WzTransfer.STATUS3);//只有子帐号改成已处理后，总帐号才能发布这个问政
            map.put("EQ_result", WzTransfer.RESULT3);
            for(Long id : ids){
                wzQuestion = wzQuestionManager.get(id);
                if(wzQuestion == null || WzQuestion.STATUS99.equals(wzQuestion.getStatus())){
                    throw new BusinessException(ErrorCodes.FAILURE, "问政不存在");
                }
                //只处理带发布状态的问政
                if(!WzQuestion.STATUS5.equals(wzQuestion.getStatus())){
                    continue ;
                }
                
                wzQuestion.setStatus(WzQuestion.STATUS7);
                wzQuestionList.add(wzQuestion);
                
                //把转交给别人的状态处理成已发布
                map.put("EQ_questionId", id);
                tmp = wzTransferManager.query(map, null);
                if(CollectionUtils.isNotEmpty(tmp)){
                    for(WzTransfer wzTransfer : tmp){
                        wzTransfer.setStatus(WzTransfer.STATUS3);
                        wzTransfer.setResult(WzTransfer.RESULT4);
                        wzTransferList.add(wzTransfer);
                    }
                }
            }
            for(WzQuestion wzq : wzQuestionList){
                wzQuestionManager.update(wzq);
            }
            for(WzTransfer wzt : wzTransferList){
                wzTransferManager.update(wzt);
            }
        } catch (BusinessException e) {
            logger.error("问政发布失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政发布失败");
            return rs;
        }
        return rs;
    }

    @Override
    public Response<UserSession> loginAuth(Long appId, String sessionId, String token, String userName, String phone, String headUrl, String openId) {
        Response<UserSession> rs = Response.newInstance();
        try {
        	LoginResult loginResult = null;
        	UserSession userSession = null;
        	if(StringUtils.isNotBlank(openId)){//第三方帐号登录
        		openId = appId + "_" + openId;
        		loginResult = userAccountManager.login(appId, sessionId, null, null, null, null, null, openId, UserOtherAccount.TYPE10, userName, headUrl, "");
        		userSession = userSessionManager.getByToken(loginResult.getToken());
        		
        	}
        	if(userSession == null){//第三方游客登录
        		userSession = userSessionManager.getTourist(sessionId);
        		if(userSession == null) {
        			userAccountManager.createTourist(appId, sessionId);
        			userSession = userSessionManager.getTourist(sessionId);
        		}
        	}
            rs.setData(userSession);
        } catch (BusinessException e) {
            logger.error("问政授权失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政授权失败");
        }
        return rs;
    }

	@Override
	public Response<Void> delReply(Long replyId) {
		Response<Void> rs = Response.newInstance();
		try {
			wzQuestionManager.delReply(replyId);
		} catch (BusinessException e) {
            logger.error("问政评论删除失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政评论删除失败");
        }
		return rs;
	}

	@Override
	public Response<List<WzCollectInfo>> addData(Long appId, String sessionId, String token) {
		Response<List<WzCollectInfo>> rs = Response.newInstance();
		try {
			rs.setData(wzQuestionManager.addData(appId, sessionId, token));
		} catch (BusinessException e) {
            logger.error("我要问政信息获取失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
        	logger.info(e.getMessage());
        	rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("我要问政信息获取失败");
		}
		return rs;
	}

	@Override
	public Response<CommonListResult<WzQuestionData>> getPageByWzQuestion(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page) {
		Response<CommonListResult<WzQuestionData>> rs = Response.newInstance();
		try {
			rs.setData(wzQuestionManager.getPageByWzQuestion(conditions, page));
		} catch (BusinessException e) {
            logger.error("获取问政列表失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
        	logger.info(e.getMessage());
        	rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获取问政列表失败");
		}
		return rs;
	}
	
	@Override
	public Response<CommonListResult<WzQuestionData>> getPageByWzQuestionNew(Map<String, Object> conditions, ScrollPage<WzQuestionDto> page) {
		Response<CommonListResult<WzQuestionData>> rs = Response.newInstance();
		try {
			rs.setData(wzQuestionManager.getPageByWzQuestionNew(conditions, page));
		} catch (BusinessException e) {
			logger.error("获取问政列表失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获取问政列表失败");
		}
		return rs;
	}

	@Override
	public Response<Integer> saveI(Long appId, String sessionId, String token, WzQuestion params, String photos, String collectIds, String collectValues) {
        Response<Integer> rs = Response.newInstance();
        try {
            rs.setData(wzQuestionManager.saveI(appId, sessionId, token, params, photos, collectIds, collectValues));
        } catch (BusinessException e) {
            logger.error("问政保存失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("问政保存失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("问政保存失败");
        }
        return rs;
    }

	@Override
	public Response<WzQuestionResult> getDetails(Long questionId) {
        Response<WzQuestionResult> rs = Response.newInstance();
        try {
            rs.setData(wzQuestionManager.getDetails(questionId));
        } catch (BusinessException e) {
            logger.error("获得问政详情失败：" + e.getMessage(), e);
            rs.setCode(e.getErrorCode());
            rs.setMessage(e.getMessage());
        } catch (Exception e) {
            logger.error("获得问政详情失败：" + e.getMessage(), e);
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("获得问政详情失败");
        }
        return rs;
    }
	
	@Override
	public Response<WzQuestionDetailResult> getDetailsUseModify(Long questionId) {
		Response<WzQuestionDetailResult> rs = Response.newInstance();
		try {
			rs.setData(wzQuestionManager.getDetailsUseModify(questionId));
		} catch (BusinessException e) {
			logger.error("获得问政详情失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获得问政详情失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获得问政详情失败");
		}
		return rs;
	}

	@Override
	public Response<List<WzCollectInfoData>> getUserCollectInfo(Long appId, Long questionId) {
		Response<List<WzCollectInfoData>> rs = Response.newInstance();
		try {
			rs.setData(wzCollectInfoManager.getUserCollectInfo(appId, questionId));
		} catch (BusinessException e) {
			logger.error("获得用户收集信息失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获得用户收集信息失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("获得用户收集信息失败");
		}
		return rs;
	}

	@Override
	public Response<List<WzQuestionExcelDownload>> excelDownload(Map<String, Object> searchMap, Map<String, Boolean> sortMap) {
		Response<List<WzQuestionExcelDownload>> rs = Response.newInstance();
		try {
			rs.setData(wzQuestionManager.excelDownload(searchMap, sortMap));
		} catch (BusinessException e) {
			logger.error("问政列表Excel导出失败：" + e.getMessage(), e);
			rs.setCode(e.getErrorCode());
			rs.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("问政列表Excel导出失败：" + e.getMessage(), e);
			rs.setCode(ErrorCodes.FAILURE);
			rs.setMessage("问政列表Excel导出失败");
		}
		return rs;
	}

}
