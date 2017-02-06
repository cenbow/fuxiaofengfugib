
package com.cqliving.cloud.online.wz.dao;

import java.util.Map;

import com.cqliving.cloud.online.wz.domain.WzTransferDto;
import com.cqliving.framework.common.dao.support.PageInfo;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月10日
 */
public interface WzTransferDaoCustom {
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月20日
     * @param pageInfo
     * @param map
     * @param orderMap
     * @return
     */
    PageInfo<WzTransferDto> queryDtoForScrollPage(PageInfo<WzTransferDto> pageInfo, Map<String, Object> map, Map<String, Boolean> orderMap);
}
