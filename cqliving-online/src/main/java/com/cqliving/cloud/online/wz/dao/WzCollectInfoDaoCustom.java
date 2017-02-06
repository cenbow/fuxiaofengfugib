package com.cqliving.cloud.online.wz.dao;

import java.util.List;

import com.cqliving.cloud.online.interfacc.dto.WzCollectInfoData;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年10月8日
 */
public interface WzCollectInfoDaoCustom {
	
	/**
	 * Title:获得用户收集的信息
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年10月8日
	 * @param appId
	 * @param questionId
	 * @return
	 */
	public List<WzCollectInfoData> getUserCollectInfo(Long appId, Long questionId);

}
