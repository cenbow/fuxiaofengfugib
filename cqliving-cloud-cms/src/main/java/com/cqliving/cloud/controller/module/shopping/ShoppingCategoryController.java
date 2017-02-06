package com.cqliving.cloud.controller.module.shopping;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.shopping.domain.ShoppingCategory;
import com.cqliving.cloud.online.shopping.dto.ShoppingCategoryDto;
import com.cqliving.cloud.online.shopping.service.ShoppingCategoryService;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/module/shopping_category")
public class ShoppingCategoryController extends CommonController {

    @Autowired
    private ShoppingCategoryService shoppingCategoryService;
    @Autowired
    private AppInfoService appInfoService;

    //列表
    @RequestMapping(value ="shopping_category_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        searchMap.put("NOTEQ_status", ShoppingCategory.STATUS99);
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        //处理App查询条件以及页面上展示的app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList&&appList.size()>1){
            map.put("appList", appList);
        }
        if(StringUtils.isBlank(searchAppid)){
           if(null!=appList&&appList.size()>0){
               searchMap.put("EQ_appId", appList.get(0).getId());
           }else{
               searchMap.put("EQ_appId", user.getAppId());
           }
        }
        
        //排序
        Map<String, Boolean> sortMap = new LinkedHashMap<String, Boolean>();
        sortMap.put("sortNo", true);
        sortMap.put("updateTime", false);
        sortMap.put("id", false);
		
        List<ShoppingCategoryDto> list = shoppingCategoryService.queryTreeMode(searchMap, sortMap).getData();
        
        JsonMapper jsonMapper = new JsonMapper();
        map.put("categoryList",jsonMapper.toJson(list));
        	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/shopping_category/shopping_category_list_page";
        }else{
        	return "tiles.module.shopping_category.shopping_category_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="to_add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, Map<String, Object> map,ShoppingCategory shoppingCategory){
        map.put("item", shoppingCategory);
    	return "tiles.module.shopping_category.shopping_category_detail";
    }


    //增加-保存
    @RequestMapping(value ="shopping_category_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ShoppingCategory shoppingCategory){
        //ID
        shoppingCategory.setId(null);
        SessionUser user = SessionFace.getSessionUser(request);
        shoppingCategory.setAppId(null==shoppingCategory.getAppId()?user.getAppId():shoppingCategory.getAppId());
        Date now = new Date();
        shoppingCategory.setCreateTime(now);
        //创建人ID
        shoppingCategory.setCreatorId(user.getUserId());
        //创建人名称
        shoppingCategory.setCreator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
        shoppingCategory.setUpdateTime(now);
        //更新人ID
        shoppingCategory.setUpdatorId(user.getUserId());
        //分类级别
        shoppingCategory.setLevel(null==shoppingCategory.getLevel()?1:shoppingCategory.getLevel());
        //父级ID
        shoppingCategory.setParentId(null==shoppingCategory.getParentId()?0l:shoppingCategory.getParentId());
        //updator
        shoppingCategory.setUpdator(shoppingCategory.getCreator());
        shoppingCategory.setStatus(ShoppingCategory.STATUS3);
        
        Response<Void> res = shoppingCategoryService.saveCategory(shoppingCategory);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="shopping_category_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShoppingCategory shoppingCategory = shoppingCategoryService.get(id).getData();
        if(shoppingCategory==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shoppingCategory);
        return "tiles.module.shopping_category.shopping_category_detail";
    }

    //修改-保存
    @RequestMapping(value ="shopping_category_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ShoppingCategory shoppingCategory){
        Response<Void> res = Response.newInstance();
        if(shoppingCategory==null || shoppingCategory.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ShoppingCategory sourceShoppingCategory = shoppingCategoryService.get(shoppingCategory.getId()).getData();
            if(sourceShoppingCategory==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端_ID
            sourceShoppingCategory.setAppId(shoppingCategory.getAppId());
            //分类级别
            sourceShoppingCategory.setLevel(shoppingCategory.getLevel());
            //父级ID
            sourceShoppingCategory.setParentId(shoppingCategory.getParentId());
            //名称
            sourceShoppingCategory.setName(shoppingCategory.getName());
            //图片地址
            sourceShoppingCategory.setImgUrl(shoppingCategory.getImgUrl());
            //更新时间
            sourceShoppingCategory.setUpdateTime(new Date());
            SessionUser user = SessionFace.getSessionUser(request);
            //更新人ID
            sourceShoppingCategory.setUpdatorId(user.getUserId());
            //更新人
            sourceShoppingCategory.setUpdator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
            res= shoppingCategoryService.save(sourceShoppingCategory);
            if(res.getCode() < 0){
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
            }
            shoppingCategory = sourceShoppingCategory;
        }catch (Exception ex){
            logger.error("Save Method (Update) ShoppingCategory Error : " + shoppingCategory.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="shopping_category_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShoppingCategory shoppingCategory = shoppingCategoryService.get(id).getData();
        if(shoppingCategory==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shoppingCategory);
        return "tiles.module.shopping_category.shopping_category_view";
    }

    //删除
    @RequestMapping(value ="shopping_category_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "ids[]") Long[] ids){
        Response<Void> res = shoppingCategoryService.deleteLogic(ids);
        return res;
    }
    
    //修改排序和结构
    @RequestMapping(value ="/common/update_sort")
    @ResponseBody
    public Response<Void> sort(HttpServletRequest request, Map<String, Object> map,
                           @RequestParam(value = "ids[]") Long[] ids,
                           @RequestParam(value = "sortNums[]") Integer[] sortNums,
                           @RequestParam(value = "parentIds[]") Long[] parentIds){
        try{
            shoppingCategoryService.sort(ids, sortNums, parentIds);
        }catch (Exception ex){
            logger.error("修改排序失败 : " + Arrays.toString(ids), ex);
            //修改排序失败提示
            return new Response<Void>(ErrorCodes.FAILURE, "修改排序失败");
        }
        //操作提示
        return new Response<Void>();
    }
    
    /**
     * Title:获取商品分类下拉列表
     * <p>Description:</p>
     * @author huxiaoping on 2016-11-22
     * @param request
     * @param map
     * @param appId
     * @return
     */
    @RequestMapping(value ="common/getLevelOne")
    public String loadLevelOne(HttpServletRequest request, Map<String, Object> map,Long appId){
        //排序
        Map<String, Boolean> orderMap = new LinkedHashMap<String, Boolean>();
        orderMap.put("sortNo", true);
        orderMap.put("id", true);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("EQ_appId", null==appId?SessionFace.getSessionUser(request).getAppId():appId);
        //查询一级分类
        queryMap.put("EQ_parentId", 0l);
        List<ShoppingCategory> categoryList = shoppingCategoryService.queryList(queryMap, orderMap).getData();
        map.put("categoryList", categoryList);
        return "/module/shopping/shopping_category_one";
    }
    
    /**
     * Title:获取商品分类下拉列表(非一级)
     * <p>Description:</p>
     * @author huxiaoping on 2016-12-2
     * @param request
     * @param map
     * @param appId
     * @return
     */
    @RequestMapping(value ="common/getLevel")
    public String loadLevel(HttpServletRequest request, Map<String, Object> map,Long appId){
        //排序
        Map<String, Boolean> orderMap = new LinkedHashMap<String, Boolean>();
        orderMap.put("sortNo", true);
        orderMap.put("id", true);
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("EQ_appId", null==appId?SessionFace.getSessionUser(request).getAppId():appId);
        //查询非一级分类
        queryMap.put("NOTEQ_parentId", 0l);
        List<ShoppingCategory> categoryList = shoppingCategoryService.queryList(queryMap, orderMap).getData();
        map.put("categoryList", categoryList);
        return "/module/shopping/shopping_category_one";
    }
}
