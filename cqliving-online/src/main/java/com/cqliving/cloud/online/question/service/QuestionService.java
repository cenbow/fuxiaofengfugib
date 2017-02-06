package com.cqliving.cloud.online.question.service;
import com.cqliving.tool.common.Response;

/**
 * 随手拍表 Service
 * Date: 2016-06-07 16:45:09
 * @author Code Generator
 */
public interface QuestionService {
    
    /**
     * <p>Description: 问题上报</p>
     * @author huxiaoping on 2016年11月14日
     * @param eventId
     * @param imgs
     * @param recDesc
     * @param address
     * @param coordX
     * @param coordY
     * @param reporterName
     * @param reporterMobile
     * @return
     */
	Response<Boolean> saveQuestion(String eventId, String appId, String imgs, String recDesc, String address, String coordX,String coordY,String reporterName,String reporterMobile,String token,String sessionId);
}
