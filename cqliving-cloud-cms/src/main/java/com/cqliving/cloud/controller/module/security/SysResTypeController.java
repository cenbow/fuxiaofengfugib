package com.cqliving.cloud.controller.module.security;

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
import com.cqliving.cloud.online.security.domain.SysResType;
import com.cqliving.cloud.online.security.service.SysResTypeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/security")
public class SysResTypeController extends CommonController {

    @Autowired
    private SysResTypeService sysResTypeService;

    //列表
    @RequestMapping(value ="sys_res_type_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        
		
        PageInfo<SysResType> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", SysResType.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", sysResTypeService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allStatuss", SysResType.allStatuss);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/security/sys_res_type_list_page";
        }else{
        	return "tiles.module.security.sys_res_type_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="sys_res_type_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        return "tiles.module.security.sys_res_type_detail";
    }


    //增加-保存
    @RequestMapping(value ="sys_res_type_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,SysResType sysResType){
        //主键ID
        sysResType.setId(null);
        Response<Void> res = sysResTypeService.save(sysResType);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="sys_res_type_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        SysResType sysResType = sysResTypeService.get(id).getData();
        if(sysResType==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysResType);
        return "tiles.module.security.sys_res_type_detail";
    }

    //修改-保存
    @RequestMapping(value ="sys_res_type_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,SysResType sysResType){
        Response<Void> res = Response.newInstance();
        if(sysResType==null || sysResType.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            SysResType sourceSysResType = sysResTypeService.get(sysResType.getId()).getData();
            if(sourceSysResType==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //资源名称
            sourceSysResType.setName(sysResType.getName());
            //排序值
            sourceSysResType.setSortNum(sysResType.getSortNum());
            //状态
            sourceSysResType.setStatus(sysResType.getStatus());
            //创建时间
            sourceSysResType.setCreateDate(sysResType.getCreateDate());
            res= sysResTypeService.save(sourceSysResType);
            sysResType = sourceSysResType;
        }catch (Exception ex){
            logger.error("Save Method (Update) SysResType Error : " + sysResType.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="sys_res_type_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        SysResType sysResType = sysResTypeService.get(id).getData();
        if(sysResType==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", sysResType);
        return "tiles.module.security.sys_res_type_view";
    }

    //删除
    @RequestMapping(value ="sys_res_type_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = sysResTypeService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="sys_res_type_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = sysResTypeService.deleteLogic(ids);
        return res;
    }
}
