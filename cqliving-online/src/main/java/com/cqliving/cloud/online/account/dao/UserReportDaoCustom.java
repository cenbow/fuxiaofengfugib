package com.cqliving.cloud.online.account.dao;

import com.cqliving.cloud.online.account.dto.UserReportDto;

/**
 * 用户举报数据持久层接口
 * <p>Title:UserReportDaoCustom </p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author huxiaoping 2016年5月31日上午10:23:04
 *
 */
public interface UserReportDaoCustom {
    /**
     * 获取单个举报信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年5月31日上午10:26:49
     */
    public UserReportDto getByIdAndSourceType(Long id,Byte sourceType,Byte type);
}
