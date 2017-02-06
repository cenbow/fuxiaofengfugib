package com.cqliving.cloud.online.question.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.question.manager.QuestionManager;
import com.cqliving.cloud.online.question.service.QuestionService;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;

@Service("questionService")
@ServiceHandleMapping(managerClass = QuestionManager.class)
public class QuestionServiceImpl implements QuestionService {
    private static final Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
    @Autowired
    private QuestionManager questionManager;
    
    @ServiceMethodHandle(managerMethodName="saveQuestion",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="问题上报失败")})
    @Override
    public Response<Boolean> saveQuestion(String eventId, String appId, String imgs, String recDesc, String address, String coordX,
            String coordY, String reporterName, String reporterMobile,String token,String sessionId) {
        Response<Boolean> response = Response.newInstance();
        try {
            questionManager.saveQuestion(eventId, appId, imgs, recDesc, address, coordX, coordY, reporterName, reporterMobile, token, sessionId);
            response.setData(true);
            response.setMessage("问题上报成功");
        } catch (BusinessException e) {
            logger.error("问题上报失败：" + e.getMessage(), e);
            response.setCode(e.getErrorCode());
            response.setMessage(e.getMessage());
            response.setData(false);
        } catch (Exception e) {
            logger.error("问题上报失败", e);
            response.setCode(ErrorCodes.FAILURE);
            response.setMessage("问题上报失败");
            response.setData(false);
        }
        return response;
    }
}