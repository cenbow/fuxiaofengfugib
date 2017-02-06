package com.cqliving.cloud.controller.module.act;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.cqliving.cloud.online.act.domain.ActCollectInfo;
import com.cqliving.cloud.online.act.domain.ActCollectOption;
import com.cqliving.cloud.online.act.dto.ActCollectInfoDto;
import com.cqliving.cloud.online.act.service.ActCollectInfoService;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.file.ExcelTemplate;
import com.cqliving.tool.common.util.file.UploadExcel;

@Controller
@RequestMapping(value = "/module/act")
public class ActCollectInfoController extends CommonController{
    private static final Logger logger = LoggerFactory.getLogger(ActCollectInfoController.class);
    
    @Autowired
    private ActCollectInfoService actCollectInfoService;
    @Autowired
    AppInfoService appInfoService;
    
    @Resource
    private List<ExcelTemplate> actCollectTemplate;

    //列表
    @RequestMapping(value ="act_collect_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
		Map<String, Boolean> sortMap = new LinkedHashMap<String, Boolean>();
        sortMap.put("updateTime", false);
        
        //数据权限
        String searchAppid = (String)searchMap.get("EQ_appId");
        /*if(StringUtils.isBlank(searchAppid)){
            //处理APP下拉框
            SessionUser user = SessionFace.getSessionUser(request);
            List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
            if(null!=appList && appList.size()>1){
                map.put("appList", appList);
            }
            userDate(user, appList, searchMap);
        }*/
        
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        if(StringUtils.isBlank(searchAppid)){
            //过滤App的处理
            userDate(user, appList, searchMap);
        }
        
        PageInfo<ActCollectInfoDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", actCollectInfoService.queryPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allTypes", ActCollectInfo.allTypes);
        
        map.put("TYPE1", ActCollectInfo.TYPE1);
        map.put("TYPE2", ActCollectInfo.TYPE2);
        map.put("TYPE3", ActCollectInfo.TYPE3);
        map.put("TYPE4", ActCollectInfo.TYPE4);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/act/act_collect_info_list_page";
        }else{
        	return "tiles.module.act.act_collect_info_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="act_collect_info_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,String type){
        
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        
        map.put("allTypes", ActCollectInfo.allTypes);
        map.put("TYPE1", ActCollectInfo.TYPE1);
        map.put("TYPE2", ActCollectInfo.TYPE2);
        map.put("TYPE3", ActCollectInfo.TYPE3);
        map.put("TYPE4", ActCollectInfo.TYPE4);
        map.put("action", "/module/act/act_collect_info_add.html");
        
        try{
            ActCollectInfoDto actCollectInfo = new ActCollectInfoDto();
            actCollectInfo.setType(Byte.valueOf(type.trim()));
            map.put("item", actCollectInfo);
        }catch(Exception e){
            return super.operFailure("操作异常，请退出重新登录后操作!", map);
        }
//        return "/module/act/act_collect_info_detail";
        return getReturnUrl(request,map,"tiles.module.act.act_collect_info_detail");
    }


    //增加-保存
    @RequestMapping(value ="act_collect_info_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ActCollectInfo actCollectInfo,String[] value){
        //ID
        actCollectInfo.setId(null);
        SessionUser user = SessionFace.getSessionUser(request);
        if(null!=user){
            //创建人ID
            actCollectInfo.setCreatorId(user.getUserId());
            //创建人名称
            actCollectInfo.setCreator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
            //更新人ID
            actCollectInfo.setUpdatorId(user.getUserId());
            //updator
            actCollectInfo.setUpdator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
        }
        actCollectInfo.setAppId(null==actCollectInfo.getAppId()?user.getAppId():actCollectInfo.getAppId());
        actCollectInfo.setLength(actCollectInfo.getLength()==null?0:actCollectInfo.getLength());
        Response<Void> res = actCollectInfoService.save(actCollectInfo,value);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="act_collect_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActCollectInfoDto actCollectInfo = actCollectInfoService.findById(id).getData();
        if(actCollectInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actCollectInfo);
        
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        
        map.put("allTypes", ActCollectInfo.allTypes);
        map.put("TYPE1", ActCollectInfo.TYPE1);
        map.put("TYPE2", ActCollectInfo.TYPE2);
        map.put("TYPE3", ActCollectInfo.TYPE3);
        map.put("TYPE4", ActCollectInfo.TYPE4);
        map.put("action", "/module/act/act_collect_info_update.html");
        
        //return "/module/act/act_collect_info_detail";
        return getReturnUrl(request,map,"tiles.module.act.act_collect_info_detail");
    }

    //修改-保存
    @RequestMapping(value ="act_collect_info_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ActCollectInfo actCollectInfo,String[] value){
        Response<Void> res = Response.newInstance();
        if(actCollectInfo==null || actCollectInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ActCollectInfo sourceActCollectInfo = actCollectInfoService.get(actCollectInfo.getId()).getData();
            if(sourceActCollectInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            SessionUser user = SessionFace.getSessionUser(request);
            
            actCollectInfo.setAppId(null==actCollectInfo.getAppId()?sourceActCollectInfo.getAppId():actCollectInfo.getAppId());
            actCollectInfo.setLength(actCollectInfo.getLength()==null?sourceActCollectInfo.getLength():actCollectInfo.getLength());
            //创建时间
            actCollectInfo.setCreateTime(sourceActCollectInfo.getCreateTime());
            //创建人
            actCollectInfo.setCreatorId(sourceActCollectInfo.getCreatorId());
            //创建人姓名
            actCollectInfo.setCreator(sourceActCollectInfo.getCreator());
            //更新人ID
            actCollectInfo.setUpdatorId(user.getUserId());
            //更新人
            actCollectInfo.setUpdator(StringUtils.isBlank(user.getNickname())?user.getUsername():user.getNickname());
            res= actCollectInfoService.save(actCollectInfo,value);
            //actCollectInfo = sourceActCollectInfo;
        }catch (Exception ex){
            logger.error("Save Method (Update) ActCollectInfo Error : " + actCollectInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="act_collect_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ActCollectInfoDto actCollectInfo = actCollectInfoService.findById(id).getData();
        if(actCollectInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", actCollectInfo);
        map.put("allTypes", ActCollectInfo.allTypes);
        map.put("TYPE1", ActCollectInfo.TYPE1);
        map.put("TYPE2", ActCollectInfo.TYPE2);
        map.put("TYPE3", ActCollectInfo.TYPE3);
        map.put("TYPE4", ActCollectInfo.TYPE4);
        //return "/module/act/act_collect_info_view";
        return getReturnUrl(request,map,"tiles.module.act.act_collect_info_view");
    }

    //删除
    @RequestMapping(value ="act_collect_info_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = actCollectInfoService.delById(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="act_collect_info_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = actCollectInfoService.delById(ids);
        return res;
    }
    
    @RequestMapping("importExcel")
    @ResponseBody
    public Response<List<ActCollectOption>> importExcel(HttpServletRequest request, Map<String, Object> map) throws Exception{
        try{
//        ActCollectOption ac = new ActCollectOption();
//        Class<ActCollectOption> t = (Class<ActCollectOption>) Class.forName("com.cqliving.cloud.online.act.domain.ActCollectOption");
            Response<List<ActCollectOption>> res = UploadExcel.importExcel(request, actCollectTemplate, ActCollectOption.class);
            return res;
        }catch(BusinessException e){
            return new Response<List<ActCollectOption>>(-1,e.getMessage());
        }catch(Exception e){
            return new Response<List<ActCollectOption>>(-1,"上传失败，请稍后重试");
        }
    }
}