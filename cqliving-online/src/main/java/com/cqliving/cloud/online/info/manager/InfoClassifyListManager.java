package com.cqliving.cloud.online.info.manager;

import com.cqliving.cloud.online.info.domain.InfoClassifyList;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯栏目列表图片表 Manager
 * Date: 2016-04-15 09:44:40
 * @author Code Generator
 */
public interface InfoClassifyListManager extends EntityService<InfoClassifyList> {

	public InfoClassifyList findByInfoId(Long infoId);
}
