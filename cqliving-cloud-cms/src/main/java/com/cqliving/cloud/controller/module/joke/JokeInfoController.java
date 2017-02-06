package com.cqliving.cloud.controller.module.joke;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
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
import com.cqliving.cloud.online.joke.domain.JokeInfo;
import com.cqliving.cloud.online.joke.service.JokeInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/joke")
public class JokeInfoController extends CommonController {

	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private CommentConfigService commentConfigService;
    @Autowired
    private JokeInfoService jokeInfoService;

    //列表
    @RequestMapping(value ="joke_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_onlineTime", required=false) Date search_GTE_onlineTime
        ,@RequestParam(value="search_LT_onlineTime", required=false) Date search_LT_onlineTime
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        searchMap.put("EQ_appId", sessionUser.getAppId());
        searchMap.put("LT_onlineTime", search_LT_onlineTime);
        searchMap.put("GTE_onlineTime", search_GTE_onlineTime);
        searchMap.put("LT_createTime", search_LT_createTime);
        searchMap.put("GTE_createTime", search_GTE_createTime);
        PageInfo<JokeInfo> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", JokeInfo.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", jokeInfoService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", JokeInfo.allStatuss);
	                    
        //查询按钮和点击页面是ajax操作。
        if (StringUtils.isNotBlank(isAjaxPage)) {
        	return "/module/joke/joke_info_list_page";
        } else {
        	return "tiles.module.joke.joke_info_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="joke_info_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	map.put("onlineTime", DateUtil.now());
        return "tiles.module.joke.joke_info_detail";
    }


    //增加-保存
    @RequestMapping(value = {"joke_info_add", "joke_info_add_draft", "joke_info_add_save", "joke_info_add_publish", "joke_info_add_offline"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,JokeInfo jokeInfo) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Date now = DateUtil.now();
        //ID
        jokeInfo.setId(null);
        jokeInfo.setCreateTime(now);
        jokeInfo.setCreator(sessionUser.getNickname());
        jokeInfo.setCreatorId(sessionUser.getUserId());
        jokeInfo.setDespiseCount(0);
        jokeInfo.setPraiseCount(0);
        jokeInfo.setReplyCount(0);
        jokeInfo.setUpdateTime(now);
        jokeInfo.setUpdator(sessionUser.getNickname());
        jokeInfo.setUpdatorId(sessionUser.getUserId());
        Response<Void> res = jokeInfoService.save(jokeInfo);
        if (res.getCode() < 0) {
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="joke_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        JokeInfo jokeInfo = jokeInfoService.get(id).getData();
        if(jokeInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", jokeInfo);
        return "tiles.module.joke.joke_info_detail";
    }

    //修改-保存
    @RequestMapping(value = {"joke_info_update", "joke_info_update_draft", "joke_info_update_save", "joke_info_update_publish", "joke_info_update_offline"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,JokeInfo jokeInfo){
        Response<Void> res = Response.newInstance();
        if (jokeInfo == null || jokeInfo.getId() == null) {
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            JokeInfo sourceJokeInfo = jokeInfoService.get(jokeInfo.getId()).getData();
            if (sourceJokeInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
        	SessionUser sessionUser = SessionFace.getSessionUser(request);
            //状态
            sourceJokeInfo.setStatus(jokeInfo.getStatus());
            //内容
            sourceJokeInfo.setContent(jokeInfo.getContent());
            //上线时间
            sourceJokeInfo.setOnlineTime(jokeInfo.getOnlineTime());
            //更新时间
            sourceJokeInfo.setUpdateTime(DateUtil.now());
            //更新人ID
            sourceJokeInfo.setUpdatorId(sessionUser.getUserId());
            //更新人
            sourceJokeInfo.setUpdator(sessionUser.getNickname());
            res= jokeInfoService.save(sourceJokeInfo);
            jokeInfo = sourceJokeInfo;
        } catch (Exception ex) {
            logger.error("Save Method (Update) JokeInfo Error : " + jokeInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="joke_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        JokeInfo jokeInfo = jokeInfoService.get(id).getData();
        if(jokeInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", jokeInfo);
        return "tiles.module.joke.joke_info_view";
    }

    //删除
    @RequestMapping(value ="joke_info_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = jokeInfoService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="joke_info_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = jokeInfoService.deleteLogic(ids);
        return res;
    }
    
    /**
     * <p>Description: 批量发布</p>
     * @author Tangtao on 2016年6月28日
     * @param request
     * @param map
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping({"joke_info_publish_batch"})
    public Response<Void> publishBatch(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids) {
    	Response<Void> res = jokeInfoService.publishBatch(Arrays.asList(ids));
    	return res;
    }
    
    /**
     * <p>Description: 批量下线</p>
     * @author Tangtao on 2016年6月28日
     * @param request
     * @param map
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping({"joke_info_offline_batch"})
    public Response<Void> offlineBatch(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids) {
    	Response<Void> res = jokeInfoService.offlineBatch(Arrays.asList(ids));
    	return res;
    }
    
    /**
     * <p>Description: 段子配置</p>
     * @author Tangtao on 2016年7月7日
     * @param request
     * @param model
     * @param appId
     * @return
     */
    @RequestMapping(value = "joke_app_config", method = RequestMethod.GET)
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
    	
    	//获取段子app配置
    	Response<List<CommentAppConfig>> response1 = commentAppConfigService.getByAppAndType(appId, BusinessType.SOURCE_TYPE_5);
    	//获取段子系统配置
    	Response<List<CommentConfig>> response2 = commentConfigService.getByType(BusinessType.SOURCE_TYPE_5);
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
        return "tiles.module.joke.joke_app_config";
    }
    
    /**
     * <p>Description: 保存段子配置</p>
     * @author Tangtao on 2016年7月7日
     * @param request
     * @param appId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "joke_app_config_save", method = {RequestMethod.POST})
    public Response<Void> configSave(HttpServletRequest request, Long appId) {
    	Response<Void> response = Response.newInstance();
    	Map<String, String[]> map = request.getParameterMap();
    	Map<Long, Byte> parma = Maps.newHashMap();
    	for (String configKey : map.keySet()) {
			if (!configKey.startsWith("jokeConfig_")) {
				continue;
			}
			parma.put(Long.valueOf(configKey.split("_")[1]), Byte.valueOf(map.get(configKey)[0]));
		}
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	appId = appId == null ? sessionUser.getAppId() : appId;
    	response = commentAppConfigService.save(appId, sessionUser.getUserId(), sessionUser.getNickname(), parma, BusinessType.SOURCE_TYPE_5);
    	return response;
    }
    
}