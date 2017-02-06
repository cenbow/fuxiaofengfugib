package com.cqliving.cloud.online.consult.dao;

import java.util.Map;

import com.cqliving.cloud.online.consult.dto.ConsultInfoDto;
import com.cqliving.framework.common.dao.support.ScrollPage;

public interface ConsultInfoDaoCustom {
    /**
     * 我要咨询列表(滚动分页)
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月29日下午3:11:59
     */
    public ScrollPage<ConsultInfoDto> queryConsultScrollPage(ScrollPage<ConsultInfoDto> page,Map<String, Object> conditions);
}
