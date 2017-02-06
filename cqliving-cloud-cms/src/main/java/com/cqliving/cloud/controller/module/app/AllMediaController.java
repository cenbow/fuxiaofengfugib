package com.cqliving.cloud.controller.module.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
import com.cqliving.cloud.online.app.domain.AllMedia;
import com.cqliving.cloud.online.app.domain.AppColumns;
import com.cqliving.cloud.online.app.dto.AllMediaDto;
import com.cqliving.cloud.online.app.dto.AppColumnsDto;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AllMediaService;
import com.cqliving.cloud.online.app.service.AppColumnsService;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.security.domain.SysUserData;
import com.cqliving.cloud.online.security.service.SysUserDataService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.JsonMapper;
import com.cqliving.tool.common.util.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/all_media")
public class AllMediaController extends CommonController {

    @Autowired
    private AllMediaService allMediaService;
    @Autowired
    AppInfoService appInfoService;
    @Autowired
    AppColumnsService appColumnsService; 
    @Autowired
    SysUserDataService sysUserDataService;
    
    private void initData(HttpServletRequest request){
    	
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	List<AppInfoDto> apps = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
		request.setAttribute("apps",apps);
    }
    
    //列表
    @RequestMapping(value ="all_media_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	List<AppInfoDto> apps = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
		request.setAttribute("apps",apps);
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        PageInfo<AllMediaDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", AllMedia.STATUS99);//排除逻辑删除状态
        if(null == searchMap.get("EQ_appId")){
        	searchMap.put("EQ_appId",apps.get(0).getId());
        }
        sortMap.put("sortNo",true);
        sortMap.put("updateTime",false);
        map.put("pageInfo", allMediaService.queryPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allTypes", AllMedia.allTypes);
        map.put("allStatuss", AllMedia.allStatuss);
        initData(request);
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/app/all_media_list_page";
        }else{
        	return "tiles.module.app.all_media_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="all_media_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	initData(request);
    	return getReturnUrl(request,map,"tiles.module.app.all_media_detail");
    }

    //增加-保存
    @RequestMapping(value ="all_media_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,AllMedia allMedia){
    	
    	Date now = Dates.now();
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        allMedia.setId(null);
        allMedia.setCreateTime(now);
        allMedia.setCreatorId(sessionUser.getUserId());
        allMedia.setCreator(sessionUser.getNickname());
        allMedia.setUpdatorId(sessionUser.getUserId());
        allMedia.setUpdator(sessionUser.getNickname());
        allMedia.setUpdateTime(now);
        allMedia.setSortNo(Integer.MAX_VALUE);
        allMedia.setStatus(AllMedia.STATUS3);
        allMedia.setTopColumnsIndex(getTopColIndex(allMedia));
        Response<Void> res = allMediaService.save(allMedia);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="common/all_media_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AllMedia allMedia = allMediaService.get(id).getData();
        if(allMedia==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        initData(request);
        map.put("item", allMedia);
        map.put("jsonObj", JsonMapper.nonDefaultMapper().toJson(allMedia));
        return getReturnUrl(request,map,"tiles.module.app.all_media_detail");
    }

    //修改-保存
    @RequestMapping(value ="common/all_media_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,AllMedia allMedia){
        Response<Void> res = Response.newInstance();
        if(allMedia==null || allMedia.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            AllMedia sourceAllMedia = allMediaService.get(allMedia.getId()).getData();
            if(sourceAllMedia==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            Date now = Dates.now();
        	SessionUser sessionUser = SessionFace.getSessionUser(request);
        	sourceAllMedia.setUpdateTime(now);
        	sourceAllMedia.setUpdatorId(sessionUser.getUserId());
        	sourceAllMedia.setUpdator(sessionUser.getNickname());
            //客户端_ID
            sourceAllMedia.setAppId(allMedia.getAppId());
            //全媒体名称
            sourceAllMedia.setName(allMedia.getName());
            //2倍图标地址
            sourceAllMedia.setMinImageUrl(allMedia.getMinImageUrl());
            //3倍图标地址
            sourceAllMedia.setMaxImageUrl(allMedia.getMaxImageUrl());
            //类型
            sourceAllMedia.setType(allMedia.getType());
            //当type=1时该值有效
            sourceAllMedia.setLinkUrl(allMedia.getLinkUrl());
            //栏目ID，app_columns表主键。当type=2或type=3时该值必填
            sourceAllMedia.setColumnsId(allMedia.getColumnsId());
            //当type=3时该值有效。栏目ID的对应的顶层栏目所在的索引位置，索引从0开始
            sourceAllMedia.setTopColumnsIndex(getTopColIndex(allMedia));
            res= allMediaService.save(sourceAllMedia);
            allMedia = sourceAllMedia;
        }catch (Exception ex){
            logger.error("Save Method (Update) AllMedia Error : " + allMedia.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    private Integer getTopColIndex(AllMedia allMedia){
	    	if(null == allMedia)return null;
	    	Byte type = allMedia.getType();
	    	if( AllMedia.TYPE1.byteValue() !=type.byteValue()){
	    		Map<String,Object> conditions = Maps.newHashMap();
	    		Map<String,Boolean> sortMap = Maps.newLinkedHashMap();
	    		conditions.put("EQ_parentId", 0);
	    		conditions.put("EQ_appId", allMedia.getAppId());
	    		sortMap.put("sortNo", true);
	    		sortMap.put("id",true);
	    		List<AppColumnsDto> columnsData = appColumnsService.getList(conditions, sortMap).getData();
	    		AppColumns ac = appColumnsService.get(allMedia.getColumnsId()).getData();
	    		String code = ac.getCode();
	    		String[] codes = code.split("\\.");
	    		if(codes.length >=2){
	    			ac = appColumnsService.get(StringUtil.stringToLong(codes[0])).getData();
	    		}
	    		for(int i = 0;i<columnsData.size();i++){
	    			AppColumnsDto data = columnsData.get(i);
	    			if(data.getId().longValue() == ac.getId().longValue()){
	    				return i;
	    			}
	    		}
	    	}
	    	return null;
    }
    
    
    //查看
    @RequestMapping(value ="all_media_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AllMedia allMedia = allMediaService.get(id).getData();
        if(allMedia==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", allMedia);
        return getReturnUrl(request,map,"tiles.module.app.all_media_view");
    }

    //删除
    @RequestMapping(value ="all_media_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = allMediaService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="common/all_media_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = allMediaService.deleteLogic(ids);
        return res;
    }
    
    @RequestMapping(value="update_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request,@RequestParam Long id,@RequestParam Integer sortNo){
    	
    	return allMediaService.updateSortNo(id, sortNo);
    }
    
    @RequestMapping(value="common/load_type_content")
    public String changeType(HttpServletRequest request,byte type,Long columnsId,Long appId,String linkUrl){
    	
    	if(1 != type){
    		SessionUser sessionUser = SessionFace.getSessionUser(request);
    		Map<String,Object> conditions = Maps.newHashMap();
        	conditions.put("EQ_appId",null != appId ? appId : sessionUser.getAppId());
        	if(AllMedia.TYPE2.byteValue() == type){
        		conditions.put("EQ_columnsType", AppColumns.COLUMNSTYPE0);
        	}
        	conditions.put("appendAll",false);
        	//过滤没有栏目权限的
        	conditions.put("filter_sysUserDataValue",sysUserDataService.findValueByUserId(sessionUser.getUserId(), SysUserData.TYPE2).getData());
        	List<AppColumnsDto> allColumns = appColumnsService.getByConditions(conditions).getData();
        	List<AppColumnsDto> selectdto = Lists.newArrayList();
        	this.iteratorColumns(allColumns, columnsId,selectdto);
        	request.setAttribute("selectAppColumn",CollectionUtils.isNotEmpty(selectdto) ?  selectdto.get(0) : null);
        	request.setAttribute("appColumnsJson",JsonMapper.nonDefaultMapper().toJson(allColumns));
    	}
    	request.setAttribute("type",type);
    	request.setAttribute("linkUrl",linkUrl);
    	return "/module/app/all_media_detail_type";
    }
    
    private void iteratorColumns(List<AppColumnsDto> appColumns,Long columnsId,List<AppColumnsDto> selectdto){
    	//获取已选中的栏目名称
    	if(CollectionUtils.isEmpty(appColumns) || null == columnsId)return;
    	Iterator<AppColumnsDto> it = appColumns.iterator();
    	while(it.hasNext()){
    		AppColumnsDto itdto = it.next();
    		if(itdto.getId().longValue() == columnsId.longValue()){
    			itdto.getState().setSelected(true);
    			selectdto.add(itdto);
    			return;
    		}
    		if(CollectionUtils.isNotEmpty(itdto.getNodes())){
    		    iteratorColumns(new ArrayList<AppColumnsDto>(itdto.getNodes()),columnsId,selectdto);
    		}
    	}
    }
}
