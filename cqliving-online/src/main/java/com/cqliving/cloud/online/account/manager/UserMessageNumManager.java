package com.cqliving.cloud.online.account.manager;

import com.cqliving.framework.common.service.EntityService;
import com.cqliving.cloud.online.account.domain.UserMessageNum;

/**
 * 用户消息通知数量表 Manager
 * Date: 2016-05-12 11:23:50
 * @author Code Generator
 */
public interface UserMessageNumManager extends EntityService<UserMessageNum> {
    
    /**
     * Title:获得通知数量
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月12日
     * @param appId
     * @param token
     * @param type
     * @return
     */
    UserMessageNum getNum(Long appId, Long userId, Byte type);
    
    /**
     * Title:设置数量
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月12日
     * @param appId
     * @param type
     * @param userId
     * @param num 为null或0的时候表示删除这条数据，也是清零的一种方式，其他情况下在原来的数量上做增加减少
     */
    void setMessageNum(Long appId, Byte type, Long userId, Integer num);
}
