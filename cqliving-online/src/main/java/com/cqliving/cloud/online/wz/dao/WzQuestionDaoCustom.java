
package com.cqliving.cloud.online.wz.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.wz.dto.WzQuestionDto;
import com.cqliving.cloud.online.wz.dto.WzQuestionExcelDownload;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年5月10日
 */
public interface WzQuestionDaoCustom {
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年5月10日
     * @param page
     * @param conditions
     * @return
     */
    ScrollPage<WzQuestionDto> queryDtoForScrollPage(ScrollPage<WzQuestionDto> page, Map<String, Object> conditions);
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月18日
     * @param searchMap
     * @param sortMap
     * @return
     */
    List<WzQuestionExcelDownload> excelDownload(Map<String, Object> searchMap, Map<String, Boolean> sortMap);
}
