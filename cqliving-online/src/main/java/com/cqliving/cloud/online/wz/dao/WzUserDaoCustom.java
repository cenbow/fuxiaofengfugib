package com.cqliving.cloud.online.wz.dao;

import java.util.List;

import com.cqliving.cloud.online.wz.domain.WzUser;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2017年2月3日
 */
public interface WzUserDaoCustom {
    
    /**
     * Title:根据appId获取用户
     * <p>Description:</p>
     * @author DeweiLi on 2017年2月3日
     * @param appId
     * @return
     */
    public List<WzUser> getByAppId(Long appId);
}
