package com.cqliving.cloud.online.question.manager;

import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 江北城管系统接入需求
 * Date: 2016-06-07 16:45:09
 * @author Code Generator
 */
public interface QuestionManager extends EntityService<ShootInfo> {
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
    public Boolean saveQuestion(String eventId, String appId, String imgs, String recDesc, String address, String coordX,String coordY,String reporterName,String reporterMobile,String token,String sessionId) throws Exception;
}
