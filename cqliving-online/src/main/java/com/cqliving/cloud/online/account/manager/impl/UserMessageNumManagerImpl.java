package com.cqliving.cloud.online.account.manager.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.account.dao.UserMessageNumDao;
import com.cqliving.cloud.online.account.domain.UserMessageNum;
import com.cqliving.cloud.online.account.manager.UserMessageNumManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Maps;

@Service("userMessageNumManager")
public class UserMessageNumManagerImpl extends EntityServiceImpl<UserMessageNum, UserMessageNumDao> implements UserMessageNumManager {
    
    @Override
    public UserMessageNum getNum(Long appId, Long userId, Byte type) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("EQ_appId", appId);
        map.put("EQ_userId", userId);
        map.put("EQ_type", type);
        List<UserMessageNum> list = getEntityDao().query(map, null);
        if(CollectionUtils.isNotEmpty(list)){
            return list.get(0);
        }
        return null;
        
    }
    @Override
    public void setMessageNum(Long appId, Byte type, Long userId, Integer num) {
        if(num == null){
            num = 0;
        }
        UserMessageNum userMessageNum = getNum(appId, userId, type);
        //num为null或0 表示要清空
        if(num == 0){
            if(userMessageNum != null){
                getEntityDao().remove(userMessageNum);
            }
        }else{
            if(userMessageNum == null){
                userMessageNum = new UserMessageNum();
                userMessageNum.setAppId(appId);
                userMessageNum.setType(type);
                userMessageNum.setUserId(userId);
                userMessageNum.setQuantity(num > 0 ? num : 0);
                getEntityDao().save(userMessageNum);
            }else{
                if(userMessageNum.getQuantity() + num > 0){
                    userMessageNum.setQuantity(userMessageNum.getQuantity() + num);
                }else{
                    userMessageNum.setQuantity(0);
                }
                getEntityDao().update(userMessageNum);
            }
        }
    }
}
