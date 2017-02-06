package com.cqliving.cloud.online.info.manager;

import java.util.List;

import com.cqliving.cloud.online.info.domain.InfoTheme;
import com.cqliving.framework.common.service.EntityService;

/**
 * 资讯专题分类表 Manager
 * Date: 2016-04-15 09:45:02
 * @author Code Generator
 */
public interface InfoThemeManager extends EntityService<InfoTheme> {

	/**
	 * <p>Description: 获取专题分类</p>
	 * @author Tangtao on 2016年5月12日
	 * @param appId
	 * @return
	 */
	List<InfoTheme> getByApp(Long appId);

	public List<InfoTheme> findByInfoClassifyId(Long infoClassifyId);
}
