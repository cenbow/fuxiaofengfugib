package com.cqliving.cloud.online.account.dao;

import java.util.List;

import com.cqliving.cloud.online.account.dto.UserActCollectInfoDto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月22日
 */
public interface UserActCollectInfoCustom {
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月22日
	 * @param userId
	 * @param actInfoListId
	 * @return
	 */
	public List<UserActCollectInfoDto> getCollectInfoList(Long userId, Long actInfoListId);
}
