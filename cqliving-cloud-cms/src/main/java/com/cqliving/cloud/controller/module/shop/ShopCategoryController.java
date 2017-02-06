package com.cqliving.cloud.controller.module.shop;

import java.util.Arrays;
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
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.shop.domain.ShopCategory;
import com.cqliving.cloud.online.shop.dto.ShopCategoryDto;
import com.cqliving.cloud.online.shop.service.ShopCategoryService;
import com.cqliving.cloud.online.shop.service.ShopTypeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/shop_category")
public class ShopCategoryController extends CommonController {

	@Autowired
	private AppInfoService appInfoService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private ShopTypeService shopTypeService;

    //列表
    @RequestMapping(value ="shop_category_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
        PageInfo<ShopCategoryDto> pageInfo = getPageInfo(request);
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
        searchMap.put("NOTEQ_status", ShopCategory.STATUS99);//排除逻辑删除状态
        
        //排序条件
        if (searchMap.containsKey("EQ_typeId") && StringUtils.isNotBlank(String.valueOf(searchMap.get("EQ_typeId")))) {
        	map.put("typeId", searchMap.get("EQ_typeId"));
        	sortMap.put("sort_no", true);
        } else {
        	sortMap.put("type_id", true);
        }
        sortMap.put("id", false);
        map.put("pageInfo", shopCategoryService.queryDtoForPage(pageInfo, searchMap, sortMap).getData());
        //查询商情类型集合
        map.put("allTypes", shopTypeService.getAll().getData());
	                    
        //查询按钮和点击页面是ajax操作。
        if (StringUtils.isNotBlank(isAjaxPage)) {
        	return "/module/shop/shop_category_list_page";
        } else {
        	return "tiles.module.shop.shop_category_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="shop_category_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
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
    	//查询商情类型集合
    	map.put("allTypes", shopTypeService.getAll().getData());
        return "tiles.module.shop.shop_category_detail";
    }


    //增加-保存
    @RequestMapping(value ="shop_category_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ShopCategory shopCategory) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	String creator = sessionUser.getNickname();
    	Long userId = sessionUser.getUserId();
    	
    	Date now = DateUtil.now();
        //ID
        shopCategory.setId(null);
        shopCategory.setCreateTime(now);
        shopCategory.setCreator(creator);
        shopCategory.setCreatorId(userId);
        shopCategory.setSortNo(Integer.MAX_VALUE);
        shopCategory.setStatus(ShopCategory.STATUS3);
        shopCategory.setUpdateTime(now);
        shopCategory.setUpdator(creator);
        shopCategory.setUpdatorId(userId);
        Response<Void> res = shopCategoryService.save(shopCategory);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="shop_category_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShopCategory shopCategory = shopCategoryService.get(id).getData();
        if (shopCategory==null) {
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shopCategory);
        //查询商情类型集合
        Long appId = shopCategory.getAppId();
        map.put("allTypes", shopTypeService.getByApp(appId).getData());
        return "tiles.module.shop.shop_category_detail";
    }

    //修改-保存
    @RequestMapping(value ="shop_category_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ShopCategory shopCategory){
        Response<Void> res = Response.newInstance();
        if (shopCategory == null || shopCategory.getId() == null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try {
            ShopCategory sourceShopCategory = shopCategoryService.get(shopCategory.getId()).getData();
            if (sourceShopCategory == null) {
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            SessionUser sessionUser = SessionFace.getSessionUser(request);
            //类型ID,shop_type表的ID
            sourceShopCategory.setTypeId(shopCategory.getTypeId());
            //分类名称
            sourceShopCategory.setName(shopCategory.getName());
            //更新时间
            sourceShopCategory.setUpdateTime(DateUtil.now());
            //更新人ID
            sourceShopCategory.setUpdatorId(sessionUser.getUserId());
            //更新人
            sourceShopCategory.setUpdator(sessionUser.getNickname());
            res= shopCategoryService.save(sourceShopCategory);
            shopCategory = sourceShopCategory;
        } catch (Exception ex) {
            logger.error("Save Method (Update) ShopCategory Error : " + shopCategory.toString(), ex);
            //修改失败
            return new Response<Void>(-1, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="shop_category_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShopCategory shopCategory = shopCategoryService.get(id).getData();
        if(shopCategory==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shopCategory);
        return "tiles.module.shop.shop_category_view";
    }

    //删除
    @RequestMapping(value ="shop_category_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shopCategoryService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="shop_category_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shopCategoryService.deleteLogic(ids);
        return res;
    }
    
    /**
     * <p>Description: 修改排序</p>
     * @author Tangtao on 2016年6月17日
     * @param request
     * @param map
     * @param id
     * @param sortNo
     * @return
     */
    @ResponseBody
    @RequestMapping({"modify_sort_no"})
    public Response<Void> modifySortNo(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("i") Long id, 
    		@RequestParam(value = "s", required = false) Integer sortNo) {
    	Response<Void> response = shopCategoryService.modifySortNo(id, sortNo);
    	return response;
    }
    
    /**
     * <p>Description: 清空排序</p>
     * @author Tangtao on 2016年6月17日
     * @param request
     * @param map
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping({"clear_order_batch"})
    public Response<Void> clearSortNo(HttpServletRequest request, Map<String, Object> map, @RequestParam("ids[]") Long[] ids){
    	Response<Void> response = shopCategoryService.clearSortNo(Arrays.asList(ids));
    	return response;
    }
    
}