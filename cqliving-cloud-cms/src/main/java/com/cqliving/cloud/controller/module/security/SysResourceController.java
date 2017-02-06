package com.cqliving.cloud.controller.module.security;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.security.domain.SysResource;
import com.cqliving.cloud.online.security.dto.SysResourceDto;
import com.cqliving.cloud.online.security.service.SysResTypeService;
import com.cqliving.cloud.security.service.SysResourceService;
import com.cqliving.framework.utils.Dates;
import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/security")
public class SysResourceController extends CommonController {

    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    SysResTypeService sysResTypeService;
    
    //列表
    @RequestMapping(value ="sys_resource_list")
    public String list(HttpServletRequest request, Map<String, Object> map,Long sysResTypeId){
        
    	map.put("sysResTypes",sysResTypeService.findExistsRes().getData());
    	
        return "tiles.module.security.new_sys_resource_list";
    }

    @RequestMapping(value="common/find_by_resytype")
    public String findBySysResType(HttpServletRequest request,Long sysResTypeId){
    	
        Map<String,Object> searchMap = Maps.newHashMap();
    	searchMap.put("EQ_sysResTypeId", sysResTypeId);
    	 Map<String,Boolean> orderMap = Maps.newHashMap();
    	 orderMap.put("sortNum",true);
        List<SysResourceDto> list = sysResourceService.findByConditions(searchMap, orderMap);
        request.setAttribute("sysResTypeJson",new JsonMapper().toJson(sysResTypeService.getAll().getData()));
        request.setAttribute("resources",new JsonMapper().toJson(list));
        
    	return "/module/security/sys_type_res_list";
    }
    
    
    //增加-保存
    @RequestMapping(value ="sys_resource_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Long> postAdd(HttpServletRequest request, Map<String, Object> map,
                                SysResource sysResource){
        try{
            //主键ID
            sysResource.setId(null);
            sysResource.setCreateDate(Dates.now());
            sysResourceService.save(sysResource);
            sysResourceService.initAllResourceCache();
        }catch (Exception ex){
            logger.error("Save Method (inster) SysResource Error : " + sysResource.toString(), ex);
            //增加失败
            return new Response<Long>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        Response<Long> jr = new Response<Long>();
        jr.setData(sysResource.getId());
        return jr;
    }

    //修改-保存
    @RequestMapping(value ="sys_resource_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,
                                SysResource sysResource){
        if(sysResource==null ||
                sysResource.getId()==null){
            //没有记录
            return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            SysResource sourceSysResource = sysResourceService.get(sysResource.getId());
            if(sourceSysResource==null){
                //没有记录
                return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            
            //资源名称
            sourceSysResource.setTitle(sysResource.getTitle());
            //资源类型
            sourceSysResource.setRestype(sysResource.getRestype());
            //资源值
            sourceSysResource.setResString(sysResource.getResString());
            //权限
            sourceSysResource.setPermissionValue(sysResource.getPermissionValue());
            //描述
            sourceSysResource.setDescn(sysResource.getDescn());
            //状态
            sourceSysResource.setStatus(sysResource.getStatus());
            //排序
            sourceSysResource.setSortNum(sysResource.getSortNum());
            sourceSysResource.setSysResTypeId(sysResource.getSysResTypeId());
            sysResourceService.update(sourceSysResource);
            
            sysResourceService.updateSysResTypeId(sysResource.getId());
            sysResource = sourceSysResource;
            sysResourceService.initAllResourceCache();
        }catch (Exception ex){
            logger.error("Save Method (Update) SysResource Error : " + sysResource.toString(), ex);
            //修改失败
            return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new Response<Void>();
    }

    //删除
    @RequestMapping(value ="sys_resource_del")
    @ResponseBody
    public Response<Void> status(HttpServletRequest request, Map<String, Object> map,
                                @RequestParam(value = "ids[]") Long[] ids){
        try{
            sysResourceService.removes((Serializable[])ids);
            sysResourceService.initAllResourceCache();
        }catch (Exception ex){
            logger.error("Del Method (Del) SysResource Error : " + Arrays.toString(ids), ex);
            //删除失败提示
            return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.DEL_FAILURE));
        }
        //操作提示
        return new Response<Void>();
    }

    //修改排序和结构
    @RequestMapping(value ="sys_resource_sort")
    @ResponseBody
    public Response<Void> sort(HttpServletRequest request, Map<String, Object> map,
                           @RequestParam(value = "ids[]") Long[] ids,
                           @RequestParam(value = "sortNums[]") Integer[] sortNums,
                           @RequestParam(value = "parentIds[]") Long[] parentIds,
                           @RequestParam(value="sysResTypeIds[]") Long[] sysResTypeIds){
        try{
            sysResourceService.sort(ids, sortNums, parentIds,sysResTypeIds);
            sysResourceService.initAllResourceCache();
        }catch (Exception ex){
            logger.error("Save Method (sort) SysResource Error ids : " + Arrays.toString(ids), ex);
            //修改排序失败提示
            return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new Response<Void>();
    }
	
}
