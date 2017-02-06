package com.cqliving.cloud.online.info.manager;

import java.util.List;

import com.cqliving.cloud.online.info.domain.InfoTempleteImageConfig;
import com.cqliving.cloud.online.info.dto.InfoTempleteImageConfigDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 模板图片配置表 Manager
 * Date: 2016-04-15 09:44:58
 * @author Code Generator
 */
public interface InfoTempleteImageConfigManager extends EntityService<InfoTempleteImageConfig> {

	public List<InfoTempleteImageConfig> getByAppColumnsId(Long appColumnsId,Long appId);
	
	public InfoTempleteImageConfigDto getById(Long id);
}
