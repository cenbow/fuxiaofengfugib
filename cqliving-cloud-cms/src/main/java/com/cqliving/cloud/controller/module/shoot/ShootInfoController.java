package com.cqliving.cloud.controller.module.shoot;

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
import com.cqliving.cloud.online.shoot.domain.ShootImages;
import com.cqliving.cloud.online.shoot.domain.ShootInfo;
import com.cqliving.cloud.online.shoot.dto.ShootInfoDto;
import com.cqliving.cloud.online.shoot.service.ShootImagesService;
import com.cqliving.cloud.online.shoot.service.ShootInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/shoot")
public class ShootInfoController extends CommonController {

	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private CommentConfigService commentConfigService;
	@Autowired
	private ShootImagesService shootImagesService;
    @Autowired
    private ShootInfoService shootInfoService;

    //列表
    @RequestMapping(value ="shoot_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        searchMap.put("EQ_appId", sessionUser.getAppId());
        searchMap.put("NOTEQ_status", ShootInfo.STATUS99);//排除逻辑删除状态
        sortMap.put("id", false);
        PageInfo<ShootInfoDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", shootInfoService.queryDtoForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", ShootInfo.allStatuss);
        map.put("allShootTypes", ShootInfo.allShootTypes);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/shoot/shoot_info_list_page";
        }else{
        	return "tiles.module.shoot.shoot_info_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="shoot_info_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.shoot.shoot_info_detail";
    }


    //增加-保存
    @RequestMapping(value ="shoot_info_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ShootInfo shootInfo){
        //ID
        shootInfo.setId(null);
        Response<Void> res = shootInfoService.save(shootInfo);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="shoot_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShootInfo shootInfo = shootInfoService.get(id).getData();
        if(shootInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shootInfo);
        return "tiles.module.shoot.shoot_info_detail";
    }

    //修改-保存
    @RequestMapping(value ="shoot_info_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ShootInfo shootInfo){
        Response<Void> res = Response.newInstance();
        if(shootInfo==null || shootInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ShootInfo sourceShootInfo = shootInfoService.get(shootInfo.getId()).getData();
            if(sourceShootInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端_ID
            sourceShootInfo.setAppId(shootInfo.getAppId());
            //随手拍内容
            sourceShootInfo.setContent(shootInfo.getContent());
            //用户ID
            sourceShootInfo.setUserId(shootInfo.getUserId());
            //回复量
            sourceShootInfo.setReplyCount(shootInfo.getReplyCount());
            //点赞量
            sourceShootInfo.setPriseCount(shootInfo.getPriseCount());
            //状态
            sourceShootInfo.setStatus(shootInfo.getStatus());
            //摄影类型
            sourceShootInfo.setShootType(shootInfo.getShootType());
            //创建时间
            sourceShootInfo.setCreateTime(shootInfo.getCreateTime());
            //创建人
            sourceShootInfo.setCreatorId(shootInfo.getCreatorId());
            //创建人姓名
            sourceShootInfo.setCreator(shootInfo.getCreator());
            //更新时间
            sourceShootInfo.setUpdateTime(shootInfo.getUpdateTime());
            //更新人ID
            sourceShootInfo.setUpdatorId(shootInfo.getUpdatorId());
            //更新人
            sourceShootInfo.setUpdator(shootInfo.getUpdator());
            //所处经度
            sourceShootInfo.setLng(shootInfo.getLng());
            //所处纬度
            sourceShootInfo.setLat(shootInfo.getLat());
            //所处位置
            sourceShootInfo.setPlace(shootInfo.getPlace());
            res= shootInfoService.save(sourceShootInfo);
            shootInfo = sourceShootInfo;
        }catch (Exception ex){
            logger.error("Save Method (Update) ShootInfo Error : " + shootInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping({"shoot_info_view"})
    public String show(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id) {
    	//查询随手拍
        ShootInfo shootInfo = shootInfoService.get(id).getData();
        if(shootInfo == null) {
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shootInfo);
        
        //查询随手拍图片列表
        List<ShootImages> images = shootImagesService.getByShootId(shootInfo.getId()).getData();
        for (ShootImages img : images) {
			//获取原图
			img.setImageUrl(img.getImageUrl().replaceAll("_\\d{3}x\\d{3}\\.", "."));
		}
        map.put("images", images);
        return "tiles.module.shoot.shoot_info_view";
    }
    
    /**
     * <p>Description: 保存随手拍状态</p>
     * @author Tangtao on 2016年6月14日
     * @param request
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "shoot_info_view_save", method = {RequestMethod.POST})
    public Response<Void> showSave(HttpServletRequest request, @RequestParam Long id, @RequestParam Byte status) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	return shootInfoService.updateStatus(status, id, sessionUser.getUserId(), sessionUser.getNickname());
    }

    //删除
    @RequestMapping(value ="shoot_info_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shootInfoService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="shoot_info_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shootInfoService.deleteLogic(ids);
        return res;
    }
    
    /**
     * <p>Description: 下线</p>
     * @author Tangtao on 2016年6月12日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="shoot_info_offline")
    public Response<Void> offline(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id) {
    	Response<Void> res = shootInfoService.updateStatus(ShootInfo.STATUS88, id);
    	return res;
    }
    
    /**
     * <p>Description: 批量下线</p>
     * @author Tangtao on 2016年6月12日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="shoot_info_offline_batch")
    public Response<Void> offlineBatch(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids) {
    	Response<Void> res = shootInfoService.updateStatus(ShootInfo.STATUS88, ids);
    	return res;
    }
    
    /**
     * <p>Description: 上线</p>
     * @author Tangtao on 2016年6月12日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="shoot_info_online")
    public Response<Void> online(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id) {
    	Response<Void> res = shootInfoService.updateStatus(ShootInfo.STATUS3, id);
    	return res;
    }
    
    /**
     * <p>Description: 批量上线</p>
     * @author Tangtao on 2016年6月12日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="shoot_info_online_batch")
    public Response<Void> online(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids) {
    	Response<Void> res = shootInfoService.updateStatus(ShootInfo.STATUS3, ids);
    	return res;
    }
    
    /**
     * <p>Description: 随手拍配置</p>
     * @author Tangtao on 2016年6月12日
     * @param request
     * @param model
     * @param appId
     * @return
     */
    @RequestMapping(value = "shoot_app_config", method = RequestMethod.GET)
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
    	
    	//获取随手拍app配置
    	Response<List<CommentAppConfig>> response1 = commentAppConfigService.getByAppAndType(appId, BusinessType.SOURCE_TYPE_4);
    	//获取随手拍系统配置
    	Response<List<CommentConfig>> response2 = commentConfigService.getByType(BusinessType.SOURCE_TYPE_4);
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
        return "tiles.module.shoot.shoot_app_config";
    }
    
    /**
     * <p>Description: 保存随手拍配置</p>
     * @author Tangtao on 2016年6月13日
     * @param request
     * @param appId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "shoot_app_config_save", method = {RequestMethod.POST})
    public Response<Void> configSave(HttpServletRequest request, Long appId) {
    	Response<Void> response = Response.newInstance();
    	Map<String, String[]> map = request.getParameterMap();
    	Map<Long, Byte> parma = Maps.newHashMap();
    	for (String configKey : map.keySet()) {
			if (!configKey.startsWith("shootConfig_")) {
				continue;
			}
			parma.put(Long.valueOf(configKey.split("_")[1]), Byte.valueOf(map.get(configKey)[0]));
		}
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	appId = appId == null ? sessionUser.getAppId() : appId;
    	response = commentAppConfigService.save(appId, sessionUser.getUserId(), sessionUser.getNickname(), parma, BusinessType.SOURCE_TYPE_4);
    	return response;
    }
    
}