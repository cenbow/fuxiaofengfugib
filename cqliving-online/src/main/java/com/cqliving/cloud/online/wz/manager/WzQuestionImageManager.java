package com.cqliving.cloud.online.wz.manager;

import java.util.List;

import com.cqliving.cloud.online.wz.domain.WzQuestionImage;
import com.cqliving.framework.common.service.EntityService;

/**
 * 问政问题图片表 Manager
 * Date: 2016-05-10 09:47:54
 * @author Code Generator
 */
public interface WzQuestionImageManager extends EntityService<WzQuestionImage> {
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月16日
     * @param id
     * @return
     */
    List<WzQuestionImage> getByQuestion(Long questionId);
}
