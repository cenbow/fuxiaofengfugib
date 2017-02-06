package com.cqliving.cloud.online.joke.dao;

import java.util.Map;

import com.cqliving.cloud.online.joke.dto.JokeInfoDto;
import com.cqliving.framework.common.dao.support.ScrollPage;

public interface JokeInfoDaoCustom {

	/**
	 * <p>Description:</p>
	 * @author Tangtao on 2016年6月29日
	 * @param scrollPage
	 * @param conditions
	 * @param praiseUserId
	 * @return
	 */
	ScrollPage<JokeInfoDto> queryForScrollPage(ScrollPage<JokeInfoDto> scrollPage, Map<String, Object> conditions, Long praiseUserId);
	 
}
