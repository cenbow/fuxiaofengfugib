package com.cqliving.cloud.online.wz.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.wz.dto.WzAppAuthorityDto;
import com.cqliving.cloud.online.wz.dto.WzAuthorityDto;
import com.cqliving.tool.common.Response;

/**
 * 问政权限配置表 Service
 * Date: 2016-05-09 17:26:06
 * @author Code Generator
 */
public interface WzAuthorityService {

    public Response<List<WzAuthorityDto>> list(Long appId);
	
	/**
	 * Title:根据配置名称获得值，并获得收集信息的字段或自动回复的内容
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月12日
	 * @param authorityId
	 * @return
	 */
	Response<WzAppAuthorityDto> getAppAuthByAuthName(Long appId, String configName);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月13日
	 * @param map
	 * @param authorityId
	 * @param collectInfoId
	 * @param collectInfoName
	 * @param isRequired
	 * @return
	 */
	Response<Void> saveAuthority(Long appId, Long userId, String userName, Map<String, Object> map, String[] authorityId, String[] collectInfoId, String[] collectInfoName, Byte[] isRequired);
	
}
