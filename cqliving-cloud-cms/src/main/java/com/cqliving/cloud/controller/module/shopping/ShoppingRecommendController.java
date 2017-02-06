package com.cqliving.cloud.controller.module.shopping;

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
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.shopping.domain.ShoppingRecommend;
import com.cqliving.cloud.online.shopping.dto.ShoppingRecommendDto;
import com.cqliving.cloud.online.shopping.service.ShoppingRecommendService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/module/shopping_recommend")
public class ShoppingRecommendController extends CommonController {

    @Autowired
    private ShoppingRecommendService shoppingRecommendService;
    @Autowired
    private AppInfoService appInfoService;

    //商城首页列表
    @RequestMapping(value ="shopping_recommend_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
        
        queryList(request, map, ShoppingRecommend.TYPE1);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/shopping/shopping_recommend_list_page";
        }else{
        	return "tiles.module.shopping.shopping_recommend_list";
        }
    }
    
    //轮播图管理列表
    @RequestMapping(value ="shuffling_figure_list")
    public String shuffling_figure_list(HttpServletRequest request, Map<String, Object> map,
            @RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        queryList(request, map, ShoppingRecommend.TYPE2);
        
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
            return "/module/shopping/shuffling_figure_list_page";
        }else{
            return "tiles.module.shopping.shuffling_figure_list";
        }
    }
    
    /**
     * 查询列表数据
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月23日下午3:02:25
     */
    private void queryList(HttpServletRequest request, Map<String, Object> map,Byte type){
        Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        
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
        searchMap.put("EQ_type", type);
        //排序
        Map<String, Boolean> orderMap = new LinkedHashMap<String, Boolean>();
        orderMap.put("sortNo", true);
        orderMap.put("id", true);
        
        PageInfo<ShoppingRecommendDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", ShoppingRecommend.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", shoppingRecommendService.queryDtoForPage(pageInfo, searchMap, orderMap).getData());
        
        map.put("maxSortNo", Integer.MAX_VALUE);
        map.put("allStatuss", ShoppingRecommend.allStatuss);
        //已下线
        map.put("STATUS3", ShoppingRecommend.STATUS3);
        map.put("STATUS88", ShoppingRecommend.STATUS88);
        map.put("STATUS1", ShoppingRecommend.STATUS1);
        
    }

    //查看
    @RequestMapping(value ={"shopping_recommend_view","shuffling_figure_view"})
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ShoppingRecommendDto shoppingRecommend = shoppingRecommendService.getById(id).getData();
        if(shoppingRecommend==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", shoppingRecommend);
        map.put("allStatuss", ShoppingRecommend.allStatuss);
        map.put("maxSortNo", Integer.MAX_VALUE);
        return getReturnUrl(request,map,"tiles.module.shopping.shopping_recommend_view");
    }

    //删除
    @RequestMapping(value ={"shopping_recommend_delete","shuffling_figure_delete"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = shoppingRecommendService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ={"shopping_recommend_delete_batch","shuffling_figure_delete_batch"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shoppingRecommendService.deleteLogic(ids);
        return res;
    }
    
    //修改排序号
    @RequestMapping(value ="/common/update_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Integer sortNo){
        Response<Void> res = shoppingRecommendService.updateSortNo(null==sortNo?Integer.MAX_VALUE:sortNo,id);
        return res;
    }
    
    //上线
    @RequestMapping(value ={"on_line","shuffling_figure_on_line"})
    @ResponseBody
    public Response<Void> onLine(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shoppingRecommendService.updateStatus(ShoppingRecommend.STATUS3,ids);
        return res;
    }
    
    //下线
    @RequestMapping(value = {"out_line","shuffling_figure_out_line"})
    @ResponseBody
    public Response<Void> outLine(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = shoppingRecommendService.updateStatus(ShoppingRecommend.STATUS88,ids);
        return res;
    }
}
