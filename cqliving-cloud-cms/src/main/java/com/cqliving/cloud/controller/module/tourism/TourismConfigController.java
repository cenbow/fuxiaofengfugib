package com.cqliving.cloud.controller.module.tourism;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.CommentAppConfig;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.service.CommentAppConfigService;
import com.cqliving.cloud.online.config.service.CommentConfigService;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/tourism")
public class TourismConfigController extends CommonController {

	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private CommentConfigService commentConfigService;
    
    /**
     * <p>Description: 旅游配置</p>
     * @author huxiaoping on 2016年8月25日
     * @param request
     * @param model
     * @param appId
     * @return
     */
    @RequestMapping(value = "config", method = RequestMethod.GET)
    public String config(HttpServletRequest request, Model model, Long appId) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	//获取操作用户数据权限范围
    	List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	if (CollectionUtils.isNotEmpty(appInfoDtos) && appInfoDtos.size() > 1) {
    		model.addAttribute("appInfos", appInfoDtos);
    	}
    	if (appId == null) {
    		appId = sessionUser.getAppId() == null ? appInfoDtos.get(0).getId() : sessionUser.getAppId();
		}
    	model.addAttribute("appId", appId);
    	
    	//获取app配置
    	Response<List<CommentAppConfig>> response1 = commentAppConfigService.getByAppAndType(appId, BusinessType.SOURCE_TYPE_10);
    	//获取系统配置
    	Response<List<CommentConfig>> response2 = commentConfigService.getByType(BusinessType.SOURCE_TYPE_10);
    	//便于权限控制，app配置数据改为手动初始化，只展示已存在数据的配置项 By Tangtao 2016-10-27
        //设置返回页面的数据，如果app单独有此配置，覆盖系统配置（逻辑已改变 By Tangtao 2016-10-27）
        Map<Long, CommentConfig> map = Maps.newLinkedHashMap();
        for (CommentConfig config : response2.getData()) {
			for (CommentAppConfig appConfig : response1.getData()) {
				if (appConfig.getCommentConfigId().equals(config.getId())) {
					config.setValue(appConfig.getValue());	//覆盖系统默认值
					map.put(config.getId(), config);
					break;
				}
			}
		}
    	model.addAttribute("configMap", map);
        return "tiles.module.tourism.tourism_app_config";
    }
    
    /**
     * <p>Description: 保存配置</p>
     * @author huxiaoping on 2016年8月25日
     * @param request
     * @param appId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "config_save", method = {RequestMethod.POST})
    public Response<Void> configSave(HttpServletRequest request, Long appId) {
    	Response<Void> response = Response.newInstance();
    	Map<String, String[]> map = request.getParameterMap();
    	Map<Long, Byte> parma = Maps.newHashMap();
    	for (String configKey : map.keySet()) {
			if (!configKey.startsWith("tourismConfig_")) {
				continue;
			}
			parma.put(Long.valueOf(configKey.split("_")[1]), Byte.valueOf(map.get(configKey)[0]));
		}
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	appId = appId == null ? sessionUser.getAppId() : appId;
    	response = commentAppConfigService.save(appId, sessionUser.getUserId(), sessionUser.getNickname(), parma, BusinessType.SOURCE_TYPE_10);
    	return response;
    }
    
}