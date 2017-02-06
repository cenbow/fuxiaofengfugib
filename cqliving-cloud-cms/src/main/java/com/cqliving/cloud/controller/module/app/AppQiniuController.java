package com.cqliving.cloud.controller.module.app;

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
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.domain.AppQiniu;
import com.cqliving.cloud.online.app.service.AppQiniuService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/app")
public class AppQiniuController extends CommonController {

    @Autowired
    private AppQiniuService appQiniuService;

    //列表
    @RequestMapping(value ="app_qiniu_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
		
        PageInfo<AppQiniu> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", AppQiniu.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", appQiniuService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", AppQiniu.allStatuss);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/app/app_qiniu_list_page";
        }else{
        	return "tiles.module.app.app_qiniu_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="app_qiniu_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.app.app_qiniu_detail";
    }


    //增加-保存
    @RequestMapping(value ="app_qiniu_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,AppQiniu appQiniu){
        //主键
        appQiniu.setId(null);
        Response<Void> res = appQiniuService.save(appQiniu);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="app_qiniu_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AppQiniu appQiniu = appQiniuService.get(id).getData();
        if(appQiniu==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", appQiniu);
        return "tiles.module.app.app_qiniu_detail";
    }

    //修改-保存
    @RequestMapping(value ="app_qiniu_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,AppQiniu appQiniu){
        Response<Void> res = Response.newInstance();
        if(appQiniu==null || appQiniu.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            AppQiniu sourceAppQiniu = appQiniuService.get(appQiniu.getId()).getData();
            if(sourceAppQiniu==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //APP_ID
            sourceAppQiniu.setAppId(appQiniu.getAppId());
            //七牛云服务资源名称
            sourceAppQiniu.setBucketName(appQiniu.getBucketName());
            //七牛提供的测试域名
            sourceAppQiniu.setDomainTest(appQiniu.getDomainTest());
            //绑定的自定义域名
            sourceAppQiniu.setDomainCustom(appQiniu.getDomainCustom());
            //状态
            sourceAppQiniu.setStatus(appQiniu.getStatus());
            //创建时间
            sourceAppQiniu.setCreateTime(appQiniu.getCreateTime());
            //创建人ID
            sourceAppQiniu.setCreatorId(appQiniu.getCreatorId());
            //创建人名称
            sourceAppQiniu.setCreator(appQiniu.getCreator());
            //更新人ID
            sourceAppQiniu.setUpdatorId(appQiniu.getUpdatorId());
            //更新人
            sourceAppQiniu.setUpdator(appQiniu.getUpdator());
            //更新时间
            sourceAppQiniu.setUpdateTime(appQiniu.getUpdateTime());
            res= appQiniuService.save(sourceAppQiniu);
            appQiniu = sourceAppQiniu;
        }catch (Exception ex){
            logger.error("Save Method (Update) AppQiniu Error : " + appQiniu.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="app_qiniu_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AppQiniu appQiniu = appQiniuService.get(id).getData();
        if(appQiniu==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", appQiniu);
        return "tiles.module.app.app_qiniu_view";
    }

    //删除
    @RequestMapping(value ="app_qiniu_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = appQiniuService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="app_qiniu_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = appQiniuService.deleteLogic(ids);
        return res;
    }
}
