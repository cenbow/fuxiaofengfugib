package com.cqliving.cloud.controller.module.shop;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
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
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.CommentAppConfig;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.domain.RecommendInfo;
import com.cqliving.cloud.online.config.service.CommentAppConfigService;
import com.cqliving.cloud.online.config.service.CommentConfigService;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.config.service.RecommendInfoService;
import com.cqliving.cloud.online.info.domain.InfoLable;
import com.cqliving.cloud.online.info.service.InfoLableService;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.service.SysUserDataService;
import com.cqliving.cloud.online.shop.domain.ShopCategory;
import com.cqliving.cloud.online.shop.domain.ShopInfo;
import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.dto.ShopInfoListDto;
import com.cqliving.cloud.online.shop.service.ShopCategoryService;
import com.cqliving.cloud.online.shop.service.ShopImageService;
import com.cqliving.cloud.online.shop.service.ShopInfoService;
import com.cqliving.cloud.online.shop.service.ShopTypeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/shop")
public class ShopInfoController extends CommonController {

	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private CommentConfigService commentConfigService;
	@Autowired
	private ConfigRegionService configRegionService;
	@Autowired
	private InfoLableService infoLableService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private ShopImageService shopImageService;
    @Autowired
    private ShopInfoService shopInfoService;
    @Autowired
    private ShopTypeService shopTypeService;
    @Autowired
    private SysUserDataService sysUserDataService;
    @Autowired
    private RecommendInfoService recommendInfoService;

    //列表
    @RequestMapping(value ="shop_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
		SessionUser sessionUser = SessionFace.getSessionUser(request);
        Long appId = sessionUser.getAppId();
        PageInfo<ShopInfoListDto> pageInfo = getPageInfo(request);
        searchMap.put("EQ_appId", appId);
        searchMap.put("NOTEQ_status", ShopInfo.STATUS99);//排除逻辑删除状态
        //控制分类数据权限
    	Long[] typeIds = sysUserDataService.findValueByUserId(sessionUser.getUserId(), SysUserData.TYPE3).getData();
    	if (ArrayUtils.isNotEmpty(typeIds)) {
    		searchMap.put("IN_typeId", Arrays.asList(typeIds));
		}
        //排序
        sortMap.put("topType", false);
        sortMap.put("updateTime", false);
        sortMap.put("id", false);
        map.put("pageInfo", shopInfoService.queryByPage(pageInfo, searchMap, sortMap).getData());
        map.put("allStatuss", ShopInfo.allStatuss);
        //获取类型
        Response<List<ShopType>> typeResponse = shopTypeService.getByApp(appId);
        List<ShopType> typeConditionList = Lists.newArrayList();
        Map<Long, String> allTypeMap = Maps.newHashMap();
        if (ArrayUtils.isNotEmpty(typeIds)) {
        	 for (Long typeId : typeIds) {
        		 for (ShopType obj : typeResponse.getData()) {
        			 if (typeId.equals(obj.getId())) {
        				 allTypeMap.put(obj.getId(), obj.getName());
        				 typeConditionList.add(obj);
					}
         		}
			}
        } else {
            for (ShopType obj : typeResponse.getData()) {
            	allTypeMap.put(obj.getId(), obj.getName());
            	typeConditionList.add(obj);
    		}
        }

        map.put("allTypes", typeConditionList);         
        map.put("allTypeMap", allTypeMap);         
        //获取区域
        Response<List<ConfigRegion>> regionResponse = configRegionService.getByAppAndType(new Long[]{appId}, ConfigRegion.TYPE3);
        map.put("allRegion", regionResponse.getData());      
        //获取分类
        Response<List<ShopCategory>> categoryResponse = shopCategoryService.getByApp(appId, sessionUser.getUserId());
        map.put("allCategories", categoryResponse.getData());
        map.put("allSourceTypes", ShopInfo.allSourceTypes);
        map.put("allAppStatuss", ShopInfo.allAppStatuss);
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/shop/shop_info_list_page";
        }else{
        	return "tiles.module.shop.shop_info_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="shop_info_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        Long appId = sessionUser.getAppId();
        if (appId == null) {
        	return super.operFailure("请登录App管理员操作", map);
		}
        //查询百度地图Key
  		Response<AppInfo> appInforResponse = appInfoService.get(appId);
  		map.put("baiduLbsKey", appInforResponse.getData().getBaiduLbsKey());
    	//获取区域
        Response<List<ConfigRegion>> regionResponse = configRegionService.getByAppAndType(new Long[]{appId}, ConfigRegion.TYPE3);
        map.put("allRegion", regionResponse.getData());         
        //获取类型
        Response<List<ShopType>> typeResponse = shopTypeService.getByApp(appId);
        map.put("allTypes", typeResponse.getData());         
        //获取分类
        Response<List<ShopCategory>> categoryResponse = shopCategoryService.getByApp(appId, sessionUser.getUserId());
        map.put("allCategories", categoryResponse.getData());      
        //获取标签
        Response<List<InfoLable>> labelreResponse = infoLableService.findByAppId(appId, BusinessType.SOURCE_TYPE_3);
        map.put("infoLabels", labelreResponse.getData());    
        return "tiles.module.shop.shop_info_detail";
    }


    //增加-保存
    @RequestMapping(value = "shop_info_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map, 
    		ShopInfo shopInfo, 
    		@RequestParam String images, 
    		@RequestParam String editorValue) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        //ID
        shopInfo.setId(null);
        shopInfo.setContent(editorValue);
        Response<Void> res = shopInfoService.save(shopInfo, images, sessionUser.getUserId(), sessionUser.getNickname());
        if (res.getCode() < 0) {
        	return new Response<Void>(-1, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="shop_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
    	ShopInfo shopInfo = shopInfoService.get(id).getData();
    	if (shopInfo == null) {
    		//没有记录
    		return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
    	}
    	Long appId = shopInfo.getAppId();
        //查询百度地图Key
  		Response<AppInfo> appInforResponse = appInfoService.get(appId);
  		map.put("baiduLbsKey", appInforResponse.getData().getBaiduLbsKey());
    	//获取区域
        Response<List<ConfigRegion>> regionResponse = configRegionService.getByAppAndType(new Long[]{appId}, ConfigRegion.TYPE3);
        map.put("allRegion", regionResponse.getData());      
        //获取类型
        Response<List<ShopType>> typeResponse = shopTypeService.getByApp(appId);
        map.put("allTypes", typeResponse.getData());         
        //获取分类
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<List<ShopCategory>> categoryResponse = shopCategoryService.getByApp(appId, sessionUser.getUserId());
        map.put("allCategories", categoryResponse.getData());    
        map.put("item", shopInfo);
        //获取标签
        Response<List<InfoLable>> labelreResponse = infoLableService.findByAppId(appId, BusinessType.SOURCE_TYPE_3);
        map.put("infoLabels", labelreResponse.getData());    
        //查询商铺图片
        map.put("images", shopImageService.getByShop(shopInfo.getId()).getData());
        return "tiles.module.shop.shop_info_detail";
    }

    //修改-保存
    @ResponseBody
    @RequestMapping(value = "shop_info_update", method = {RequestMethod.POST})
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map, 
    		ShopInfo shopInfo, 
    		@RequestParam String images, 
    		@RequestParam String editorValue) {
        Response<Void> res = Response.newInstance();
        if (shopInfo == null || shopInfo.getId() == null) {
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try {
            ShopInfo sourceShopInfo = shopInfoService.get(shopInfo.getId()).getData();
            if (sourceShopInfo == null) {
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            if("4".equals(shopInfo.getStatus() + "") && ShopInfo.SOURCETYPE2.equals(sourceShopInfo.getSourceType())){//审核保存并通过
            	sourceShopInfo.setStatus(ShopInfo.STATUS3);
            	sourceShopInfo.setDescription(shopInfo.getAuditDesc());
            }

            //店铺名称
            sourceShopInfo.setName(shopInfo.getName());
            //所处位置纬度
            sourceShopInfo.setLat(shopInfo.getLat());
            //所处位置经度
            sourceShopInfo.setLng(shopInfo.getLng());
            //所属区域CODE
            sourceShopInfo.setRegionCode(shopInfo.getRegionCode());
            //地址
            sourceShopInfo.setAddress(shopInfo.getAddress());
            //封面图
            sourceShopInfo.setCoverImg(shopInfo.getCoverImg());
            //电话
            sourceShopInfo.setTelephone(shopInfo.getTelephone());
            //描述
            sourceShopInfo.setDescription(shopInfo.getDescription());
            //内容
            sourceShopInfo.setContent(editorValue);
            //价格（分）
            sourceShopInfo.setPrice(shopInfo.getPrice() * 100);
            //更新时间
            sourceShopInfo.setUpdateTime(DateUtil.now());
            //分类
            sourceShopInfo.setCategoryId(shopInfo.getCategoryId());
            //标签
            sourceShopInfo.setInfoLabelId(shopInfo.getInfoLabelId());
            //类型
            sourceShopInfo.setTypeId(shopInfo.getTypeId());
            res= shopInfoService.save(sourceShopInfo, images, SessionFace.getSessionUser(request).getUserId(), SessionFace.getSessionUser(request).getNickname());
            shopInfo = sourceShopInfo;
        } catch (Exception ex) {
            logger.error("Save Method (Update) ShopInfo Error : " + shopInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }
    
    //跳转到推荐到首页
    @RequestMapping(value ="recommend", method = RequestMethod.GET)
    public String toRecommend(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        RecommendInfo recommend = new RecommendInfo();
        recommend.setSourceId(id);
        map.put("item", recommend);
        return getReturnUrl(request,map,"tiles.module.shop.recommend");
    }
    
    //推荐到首页
    @ResponseBody
    @RequestMapping(value = "recommend", method = {RequestMethod.POST})
    public Response<Void> recommend(HttpServletRequest request, Map<String, Object> map, RecommendInfo recommend) {
        Response<Void> res = Response.newInstance();
        if (recommend == null || recommend.getSourceId() == null) {
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try {
            ShopInfo sourceShopInfo = shopInfoService.get(recommend.getSourceId()).getData();
            if (sourceShopInfo == null) {
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            /** 客户端_ID */
            recommend.setAppId(sourceShopInfo.getAppId());
            /** 业务类型 */
            recommend.setSourceType(RecommendInfo.SOURCETYPE3);
            /** 排序号 */
            recommend.setSortNo(0);
            /** 名称  */
            recommend.setName(sourceShopInfo.getName());
            /** 状态 */
            recommend.setStatus(RecommendInfo.STATUS1);
            SessionUser user = SessionFace.getSessionUser(request);
            Date now = new Date();
            /** 创建时间 */
            recommend.setCreateTime(now);;
            /** 创建人ID */
            recommend.setCreatorId(user.getUserId());
            /** 创建人 */
            recommend.setCreator(user.getNickname());
            /** 更新时间 */
            recommend.setUpdateTime(now);
            /** 更新人ID */
            recommend.setUpdatorId(user.getUserId());
            /** 更新人 */
            recommend.setUpdator(user.getNickname());
            res = recommendInfoService.save(recommend);
            if (res.getCode() < 0) {
                return new Response<Void>(-1, "推荐到首页失败!");
            }
        } catch (Exception ex) {
            logger.error("推荐失败 ，源: " + recommend.getSourceId(), ex);
            //修改失败
            return new Response<Void>(-1, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="shop_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShopInfo shopInfo = shopInfoService.get(id).getData();
        if(shopInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shopInfo);
        return "tiles.module.shop.shop_info_view";
    }

    //删除
    @RequestMapping(value ="shop_info_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shopInfoService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="shop_info_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shopInfoService.deleteLogic(ids);
        return res;
    }
    
    /**
     * <p>Description: 批量下线</p>
     * @author Tangtao on 2016年5月17日
     * @param request
     * @param map
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="shop_info_offline_batch", method = RequestMethod.POST)
    public Response<Void> offlineBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shopInfoService.updateStatus(ShopInfo.STATUS88, ids);
        return res;
    }
    
    /**
     * <p>Description: 上线</p>
     * @author Tangtao on 2016年5月17日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="shop_info_online", method = RequestMethod.POST)
    public Response<Void> online(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shopInfoService.updateStatus(ShopInfo.STATUS3, id);
        return res;
    }
    
    /**
     * <p>Description: 下线</p>
     * @author Tangtao on 2016年5月17日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="shop_info_offline", method = RequestMethod.POST)
    public Response<Void> offline(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
    	Response<Void> res = shopInfoService.updateStatus(ShopInfo.STATUS88, id);
    	return res;
    }
    
    /**
     * <p>Description: 置顶</p>
     * @author Tangtao on 2016年7月6日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="shop_info_top", method = RequestMethod.POST)
    public Response<Void> top(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Response<Void> res = shopInfoService.top(id, sessionUser.getNickname(), sessionUser.getUserId());
    	return res;
    }
    
    /**
     * <p>Description: 取消置顶</p>
     * @author Tangtao on 2016年7月25日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="shop_info_untop", method = RequestMethod.POST)
    public Response<Void> untop(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Response<Void> res = shopInfoService.untop(id, sessionUser.getNickname(), sessionUser.getUserId());
    	return res;
    }
    
    /**
     * <p>Description: 商情配置</p>
     * @author Tangtao on 2016年6月12日
     * @param request
     * @param model
     * @param appId
     * @return
     */
    @RequestMapping(value = "shop_app_config", method = RequestMethod.GET)
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
    	
    	//获取商情app配置
    	Response<List<CommentAppConfig>> response1 = commentAppConfigService.getByAppAndType(appId, BusinessType.SOURCE_TYPE_3);
    	//获取商情系统配置
    	Response<List<CommentConfig>> response2 = commentConfigService.getByType(BusinessType.SOURCE_TYPE_3);
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
        return "tiles.module.shop.shop_app_config";
    }
    
    /**
     * <p>Description: 保存商情配置</p>
     * @author Tangtao on 2016年6月13日
     * @param request
     * @param appId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "shop_app_config_save", method = {RequestMethod.POST})
    public Response<Void> configSave(HttpServletRequest request, Long appId) {
    	Response<Void> response = Response.newInstance();
    	Map<String, String[]> map = request.getParameterMap();
    	Map<Long, Byte> parma = Maps.newHashMap();
    	for (String configKey : map.keySet()) {
			if (!configKey.startsWith("shopConfig_")) {
				continue;
			}
			parma.put(Long.valueOf(configKey.split("_")[1]), Byte.valueOf(map.get(configKey)[0]));
		}
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	appId = appId == null ? sessionUser.getAppId() : appId;
    	response = commentAppConfigService.save(appId, sessionUser.getUserId(), sessionUser.getNickname(), parma, BusinessType.SOURCE_TYPE_3);
    	return response;
    }
    
    
    /**
     * Title:审核
     * <p>Description:</p>
     * @author DeweiLi on 2016年10月24日
     * @param request
     * @param id
     * @param content
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "shop_info_audit", method = {RequestMethod.POST})
    public Response<Void> audit(HttpServletRequest request, Long id, @RequestParam(required = false, value="ids[]") Long[] ids, @RequestParam String content, @RequestParam String status) {
    	Response<Void> rs = Response.newInstance();
    	Byte sta = "pass".equals(status) ? ShopInfo.STATUS3 : ShopInfo.STATUS_1;
    	if(id == null && !(ids != null && ids.length > 0)){
    		return new Response<>(ErrorCodes.FAILURE, "请选择要审核的记录");
    	}
    	if(id != null){
    		ids = new Long[]{id};
    	}
    	if(ShopInfo.STATUS_1.equals(sta) && StringUtils.isBlank(content)){
    		return new Response<>(ErrorCodes.FAILURE, "请输入不通过的原因");
    	}
    	rs = shopInfoService.audit(ids, sta, content);
    	return rs;
    }
}