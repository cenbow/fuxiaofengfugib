package com.cqliving.cloud.controller.module.shopping;

import java.util.ArrayList;
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
import com.cqliving.cloud.online.shopping.domain.ShoppingCategory;
import com.cqliving.cloud.online.shopping.domain.ShoppingFareTemplate;
import com.cqliving.cloud.online.shopping.domain.ShoppingGoods;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingCategoryDto;
import com.cqliving.cloud.online.shopping.dto.ShoppingFareTemplateDto;
import com.cqliving.cloud.online.shopping.dto.ShoppingGoodsDto;
import com.cqliving.cloud.online.shopping.service.ShoppingCategoryService;
import com.cqliving.cloud.online.shopping.service.ShoppingFareTemplateService;
import com.cqliving.cloud.online.shopping.service.ShoppingGoodsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.JsonMapper;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/shopgoods")
public class ShoppingGoodsController extends CommonController {

	@Autowired
	private AppInfoService appInfoService;
	@Autowired
	private CommentAppConfigService commentAppConfigService;
	@Autowired
	private CommentConfigService commentConfigService;
    @Autowired
    private ShoppingGoodsService shoppingGoodsService;
    @Autowired
    private ShoppingCategoryService shoppingCategoryService;
    @Autowired
    private ShoppingFareTemplateService shoppingFareTemplateService;
    //列表
    @RequestMapping(value ="shop_goods_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
		sortMap.put("sortNo",true);
		sortMap.put("onlineTime",false);
		sortMap.put("id",false);
        PageInfo<ShoppingGoodsDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", ShoppingGoods.STATUS99);//排除逻辑删除状态
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        //处理App查询条件以及页面上展示的app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList&&appList.size()>1){
            map.put("appList", appList);
        }
        if(StringUtils.isBlank(searchAppid)){
           if(null!=appList&&appList.size()>0){
               List<Long> appIds = new ArrayList<Long>();
               for (AppInfoDto app : appList) {
                   appIds.add(app.getId());
               }
               searchMap.put("IN_appId", appIds);
           }else{
               searchMap.put("EQ_appId", user.getAppId());
           }
        }        
        String shoppingCategoryId = (String)searchMap.get("EQ_shoppingCategoryId");
        searchMap.remove("EQ_shoppingCategoryId");
        if(StringUtils.isNotBlank(shoppingCategoryId)){
            searchMap.put("EQ_categoryLevelOneId", Long.valueOf(shoppingCategoryId.trim()));
        }
        
        map.put("pageInfo", shoppingGoodsService.queryByPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allIsFreeShippings", ShoppingGoods.allIsFreeShippings);
        map.put("allIsRecommemdIndexs", ShoppingGoods.allIsRecommemdIndexs);
        map.put("allIsRecommendCarousels", ShoppingGoods.allIsRecommendCarousels);
        map.put("allStatuss", ShoppingGoods.allStatuss);
        map.put("STATUS1", ShoppingGoods.STATUS1);
        map.put("STATUS3", ShoppingGoods.STATUS3);
        map.put("STATUS88", ShoppingGoods.STATUS88);
        map.put("maxSortNo", Integer.MAX_VALUE);
        //没有推荐到首页
        map.put("ISRECOMMEMDINDEX0", ShoppingGoods.ISRECOMMEMDINDEX0);
        //没有推荐到轮播
        map.put("ISRECOMMENDCAROUSEL0", ShoppingGoods.ISRECOMMENDCAROUSEL0);
        map.put("TYPE1", ShoppingRecommend.TYPE1);
        map.put("TYPE2", ShoppingRecommend.TYPE2);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/shopgoods/shop_goods_list_page";
        }else{
        	return "tiles.module.shopgoods.shop_goods_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="shop_goods_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,Long id,Long appId){
    	
	    	if(null != id){//修改
	    		ShoppingGoods shoppingGoods = shoppingGoodsService.get(id).getData();
	    		if(null == appId)appId = shoppingGoods.getAppId();
	    		request.setAttribute("item",shoppingGoods);
	    	}
	    	SessionUser sessionUser = SessionFace.getSessionUser(request);
	    	List<AppInfoDto> allApps = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
	    	request.setAttribute("allApps",allApps);
	    	if(null == appId){
	    		appId = allApps.get(0).getId();
	    	}
	    	List<ShoppingCategoryDto> categoryDtos = this.findCategoryByAppId(request, appId).getData();
	    	request.setAttribute("categoryDtos",categoryDtos);
	    	request.setAttribute("defaultAppId",appId);
	    	
	    	Map<String,Object> conditions = Maps.newHashMap();
	    	conditions.put("EQ_appId",appId);
	    	conditions.put("EQ_status",ShoppingFareTemplate.STATUS3);
	    	List<ShoppingFareTemplateDto> tmps = shoppingFareTemplateService.findConditions(conditions).getData();
	    	request.setAttribute("tmps",tmps);
	    	return getReturnUrl(request,map,"tiles.module.shopgoods.shop_goods_detail");
    }

    @ResponseBody
    @RequestMapping(value="common/shop_categoy_appid")
    public Response<List<ShoppingCategoryDto>> findCategoryByAppId(HttpServletRequest request,@RequestParam Long appId){
		Map<String,Object> conditions = Maps.newHashMap();
		Map<String,Boolean> orderMap = Maps.newLinkedHashMap();
		conditions.put("EQ_appId",appId);
		conditions.put("EQ_status",ShoppingCategory.STATUS3);
		orderMap.put("sortNo",true);
		orderMap.put("updateTime", false);
		orderMap.put("id",false);
		return shoppingCategoryService.getList(conditions, orderMap);
    }
    
    //增加-保存
    @RequestMapping(value ="shop_goods_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,String shoppingGoods){
	    	
	    	JsonMapper jm = new JsonMapper();
	    	jm.getMapper().setDateFormat(DateUtil.sdf_YYYY_MM_DD_HH_MM_SS);
	    	ShoppingGoods goods = jm.fromJson(shoppingGoods,ShoppingGoods.class);
	    	if(null == goods){
	    		return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
	    	}
	    	SessionUser sessionUser = SessionFace.getSessionUser(request);
	    	Date now = Dates.now();
	    	goods.setUpdateTime(now);
	    	goods.setUpdator(sessionUser.getNickname());
	    	goods.setUpdatorId(sessionUser.getUserId());
	    	if(null == goods.getId()){
	    		goods.setCreateTime(now);
	    		goods.setCreator(sessionUser.getNickname());
	    		goods.setCreatorId(sessionUser.getUserId());
	    	}
        //ID
        Response<Void> res = shoppingGoodsService.updateShoppingGoods(goods);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //删除
    @RequestMapping(value ="shop_goods_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shoppingGoodsService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="shop_goods_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shoppingGoodsService.deleteLogic(ids);
        return res;
    }
    
    /**
     * <p>Description: 商城配置</p>
     * @author Tangtao on 2016年12月2日
     * @param request
     * @param model
     * @param appId
     * @return
     */
    @RequestMapping(value = "shopping_app_config", method = RequestMethod.GET)
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
    	
    	//获取商城app配置
    	Response<List<CommentAppConfig>> response1 = commentAppConfigService.getByAppAndType(appId, BusinessType.SOURCE_TYPE_13);
    	//获取商城系统配置
    	Response<List<CommentConfig>> response2 = commentConfigService.getByType(BusinessType.SOURCE_TYPE_13);
    	//只展示已存在数据的配置项
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
        return "tiles.module.shopping.shopping_app_config";
    }
    
    /**
     * <p>Description: 保存商城配置</p>
     * @author Tangtao on 2016年12月2日
     * @param request
     * @param appId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "shopping_app_config_save", method = {RequestMethod.POST})
    public Response<Void> configSave(HttpServletRequest request, Long appId) {
    	Response<Void> response = Response.newInstance();
    	Map<String, String[]> map = request.getParameterMap();
    	Map<Long, Byte> parma = Maps.newHashMap();
    	for (String configKey : map.keySet()) {
			if (!configKey.startsWith("shoppingConfig_")) {
				continue;
			}
			parma.put(Long.valueOf(configKey.split("_")[1]), Byte.valueOf(map.get(configKey)[0]));
		}
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	appId = appId == null ? sessionUser.getAppId() : appId;
    	response = commentAppConfigService.save(appId, sessionUser.getUserId(), sessionUser.getNickname(), parma, BusinessType.SOURCE_TYPE_13);
    	return response;
    }
    
    /**
     * 修改排序号
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月2日下午3:47:07
     */
    @RequestMapping(value ="/common/update_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Integer sortNo){
        Response<Void> res = shoppingGoodsService.updateSortNo(null==sortNo?Integer.MAX_VALUE:sortNo,id);
        return res;
    }
    
  //上线
    @RequestMapping(value ={"on_line"})
    @ResponseBody
    public Response<Void> onLine(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shoppingGoodsService.updateStatus(ShoppingGoods.STATUS3,ids);
        return res;
    }
    
    //下线
    @RequestMapping(value = {"out_line"})
    @ResponseBody
    public Response<Void> outLine(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shoppingGoodsService.updateStatus(ShoppingGoods.STATUS88,ids);
        return res;
    }
    
    /**
     * 跳转到推荐到首页
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月13日上午10:44:19
     */
    @RequestMapping(value = {"recommemdIndex","recommendCarousel"}, method = RequestMethod.GET)
    public String toRecommend(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Byte type){
        ShoppingRecommend recommend = new ShoppingRecommend();
        recommend.setShoppingGoodsId(id);
        map.put("item", recommend);
        map.put("type", type);
        map.put("TYPE1", ShoppingRecommend.TYPE1);
        map.put("TYPE2", ShoppingRecommend.TYPE2);
        return getReturnUrl(request,map,"tiles.module.shopgoods.recommend");
    }
    
    /**
     * 推荐到首页
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @RequestMapping(value = {"recommemdIndex"})
    @ResponseBody
    public Response<Void> updateIsRecommemdIndex(HttpServletRequest request,ShoppingRecommend recommend) {
        if(null!=recommend&&null!=recommend.getShoppingGoodsId()){
            getShoppingRecommend(request, recommend);
            return shoppingGoodsService.updateIsRecommemdIndex(ShoppingGoods.ISRECOMMEMDINDEX1,recommend);
        }else{
            return new Response<Void>(-1,"商品信息有误，请刷新重试！");
        }
    }
    
    private Response<Void> getShoppingRecommend(HttpServletRequest request,ShoppingRecommend recommend){
        ShoppingGoods goods = shoppingGoodsService.get(recommend.getShoppingGoodsId()).getData();
        if(null==goods){
            return new Response<Void>(-1,"商品不存在，请刷新页面！");
        }
        recommend.setAppId(goods.getAppId());
        /** 排序号 */
        recommend.setSortNo(Integer.MAX_VALUE);
        /** 状态 */
        recommend.setStatus(ShoppingRecommend.STATUS1);
        /** 创建时间 */
        recommend.setCreateTime(new Date());;
        SessionUser user = SessionFace.getSessionUser(request);
        /** 创建人 */
        recommend.setCreatorId(user.getUserId());
        /** 创建人姓名 */
        recommend.setCreator(user.getNickname());
        /** 更新时间 */
        recommend.setUpdateTime(recommend.getCreateTime());
        /** 更新人ID */
        recommend.setUpdatorId(user.getUserId());
        /** 更新人 */
        recommend.setUpdator(user.getNickname());
        return null;
    }

    /**
     * 推荐到轮播
     * @author huxiaoping
     * @param sortNo
     * @param ids
     * @return
     */
    @RequestMapping(value = {"recommendCarousel"})
    @ResponseBody
    public Response<Void> updateIsRecommendCarousel(HttpServletRequest request,ShoppingRecommend recommend) {
        if(null!=recommend&&null!=recommend.getShoppingGoodsId()){
            getShoppingRecommend(request, recommend);
            return shoppingGoodsService.updateIsRecommendCarousel(ShoppingGoods.ISRECOMMENDCAROUSEL1,recommend);
        }else{
            return new Response<Void>(-1,"商品信息有误，请刷新重试！");
        }
    }
}