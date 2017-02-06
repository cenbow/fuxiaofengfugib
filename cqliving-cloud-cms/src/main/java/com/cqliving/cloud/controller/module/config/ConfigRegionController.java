package com.cqliving.cloud.controller.module.config;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.cqliving.cloud.online.config.domain.ConfigRegion;
import com.cqliving.cloud.online.config.dto.ConfigRegionDto;
import com.cqliving.cloud.online.config.service.ConfigRegionService;
import com.cqliving.cloud.online.shop.domain.ShopType;
import com.cqliving.cloud.online.shop.service.ShopTypeService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.utils.PinyinUtil;
import com.google.common.collect.Maps;
/**
 * 
 * <p>Title:ConfigRegionController </p>
 * <p>Description: 区域信息</p>
 * <p>Company: </p>
 * @author huxiaoping 2016年7月27日下午5:26:17
 *
 */
@Controller
@RequestMapping(value = "/module/config_region")
public class ConfigRegionController extends CommonController {

    @Autowired
    private ConfigRegionService configRegionService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private ShopTypeService shopTypeService;

    //问政区域列表
    @RequestMapping(value = "wz_config_region_list")
    public String wz_list(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
        map.put("type", ConfigRegion.TYPE2);
        map.put("navigation", "问政区域管理");
        map.put("action", "wz_config_region_list.html");
        
        return to_list(request, map, isAjaxPage, ConfigRegion.TYPE2);
    }
    
    //商情区域列表
    @RequestMapping(value = "shop_config_region_list")
    public String shop_list(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        map.put("type", ConfigRegion.TYPE3);
        map.put("navigation", "商情区域管理");
        map.put("action", "shop_config_region_list.html");
        
        return to_list(request, map, isAjaxPage, ConfigRegion.TYPE3);
    }
    
    //旅游区域列表
    @RequestMapping(value = "tourism_config_region_list")
    public String tourism_list(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "p", required = false) String isAjaxPage
            ) {
        map.put("type", ConfigRegion.TYPE10);
        map.put("navigation", "旅游区域管理");
        map.put("action", "tourism_config_region_list.html");
        
        return to_list(request, map, isAjaxPage, ConfigRegion.TYPE10);
    }
    
    private String to_list(HttpServletRequest request, Map<String, Object> map,
            String isAjaxPage,Byte type){
        Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        searchMap.put("NOTEQ_status", ConfigRegion.STATUS99);//排除逻辑删除状态
        searchMap.put("EQ_type", type);//区分类型（商情/问政）
        
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        //排序号，正序
        sortMap.put("sortNo", true);
        //创建时间倒序
        sortMap.put("createTime", false);
        //id倒序
        sortMap.put("id", false);
        //数据权限
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        //去掉查询所有的功能
        //userDate(user, appList, searchMap);
        if(ConfigRegion.TYPE2.equals(type)){
            userDate(user, appList, searchMap);
            PageInfo<ConfigRegion> pageInfo = getPageInfo(request);
            map.put("pageInfo", configRegionService.queryForPage(pageInfo, searchMap, sortMap).getData());
        }else{
            String searchAppid = (String)searchMap.get("EQ_appId");
            if(StringUtils.isBlank(searchAppid)){
                if(null!=appList&&appList.size()>0){
                    searchMap.put("EQ_appId", appList.get(0).getId());
                }else{
                    searchMap.put("EQ_appId", user.getAppId());
                }
            }
            //userDate(user, appList, searchMap);
            PageInfo<ConfigRegionDto> pageInfo = getPageInfo(request);
            map.put("pageInfo", configRegionService.queryByPage(pageInfo, searchMap, sortMap).getData());
        }
        
        map.put("allTypes", ConfigRegion.allTypes);
        map.put("TYPE1", ConfigRegion.TYPE2);
        map.put("TYPE2", ConfigRegion.TYPE3);
        map.put("TYPE10", ConfigRegion.TYPE10);
        //排序号默认最大值
        map.put("maxSortNo", Integer.MAX_VALUE);
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
            return "/module/config/config_region_list_page";
        }else{
            return "tiles.module.config.config_region_list";
        }
    }

    //增加-查看
    @RequestMapping(value ={"wz_config_region_add","shop_config_region_add","tourism_config_region_add"}, method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,Byte type){
        ConfigRegion configRegion = new ConfigRegion();
        //不传type 默认是问政
        configRegion.setType(null==type?ConfigRegion.TYPE2:type);
        map.put("TYPE1", ConfigRegion.TYPE2);
        map.put("TYPE2", ConfigRegion.TYPE3);
        map.put("TYPE10", ConfigRegion.TYPE10);
        map.put("item", configRegion);
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        map.put("maxSortNo", Integer.MAX_VALUE);
    	return getReturnUrl(request,map,"tiles.module.config.config_region_detail");
    }


    //增加-保存
    @RequestMapping(value ={"wz_config_region_add","shop_config_region_add","tourism_config_region_add"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ConfigRegion configRegion){
        //主键ID
        configRegion.setId(null);
        //客户端ID
        configRegion.setAppId(null==configRegion.getAppId()?SessionFace.getSessionUser(request).getAppId():configRegion.getAppId());
        //区域CODE
        configRegion.setCode("1");
        //区域CODE
        configRegion.setPcode("1");
        //层级，默认为一级
        configRegion.setLevel(1);
        //区域名称全拼
        configRegion.setFullName(PinyinUtil.cn2Spell(configRegion.getName()));
        //区域名称拼音首字母
        configRegion.setPhoneticizeAb(PinyinUtil.cn2FirstSpell(configRegion.getName()));
        //区域名称拼音全拼
        configRegion.setPhoneticize(PinyinUtil.cn2Spell(configRegion.getName()));
        //创建时间
        configRegion.setCreateTime(new Date());
        //区域类型
        configRegion.setType(null==configRegion.getType()?ConfigRegion.TYPE2:configRegion.getType());
        //状态
        configRegion.setStatus(ConfigRegion.STATUS3);
        configRegion.setSortNo(null==configRegion.getSortNo()?Integer.MAX_VALUE:configRegion.getSortNo());
        Response<Void> res = configRegionService.save(configRegion);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ={"wz_config_region_update","shop_config_region_update","tourism_config_region_update"}, method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        ConfigRegion configRegion = configRegionService.get(id).getData();
        if(configRegion==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configRegion);
        map.put("TYPE1", ConfigRegion.TYPE2);
        map.put("TYPE2", ConfigRegion.TYPE3);
        map.put("TYPE10", ConfigRegion.TYPE10);
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        map.put("maxSortNo", Integer.MAX_VALUE);
        return getReturnUrl(request,map,"tiles.module.config.config_region_detail");
    }

    //修改-保存
    @RequestMapping(value ={"wz_config_region_update","shop_config_region_update","tourism_config_region_update"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ConfigRegion configRegion){
        Response<Void> res = Response.newInstance();
        if(configRegion==null || configRegion.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ConfigRegion sourceConfigRegion = configRegionService.get(configRegion.getId()).getData();
            if(sourceConfigRegion==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端ID
            sourceConfigRegion.setAppId(configRegion.getAppId());
            //名称
            sourceConfigRegion.setName(configRegion.getName());
            //排序号
            sourceConfigRegion.setSortNo(configRegion.getSortNo()==null?sourceConfigRegion.getSortNo():configRegion.getSortNo());
            //区域名称全拼
            sourceConfigRegion.setFullName(PinyinUtil.cn2Spell(configRegion.getName()));
            //区域名称拼音首字母
            sourceConfigRegion.setPhoneticizeAb(PinyinUtil.cn2FirstSpell(configRegion.getName()));
            //区域名称拼音全拼
            sourceConfigRegion.setPhoneticize(PinyinUtil.cn2Spell(configRegion.getName()));
            //区域类型
            sourceConfigRegion.setType(null==configRegion.getType()?sourceConfigRegion.getType():configRegion.getType());
            //商情分类
            sourceConfigRegion.setShopTypeId(null==configRegion.getShopTypeId()?sourceConfigRegion.getShopTypeId():configRegion.getShopTypeId());
            //排序号被改成空就设置为最大值
            sourceConfigRegion.setSortNo(null==configRegion.getSortNo()?Integer.MAX_VALUE:configRegion.getSortNo());
            res= configRegionService.save(sourceConfigRegion);
            configRegion = sourceConfigRegion;
        }catch (Exception ex){
            logger.error("Save Method (Update) ConfigRegion Error : " + configRegion.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ={"wz_config_region_view","shop_config_region_view","tourism_config_region_view"})
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Byte type){
        //update by huxiaoping 前台去掉详情查看功能，若要恢复，需要根据type关联查询app信息和商情分类（商情才查）
        ConfigRegion configRegion = configRegionService.get(id).getData();
        if(configRegion==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", configRegion);
        return getReturnUrl(request,map,"tiles.module.config.config_region_view");
    }

    //删除
    @RequestMapping(value ={"wz_config_region_delete","shop_config_region_delete","tourism_config_region_delete"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = configRegionService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ={"wz_config_region_delete_batch","shop_config_region_delete_batch","tourism_config_region_delete_batch"}, method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = configRegionService.deleteLogic(ids);
        return res;
    }
    
    /**
     * 加载分类下拉列表
     * @Description
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年7月8日
     */
    @RequestMapping(value ="/common/load_type_select")
    public String loadTypeSelect(HttpServletRequest request, Map<String, Object> map,Long appId,Long shopTypeId,Boolean isList){
        PageInfo<ShopType> pageInfo = new PageInfo<ShopType>(Integer.MAX_VALUE, 1);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("updateTime", false);
        sortMap.put("id", false);
        Map<String, Object> searchMap = new TreeMap<String, Object>();
        searchMap.put("EQ_appId", appId==null?SessionFace.getSessionUser(request).getAppId():appId);
        searchMap.put("NOTEQ_status", ShopType.STATUS99);//排除逻辑删除状态
        shopTypeService.queryForPage(pageInfo, searchMap, sortMap);
        if(null!=pageInfo&&null!=pageInfo.getPageResults()&&pageInfo.getPageResults().size()>0){
            map.put("typeList", pageInfo.getPageResults());
        }
        map.put("isList", isList==null?false:true);
        map.put("shopTypeId", shopTypeId);
        return "/module/config/shop_type_select";
    }
    
    //修改排序号
    @RequestMapping(value ="/common/update_type_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Integer sortNo){
        Response<Void> res = configRegionService.updateSortNo(null==sortNo?Integer.MAX_VALUE:sortNo,id);
        return res;
    }
}
