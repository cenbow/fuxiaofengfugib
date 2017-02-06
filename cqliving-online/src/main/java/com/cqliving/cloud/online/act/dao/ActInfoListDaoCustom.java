package com.cqliving.cloud.online.act.dao;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.dto.ActInfoListDto;
import com.cqliving.cloud.online.interfacc.dto.ActExportDto;
import com.cqliving.framework.common.dao.support.ScrollPage;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author Tangtao on 2016年6月29日
 */
public interface ActInfoListDaoCustom {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年6月29日
	 * @param scrollPage
	 * @param conditions
	 * @return
	 */
	ScrollPage<ActInfoListDto> queryForScrollPage(ScrollPage<ActInfoListDto> scrollPage, Map<String, Object> conditions);
	public List<ActExportDto> actExportList(Long classfyId);
}
