package com.cqliving.cloud.controller.module.security;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
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
import com.cqliving.cloud.online.security.domain.SysMenu;
import com.cqliving.cloud.online.security.domain.SysResType;
import com.cqliving.cloud.online.security.domain.SysResource;
import com.cqliving.cloud.online.security.dto.SysMenuDto;
import com.cqliving.cloud.online.security.service.SysResTypeService;
import com.cqliving.cloud.security.service.SysMenuService;
import com.cqliving.cloud.security.service.SysResourceService;
import com.cqliving.framework.utils.mapper.JsonMapper;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.utils.PinyinUtil;



@Controller
@RequestMapping(value = "/module/security")
public class SysMenuController extends CommonController {

    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private SysResTypeService sysResTypeService;

    //列表
    @RequestMapping(value ="sys_menu_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
                                    @RequestParam(value="id", required=false) Long id
                                    ) {
    	
        List<SysMenuDto> list = sysMenuService.findAllSysMenu();

        map.put("menuJson", JsonMapper.nonDefaultMapper().toJson(list));
        
        Map<String, Object> searchMap = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("sortNum", true);
        searchMap.put("EQ_restype", "URL");
        searchMap.put("EQ_status",SysResource.STATUS1);
        //获取资源"URL",(byte)1)
        String resList =  JsonMapper.nonDefaultMapper().toJson(sysResourceService.findByGroupResType(searchMap, sortMap));
        map.put("rescJson",resList);
        
        List<SysResType> sysResTypes = sysResTypeService.findExistsRes().getData();
        map.put("sysResTypes",sysResTypes);
        map.put("sysResTypesJson",JsonMapper.nonDefaultMapper().toJson(sysResTypes));
        return "tiles.module.security.new_sys_menu_list";
    }

    //增加-保存
    @RequestMapping(value ="sys_menu_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Long> postAdd(HttpServletRequest request, Map<String, Object> map,
                                SysMenu sysMenu){
    	
    	Response<Long> rp = new Response<Long>();
        try{
            //主键ID
            sysMenu.setId(null);
            //拼音
            sysMenu.setTitleFirstSpell(PinyinUtil.cn2FirstSpell(sysMenu.getTitle()));
            sysMenu.setCreateDate(new Date());
            sysMenuService.save(sysMenu);
        }catch (Exception ex){
            logger.error("Save Method (inster) SysMenu Error : " + sysMenu.toString(), ex);
            //增加失败
            
            rp.setCode(ErrorCodes.FAILURE);
            rp.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
            
            return rp;
        }
        //操作提示
       rp.setData(sysMenu.getId());
        return rp;
    }

    //修改-保存
    @RequestMapping(value ="sys_menu_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,
                                SysMenu sysMenu){
        if(sysMenu==null ||
                sysMenu.getId()==null){
            //没有记录
            return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            SysMenu sourceSysMenu = sysMenuService.get(sysMenu.getId());
            if(sourceSysMenu==null){
                //没有记录
                return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //菜单名称
            sourceSysMenu.setTitle(sysMenu.getTitle());
            //菜单图标
            sourceSysMenu.setIcon(sysMenu.getIcon());
            //显示方式
            sourceSysMenu.setShowMode(sysMenu.getShowMode());
            //描述
            sourceSysMenu.setDescn(sysMenu.getDescn());
            //状态
            sourceSysMenu.setStatus(sysMenu.getStatus());
            //资源
            sourceSysMenu.setResourceId(sysMenu.getResourceId());
            //拼音
            sourceSysMenu.setTitleFirstSpell(PinyinUtil.cn2FirstSpell(sysMenu.getTitle()));

            sysMenuService.update(sourceSysMenu);
            sysMenu = sourceSysMenu;
        }catch (Exception ex){
            logger.error("Save Method (Update) SysMenu Error : " + sysMenu.toString(), ex);
            //修改失败
            return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new Response<Void>();
    }


    //删除
    @RequestMapping(value ="sys_menu_del")
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,
                         @RequestParam(value = "id") Long id){

        try{

            if(0==sysMenuService.deleteTree(id)){
                //没有记录
                return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
        }catch (Exception ex){
            logger.error("Del Method (Del) SysMenu Error : " + id, ex);
            //删除失败提示
            return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.DEL_FAILURE));
        }
        //操作提示
        return new Response<Void>();
    }


    //修改排序和结构
    @RequestMapping(value ="sys_menu_sort")
    @ResponseBody
    public Response<Void> sort(HttpServletRequest request, Map<String, Object> map,
                             @RequestParam(value = "ids[]") Long[] ids,
                             @RequestParam(value = "sortNums[]") Integer[] sortNums,
                             @RequestParam(value = "parentIds[]") Long[] parentIds){
        try{
            sysMenuService.sort(ids, sortNums, parentIds);
        }catch (Exception ex){
            logger.error("Save Method (sort) SysMenu Error ids : " + Arrays.toString(ids), ex);
            //修改排序失败提示
            return new Response<Void>(ErrorCodes.FAILURE, new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        //操作提示
        return new Response<Void>();
    }



}
