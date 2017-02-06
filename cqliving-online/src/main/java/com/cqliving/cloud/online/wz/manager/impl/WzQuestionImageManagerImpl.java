package com.cqliving.cloud.online.wz.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.wz.dao.WzQuestionImageDao;
import com.cqliving.cloud.online.wz.domain.WzQuestionImage;
import com.cqliving.cloud.online.wz.manager.WzQuestionImageManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("wzQuestionImageManager")
public class WzQuestionImageManagerImpl extends EntityServiceImpl<WzQuestionImage, WzQuestionImageDao> implements WzQuestionImageManager {

    @Override
    public List<WzQuestionImage> getByQuestion(Long questionId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_questionId", questionId);
        Map<String, Boolean> sortMap = Maps.newHashMap();
        sortMap.put("sortNo", true);
        return getEntityDao().query(map, sortMap);
    }

    
}
