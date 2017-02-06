package com.cqliving.cloud.online.wz.manager;

import java.util.List;

import com.cqliving.cloud.online.wz.domain.WzQuestionCollectInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 问题信息收集表 Manager
 * Date: 2016-05-10 09:47:41
 * @author Code Generator
 */
public interface WzQuestionCollectInfoManager extends EntityService<WzQuestionCollectInfo> {
    
    /**
     * 
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月13日
     * @param questionId
     * @param collectId
     * @return
     */
    List<WzQuestionCollectInfo> getInfoByCollect(Long questionId, Long collectId);
}
