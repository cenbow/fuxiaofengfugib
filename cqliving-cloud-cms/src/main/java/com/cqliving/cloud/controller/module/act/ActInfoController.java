package com.cqliving.cloud.controller.module.act;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.common.constant.BusinessType;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.dto.ActInfoDto;
import com.cqliving.cloud.online.act.service.ActInfoService;
import com.cqliving.cloud.online.app.domain.AppInfo;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.config.domain.CommentAppConfig;
import com.cqliving.cloud.online.config.domain.CommentConfig;
import com.cqliving.cloud.online.config.service.CommentAppConfigService;
import com.cqliving.cloud.online.config.service.CommentConfigService;
import com.cqliving.cloud.online.interfacc.dto.ActExportDto;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.service.SysUserDataService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.file.ExcelEntityUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/act")
public class ActInfoController extends CommonController {

    @Autowired
    private ActInfoService actInfoService;
    @Autowired
    SysUserDataService sysUserDataService;
    @Autowired
    AppInfoService appInfoService;
    @Autowired
    private CommentAppConfigService commentAppConfigService;
    @Autowired
    private CommentConfigService commentConfigService;
    
    private void init(HttpServletRequest request){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	
    	List<AppInfo> listApps = null;
    	List<SysUserData> listSysUserData = sysUserDataService.findByUserId(sessionUser.getUserId(),SysUserData.TYPE1).getData();
    	
    	if(!CollectionUtils.isEmpty(listSysUserData)){
    		List<Long> ids = Lists.newArrayList();
    		for(SysUserData sysUserData : listSysUserData){
    			ids.add(sysUserData.getValue());
    		}
    		listApps = appInfoService.findByIds(ids).getData();
    	}else if(null == sessionUser.getAppId() || 0 == sessionUser.getAppId()){
    		listApps = appInfoService.getAll().getData();
    	}
    	//所有APP
    	request.setAttribute("allApps", listApps);
    	request.setAttribute("dateNow",Dates.now());
    	request.setAttribute("actListStatus",ActInfoList.allStatuss);
    	request.setAttribute("actListType",ActInfoList.allTypes);
    	request.setAttribute("actShowType",ActInfoList.allShowTypes);
    	request.setAttribute("TYPE3",ActInfoList.TYPE3);
    }
    
    //列表
    @RequestMapping(value ="act_infolist")
    public String list(HttpServletRequest request, Map<String, Object> map,
        @RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_startTime", required=false) Date search_GTE_startTime
        ,@RequestParam(value="search_LT_endTime", required=false) Date search_LT_endTime
        ) {
        
        Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sortNo1", true);
        sortMap.put("updateTime", false);
        sortMap.put("id", false);
        
        PageInfo<ActInfoDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", ActInfo.STATUS99);//排除逻辑删除状态
        
        //数据权限
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        userDate(user, appList, searchMap);
        PageInfo<ActInfoDto> page = actInfoService.queryPage(pageInfo, searchMap, sortMap).getData();
        //去重
        if(null!=page && null!=page.getPageResults() && page.getPageResults().size()>0){
            Set<String> typeSet;Set<String> showTypeSet;
            for (ActInfoDto act : page.getPageResults()) {
                if(StringUtils.isNotBlank(act.getActTypes())){
                    typeSet = new HashSet<String>();
                    for (String type : act.getActTypes().split(",")) {
                        typeSet.add(type);
                    }
                    act.setTypeSet(typeSet);
                }
                if(StringUtils.isNotBlank(act.getShowTypes())){
                    showTypeSet = new HashSet<String>();
                    for (String type : act.getShowTypes().split(",")) {
                        showTypeSet.add(type);
                    }
                    act.setShowTypeSet(showTypeSet);
                }
            }
        }
        map.put("pageInfo", page);
        
        map.put("allTypes", ActInfoList.allTypes);
        map.put("allActivationState", ActInfoList.allStatuss);
        //已激活
        map.put("activationState3", ActInfoList.STATUS3);
        map.put("TYPE1", ActInfoList.TYPE1+"");
        map.put("TYPE2", ActInfoList.TYPE2+"");
        map.put("allTypesStr", ActInfoList.allTypesStr);
        map.put("allShowTypesStr", ActInfoList.allShowTypesStr);
        map.put("allStatuss", ActInfo.allStatuss);
        map.put("STATUS99", ActInfo.STATUS99);
        map.put("STATUS3", ActInfo.STATUS3);
        map.put("STATUS88", ActInfo.STATUS88);
        map.put("STATUS1", ActInfo.STATUS1);
        map.put("ISRECOMMEND0", ActInfo.ISRECOMMEND0);
        map.put("ISRECOMMEND1", ActInfo.ISRECOMMEND1);
                        
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
            return "/module/act/act_infolist_page";
        }else{
            return "tiles.module.act.act_infolist";
        }
    }

    //增加-查看
    @RequestMapping(value ="act_infoadd", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,Long id){
    	
    	if(null != id && 0 != id){
    		ActInfoDto actInfo = actInfoService.findById(id).getData();
            if(actInfo==null){
                //没有记录
                return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
            }
            map.put("item", actInfo);
    	}
    	//初始化数据
    	init(request);
        return "tiles.module.act.act_infodetail";
    }

    //修改-保存
    @RequestMapping(value ="act_infoupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<ActInfoDto> postUpdate(HttpServletRequest request, Map<String, Object> map,ActInfoDto actInfoDto){
        
    	//开始时间不得小于结束时间
    	Date startTime = actInfoDto.getStartTime();
    	Date endTime = actInfoDto.getEndTime();
    	if(!startTime.equals(endTime) && endTime.before(startTime)){
    		return new Response<ActInfoDto>(ErrorCodes.FAILURE,"开始时间不得大于结束时间");
    	}
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	
    	Long userId = sessionUser.getUserId();
    	String nickName = sessionUser.getNickname();
    	Date now = Dates.now();
    	
    	actInfoDto.setCreateTime(now);
    	actInfoDto.setCreator(nickName);
    	actInfoDto.setCreatorId(userId);
    	actInfoDto.setUpdateTime(now);
    	actInfoDto.setUpdator(nickName);
    	actInfoDto.setUpdatorId(userId);
        return actInfoService.saveOrUpdate(actInfoDto);
    }

    //查看
    @RequestMapping(value ="act_infoview")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActInfo actInfo = actInfoService.get(id).getData();
        if(actInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actInfo);
        return "tiles.module.act.act_infoview";
    }

    //删除
    @RequestMapping(value ="act_infodelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
    	
        Response<Void> res = actInfoService.deleteLogic(sessionUser.getNickname(),sessionUser.getUserId(),id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="act_infodelete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
     	
        Response<Void> res = actInfoService.deleteLogic(sessionUser.getNickname(),sessionUser.getUserId(),ids);
        return res;
    }
    
    //上线
    @RequestMapping(value ="on_line")
    @ResponseBody
    public Response<Void> onLine(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
        Response<Void> res = actInfoService.updateStatus(ActInfo.STATUS3,sessionUser.getNickname(),sessionUser.getUserId(),id);
        return res;
    }
    
    //批量上线
    @RequestMapping(value ="on_line_batch")
    @ResponseBody
    public Response<Void> onLineBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
        Response<Void> res = actInfoService.updateStatus(ActInfo.STATUS3,sessionUser.getNickname(),sessionUser.getUserId(),ids);
        return res;
    }
    
    //下线
    @RequestMapping(value ="out_line")
    @ResponseBody
    public Response<Void> outLine(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
        Response<Void> res = actInfoService.updateStatus(ActInfo.STATUS88,sessionUser.getNickname(),sessionUser.getUserId(),id);
        return res;
    }
    
    //批量下线
    @RequestMapping(value ="out_line_batch")
    @ResponseBody
    public Response<Void> outLineBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        
        Response<Void> res = actInfoService.updateStatus(ActInfo.STATUS88,sessionUser.getNickname(),sessionUser.getUserId(),ids);
        return res;
    }
    
    //清空当前页的排序号
    @RequestMapping(value ="clear_sort_no")
    @ResponseBody
    public Response<Void> clearSortNo(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<Void> res = actInfoService.updateSortNo(null,sessionUser.getNickname(),sessionUser.getUserId(),ids);
        return res;
    }
    
    //修改排序号
    @RequestMapping(value ="update_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Integer sortNo){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<Void> res = actInfoService.updateSortNo(sortNo,sessionUser.getNickname(),sessionUser.getUserId(),id);
        return res;
    }
    
    /**
     * 根据类型导出该活动对应的数据
     * @Description 
     * @Company 
     * @parameter actType：活动类型
     * @parameter id：活动id
     * @return
     * @author huxiaoping 2016年6月8日上午10:27:49
     */
    @RequestMapping(value = "export")
    public void export(HttpServletRequest request, HttpServletResponse response, Byte actType,Long id) {
        
//        ExcelEntityUtil.doExportForXls(response, "活动", actList);
    }
    
    //推荐到首页-页面跳转
    @RequestMapping(value ="recommend", method = RequestMethod.GET)
    public String toRecommend(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActInfo actInfo = actInfoService.get(id).getData();
        if(actInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actInfo);
        return getReturnUrl(request,map,"tiles.module.act.act_recommend");
    }
    
    //推荐到首页-保存
    @RequestMapping(value ="recommend", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postRecommend(HttpServletRequest request, Map<String, Object> map,ActInfo actInfo){
        if(null==actInfo||null==actInfo.getId()){
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        if(StringUtils.isBlank(actInfo.getRecommendImageUrl())){
            return new Response<Void>(-1,"请上传推荐到首页图片");
        }
        String recommendImageUrl = actInfo.getRecommendImageUrl();
        
        actInfo = actInfoService.get(actInfo.getId()).getData();
        if(null==actInfo){
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        actInfo.setRecommendImageUrl(recommendImageUrl);
        //ID
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            //更新人ID
            actInfo.setUpdatorId(user.getUserId());
            //更新人
            actInfo.setUpdator(user.getNickname());
            //保存
            Response<Void> res = actInfoService.recommend(actInfo);
            if(res.getCode() < 0){
                return new Response<Void>(-1,"推荐到首页失败");
            }
            res.setMessage("推荐到首页成功");
            return res;
        }else{
            return new Response<Void>(-1,"请先登录"); 
        }        
    }
    
    //取消推送
    @RequestMapping(value ="cancel_recommend")
    @ResponseBody
    public Response<Void> cancelRecommend(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<Void> res = actInfoService.cancelRecommend(sessionUser.getNickname(),sessionUser.getUserId(),id);
        return res;
    }
    
    /**
     * <p>Description: 活动配置</p>
     * @author huxiaoping on 2016年10月19日
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
        Response<List<CommentAppConfig>> response1 = commentAppConfigService.getByAppAndType(appId, BusinessType.SOURCE_TYPE_6);
        //获取系统配置
        Response<List<CommentConfig>> response2 = commentConfigService.getByType(BusinessType.SOURCE_TYPE_6);
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
        return "tiles.module.act.act_app_config";
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
            if (!configKey.startsWith("actConfig_")) {
                continue;
            }
            parma.put(Long.valueOf(configKey.split("_")[1]), Byte.valueOf(map.get(configKey)[0]));
        }
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        appId = appId == null ? sessionUser.getAppId() : appId;
        response = commentAppConfigService.save(appId, sessionUser.getUserId(), sessionUser.getNickname(), parma, BusinessType.SOURCE_TYPE_6);
        return response;
    }
    /**
     * <p>Description: 导出数据</p>
     * @author FangHuiLin on 2016年12月7日
     * @param request
     * @param response
     * @param classfyId
     */
    @RequestMapping(value ={"act_export"})
    public void export(HttpServletRequest request, HttpServletResponse response, Long classfyId) {
       if(classfyId==null){
    	   return;
       }
       
       List<ActExportDto> actExportList= actInfoService.actExportList(classfyId).getData();
    	
       ExcelEntityUtil.doExportForXls(response, "活动投票情况表", actExportList);
    }
}