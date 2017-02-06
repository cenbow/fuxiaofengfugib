package com.cqliving.cloud.online.wz.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.wz.dao.WzQuestionCollectInfoDao;
import com.cqliving.cloud.online.wz.domain.WzQuestionCollectInfo;
import com.cqliving.cloud.online.wz.manager.WzQuestionCollectInfoManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("wzQuestionCollectInfoManager")
public class WzQuestionCollectInfoManagerImpl extends EntityServiceImpl<WzQuestionCollectInfo, WzQuestionCollectInfoDao> implements WzQuestionCollectInfoManager {

    @Override
    public List<WzQuestionCollectInfo> getInfoByCollect(Long questionId, Long collectId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_questionId", questionId);
        map.put("EQ_collectInfoId", collectId);
        return getEntityDao().query(map, null);
    }
}
