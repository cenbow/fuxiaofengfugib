package com.cqliving.cloud.controller.module.config;

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
import com.cqliving.cloud.online.config.domain.RecommendApp;
import com.cqliving.cloud.online.config.dto.RecommendAppDto;
import com.cqliving.cloud.online.config.service.RecommendAppService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/recommend_app")
public class RecommendAppController extends CommonController {

	@Autowired
	private AppInfoService appInfoService;
    @Autowired
    private RecommendAppService recommendAppService;

    //列表
    @RequestMapping(value ="recommend_app_list")
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
        
        PageInfo<RecommendAppDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", RecommendApp.STATUS99);//排除逻辑删除状态
        sortMap.put("sortNo", true);
        sortMap.put("recommendAppId", true);
        map.put("pageInfo", recommendAppService.queryDtoForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", RecommendApp.allStatuss);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/config/recommend_app_list_page";
        }else{
        	return "tiles.module.config.recommend_app_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="recommend_app_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	return getReturnUrl(request,map,"tiles.module.config.recommend_app_detail");
    }


    //增加-保存
    @RequestMapping(value ="recommend_app_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,RecommendApp recommendApp){
        //ID
        recommendApp.setId(null);
        Response<Void> res = recommendAppService.save(recommendApp);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="recommend_app_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        RecommendApp recommendApp = recommendAppService.get(id).getData();
        if(recommendApp==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", recommendApp);
        return getReturnUrl(request,map,"tiles.module.config.recommend_app_detail");
    }

    //修改-保存
    @RequestMapping(value ="recommend_app_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,RecommendApp recommendApp){
        Response<Void> res = Response.newInstance();
        if(recommendApp==null || recommendApp.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            RecommendApp sourceRecommendApp = recommendAppService.get(recommendApp.getId()).getData();
            if(sourceRecommendApp==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端ID,APP_INFO表ID，一个客户端上面可以有多个推荐客户端
            sourceRecommendApp.setAppId(recommendApp.getAppId());
            //推荐客户端ID,APP_INFO表ID
            sourceRecommendApp.setRecommendAppId(recommendApp.getRecommendAppId());
            //推荐客户端的栏目表ID,app_columns表的ID
            sourceRecommendApp.setColumnsId(recommendApp.getColumnsId());
            //排序号
            sourceRecommendApp.setSortNo(recommendApp.getSortNo());
            //状态
            sourceRecommendApp.setStatus(recommendApp.getStatus());
            //创建时间
            sourceRecommendApp.setCreateTime(recommendApp.getCreateTime());
            //创建人ID
            sourceRecommendApp.setCreatorId(recommendApp.getCreatorId());
            //创建人名称
            sourceRecommendApp.setCreator(recommendApp.getCreator());
            //更新时间
            sourceRecommendApp.setUpdateTime(recommendApp.getUpdateTime());
            //更新人ID
            sourceRecommendApp.setUpdatorId(recommendApp.getUpdatorId());
            //更新人
            sourceRecommendApp.setUpdator(recommendApp.getUpdator());
            res= recommendAppService.save(sourceRecommendApp);
            recommendApp = sourceRecommendApp;
        }catch (Exception ex){
            logger.error("Save Method (Update) RecommendApp Error : " + recommendApp.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="recommend_app_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        RecommendApp recommendApp = recommendAppService.get(id).getData();
        if(recommendApp==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", recommendApp);
        return getReturnUrl(request,map,"tiles.module.config.recommend_app_view");
    }

    //删除
    @RequestMapping(value ="recommend_app_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = recommendAppService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="recommend_app_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = recommendAppService.deleteLogic(ids);
        return res;
    }
 
    /**
     * <p>Description: 上线</p>
     * @author Tangtao on 2016年10月26日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "online", method = RequestMethod.POST)
    public Response<Void> online(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
        Response<Void> res = recommendAppService.updateStatus(RecommendApp.STATUS3, id);
        return res;
    }
    
    /**
     * <p>Description: 下线</p>
     * @author Tangtao on 2016年10月26日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "offline", method = RequestMethod.POST)
    public Response<Void> offline(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
    	Response<Void> res = recommendAppService.updateStatus(RecommendApp.STATUS88, id);
    	return res;
    }
    
    /**
     * <p>Description: 修改排序</p>
     * @author Tangtao on 2016年10月26日
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
    	Response<Void> res = recommendAppService.modifySortNo(id, sortNo);
    	return res;
    }
    
}