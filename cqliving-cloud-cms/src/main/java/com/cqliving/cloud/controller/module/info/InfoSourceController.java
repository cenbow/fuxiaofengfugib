package com.cqliving.cloud.controller.module.info;

import java.util.Date;
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
import com.cqliving.cloud.online.info.domain.InfoSource;
import com.cqliving.cloud.online.info.dto.InfoSourceDto;
import com.cqliving.cloud.online.info.service.InfoSourceService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.Response;

@Controller
@RequestMapping(value = "/module/info")
public class InfoSourceController extends CommonController {

    @Autowired
    private InfoSourceService infoSourceService;
    @Autowired
    AppInfoService appInfoService;
    
    private void init(HttpServletRequest request){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	List<AppInfoDto> appDto = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
    	request.setAttribute("allApps",appDto);
    }
    
    //列表
    @RequestMapping(value ="info_source_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	init(request);//初始化数据
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        PageInfo<InfoSourceDto> pageInfo = getPageInfo(request);
        map.put("pageInfo", infoSourceService.queryForPage(pageInfo, searchMap).getData());
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/infosource/info_source_list_page";
        }else{
        	return "tiles.module.infosource.info_source_list";
        }
    }

    //增加修改-查看
    @RequestMapping(value ="info_source_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map,Long id){
    	
    	this.init(request);//初始化数据
    	if(null != id){
    		request.setAttribute("item", this.infoSourceService.get(id).getData());
    	}
    	return getReturnUrl(request,map,"tiles.module.infosource.info_source_detail");
    }

    //增加修改-保存
    @RequestMapping(value ="info_source_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,InfoSource infoSource){
    	
    	if(null == infoSource){
    		return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
    	}
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Date now = Dates.now();
    	
    	infoSource.setUpdateTime(now);
    	infoSource.setUpdator(sessionUser.getNickname());
    	infoSource.setUpdatorId(sessionUser.getUserId());
    	infoSource.setStatus(InfoSource.status3);
    	if(null == infoSource.getId()){
    		infoSource.setCreateTime(now);
    		infoSource.setCreator(sessionUser.getNickname());
    		infoSource.setCreatorId(sessionUser.getUserId());
    		infoSource.setSortNo(Integer.MAX_VALUE);
    	}else{
    		InfoSource sqlsource = infoSourceService.get(infoSource.getId()).getData();
    		infoSource.setCreateTime(sqlsource.getCreateTime());
    		infoSource.setCreator(sqlsource.getCreator());
    		infoSource.setCreatorId(sqlsource.getCreatorId());
    		infoSource.setStatus(sqlsource.getStatus());
    		infoSource.setSortNo(sqlsource.getSortNo());
    	}
        Response<Void> res = infoSourceService.save(infoSource);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //删除
    @RequestMapping(value ="info_source_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = infoSourceService.updateStatus(InfoSource.status99,id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="common/info_source_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = infoSourceService.updateStatus(InfoSource.status99,ids);
        return res;
    }
    
    //修改排序号
    @RequestMapping(value ="info_source_sort", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request,@RequestParam(value = "id") Long id,Integer sortNo){
    	Response<Void> res = infoSourceService.updateSortNo(id,sortNo);
        return res;
    }
}
