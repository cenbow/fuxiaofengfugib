package com.cqliving.cloud.controller.module.shop;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.dto.ShopTypeDto;
import com.cqliving.cloud.online.shop.service.ShopTypeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/shop_type")
public class ShopTypeController extends CommonController {

	@Autowired
	private AppColumnsService appColumnsService;
	@Autowired
	private AppInfoService appInfoService;
    @Autowired
    private ShopTypeService shopTypeService;

    //列表
    @RequestMapping(value ="shop_type_list")
    public String list(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "p", required = false) String isAjaxPage) {
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
        //获取操作用户数据权限范围
    	List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	if (CollectionUtils.isNotEmpty(appInfoDtos) && appInfoDtos.size() > 1) {
    		map.put("appList", appInfoDtos);
    	}
    	Long appId = sessionUser.getAppId();
    	if (appId == null) {	//超管，默认获取第一个app
    		appId = appInfoDtos.get(0).getId();
		}
    	map.put("search_EQ_appId", appId);
    	if (!searchMap.containsKey("EQ_appId")) {
    		searchMap.put("EQ_appId", appId);
        } 
		
        PageInfo<ShopTypeDto> pageInfo = getPageInfo(request);
        searchMap.put("EQ_status", ShopType.STATUS3);
        sortMap.put("id", false);
        map.put("pageInfo", shopTypeService.queryDtoForPage(pageInfo, searchMap, sortMap).getData());
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/shop/shop_type_list_page";
        }else{
        	return "tiles.module.shop.shop_type_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="shop_type_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map) {
    	SessionUser sessionUser= SessionFace.getSessionUser(request);
    	//获取操作用户数据权限范围
    	List<AppInfoDto> appInfoDtos = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	if (CollectionUtils.isNotEmpty(appInfoDtos) && appInfoDtos.size() > 1) {
    		map.put("appList", appInfoDtos);
    	}
    	Long appId = sessionUser.getAppId();
    	if (appId == null) {	//超管，默认获取第一个app
    		appId = appInfoDtos.get(0).getId();
		}
    	map.put("appId", appId);
    	
    	//获取商情栏目
    	List<Long> appIdList = Lists.newArrayList();
    	if (CollectionUtils.isNotEmpty(appInfoDtos)) {
			for (AppInfoDto dto : appInfoDtos) {
				appIdList.add(dto.getId());
			}
		} else {
			appIdList.add(appId);
		}
    	Map<String, Object> conditions = Maps.newHashMap();
    	conditions.put("EQ_status", AppColumns.STATUS3);
    	conditions.put("IN_appId", appIdList);
    	conditions.put("EQ_templetCode", "O2O_SHOP_PLF");
    	Map<String, Boolean> orderMap = Maps.newLinkedHashMap();
    	orderMap.put("appId", true);
    	orderMap.put("sortNo", true);
    	orderMap.put("name", true);
    	List<AppColumns> columns = appColumnsService.queryForList(conditions, orderMap).getData();
    	map.put("columns", columns);
        return "tiles.module.shop.shop_type_detail";
    }


    //增加-保存
    @RequestMapping(value ="shop_type_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map, ShopType shopType) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	String creator = sessionUser.getNickname();
    	Long userId = sessionUser.getUserId();
    	
    	Date now = DateUtil.now();
        //ID
        shopType.setId(null);
        shopType.setCreateTime(now);
        shopType.setCreator(creator);
        shopType.setCreatorId(userId);
        shopType.setStatus(ShopType.STATUS3);
        shopType.setUpdateTime(now);
        shopType.setUpdator(creator);
        shopType.setUpdatorId(userId);
        Response<Void> res = shopTypeService.save(shopType);
        if (res.getCode() < 0) {
        	return new Response<Void>(-1, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value = "shop_type_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id) {
        ShopType shopType = shopTypeService.get(id).getData();
        if (shopType == null) {
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shopType);
        
        //获取商情栏目
    	List<Long> appIdList = Lists.newArrayList();
		appIdList.add(shopType.getAppId());
    	Map<String, Object> conditions = Maps.newHashMap();
    	conditions.put("EQ_status", AppColumns.STATUS3);
    	conditions.put("IN_appId", appIdList);
    	conditions.put("EQ_templetCode", "O2O_SHOP_PLF");
    	Map<String, Boolean> orderMap = Maps.newLinkedHashMap();
    	orderMap.put("appId", true);
    	orderMap.put("sortNo", true);
    	orderMap.put("name", true);
    	List<AppColumns> columns = appColumnsService.queryForList(conditions, orderMap).getData();
    	map.put("columns", columns);
        return "tiles.module.shop.shop_type_detail";
    }

    //修改-保存
    @RequestMapping(value ="shop_type_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ShopType shopType){
        Response<Void> res = Response.newInstance();
        if(shopType==null || shopType.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ShopType sourceShopType = shopTypeService.get(shopType.getId()).getData();
            if(sourceShopType==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            SessionUser sessionUser = SessionFace.getSessionUser(request);
            //栏目
            sourceShopType.setAppColumnsId(shopType.getAppColumnsId());
            //分类名称
            sourceShopType.setName(shopType.getName());
            //分类CODE
//            sourceShopType.setCode(shopType.getCode());
            //更新时间
            sourceShopType.setUpdateTime(DateUtil.now());
            //更新人ID
            sourceShopType.setUpdatorId(sessionUser.getUserId());
            //更新人
            sourceShopType.setUpdator(sessionUser.getNickname());
            res= shopTypeService.save(sourceShopType);
            shopType = sourceShopType;
        }catch (Exception ex){
            logger.error("Save Method (Update) ShopType Error : " + shopType.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="shop_type_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShopType shopType = shopTypeService.get(id).getData();
        if(shopType==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shopType);
        return "tiles.module.shop.shop_type_view";
    }

    //删除
    @RequestMapping(value ="shop_type_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shopTypeService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="shop_type_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shopTypeService.deleteLogic(ids);
        return res;
    }
}
