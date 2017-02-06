package com.cqliving.cloud.controller.module.recruitinfo;

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

import com.cqliving.basic.domain.Option;
import com.cqliving.basic.service.BasicService;
import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitImage;
import com.cqliving.cloud.online.recruitinfo.domain.RecruitInfo;
import com.cqliving.cloud.online.recruitinfo.service.RecruitImageService;
import com.cqliving.cloud.online.recruitinfo.service.RecruitInfoService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/recruit_info")
public class RecruitInfoController extends CommonController {

    @Autowired
    private RecruitInfoService recruitInfoService;
    @Autowired
    private BasicService basicService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private RecruitImageService recruitImageService;

    //列表
    @RequestMapping(value ="recruit_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sortNo", true);
        sortMap.put("id", false);
        
        //处理APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        //数据权限处理
        String searchAppid = (String)searchMap.get("EQ_appId");
        if(StringUtils.isBlank(searchAppid)){
            userDate(user, appList, searchMap);
        }
        
        PageInfo<RecruitInfo> pageInfo = getPageInfo(request);        
        searchMap.put("NOTEQ_status", RecruitInfo.STATUS99);//排除逻辑删除状态
        
        map.put("pageInfo", recruitInfoService.queryForPage(pageInfo, searchMap, sortMap).getData());        
        map.put("allStatuss", RecruitInfo.allStatuss);
        map.put("maxSortNo", Integer.MAX_VALUE);
        map.put("STATUS1", RecruitInfo.STATUS1);
        map.put("STATUS3", RecruitInfo.STATUS3);
        map.put("STATUS88", RecruitInfo.STATUS88);
        //查询基础数据
        queryBasicData(map);
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/recruit_info/recruit_info_list_page";
        }else{
        	return "tiles.module.recruit_info.recruit_info_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="recruit_info_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
        RecruitInfo recruitInfo = new RecruitInfo();
        recruitInfo.setPublicTime(new Date());
        map.put("item", recruitInfo);
        //APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        //查询基础数据
        queryBasicData(map);
    	return getReturnUrl(request,map,"tiles.module.recruit_info.recruit_info_detail");
    }


    //增加-保存
    @RequestMapping(value ="recruit_info_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,RecruitInfo recruitInfo,String[] urls){
        //ID
        recruitInfo.setId(null);
        SessionUser user = SessionFace.getSessionUser(request);
        //所属客户端
        recruitInfo.setAppId(null==recruitInfo.getAppId()?user.getAppId():recruitInfo.getAppId());
        //状态
        recruitInfo.setStatus(RecruitInfo.STATUS1);
        //时间和操作人
        Date now = new Date();
        recruitInfo.setCreateTime(now);
        recruitInfo.setCreatorId(user.getUserId());
        recruitInfo.setCreator(user.getNickname());
        recruitInfo.setUpdateTime(now);
        recruitInfo.setUpdatorId(user.getUserId());
        recruitInfo.setUpdator(user.getNickname());
        recruitInfo.setSortNo(Integer.MAX_VALUE);
        Response<Void> res = recruitInfoService.save(recruitInfo,urls);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="recruit_info_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        RecruitInfo recruitInfo = recruitInfoService.get(id).getData();
        if(recruitInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", recruitInfo);
        //查询企业图片
        List<RecruitImage> images = recruitImageService.getByRecruitInfoId(id).getData();
        map.put("imageList", images);
        //APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        //查询基础数据
        queryBasicData(map);
        return getReturnUrl(request,map,"tiles.module.recruit_info.recruit_info_detail");
    }

    //修改-保存
    @RequestMapping(value ="recruit_info_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,RecruitInfo recruitInfo,String[] urls){
        Response<Void> res = Response.newInstance();
        if(recruitInfo==null || recruitInfo.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            RecruitInfo sourceRecruitInfo = recruitInfoService.get(recruitInfo.getId()).getData();
            if(sourceRecruitInfo==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }
            //客户端_ID
            sourceRecruitInfo.setAppId(recruitInfo.getAppId()==null?sourceRecruitInfo.getAppId():recruitInfo.getAppId());
            //时间和操作人
            Date now = new Date();
            sourceRecruitInfo.setUpdateTime(now);
            SessionUser user = SessionFace.getSessionUser(request);
            sourceRecruitInfo.setUpdatorId(user.getUserId());
            sourceRecruitInfo.setUpdator(user.getNickname());
            //企业名称
            sourceRecruitInfo.setName(recruitInfo.getName());
            //企业性质，见ol_option表TYPE_CODE = ENT_NATURE
            sourceRecruitInfo.setNature(recruitInfo.getNature());
            //企业规模，见ol_option表TYPE_CODE = ENT_SCALE
            sourceRecruitInfo.setScale(recruitInfo.getScale());
            //企业简介
            sourceRecruitInfo.setSynopsis(recruitInfo.getSynopsis());
            //招聘职位
            sourceRecruitInfo.setPosition(recruitInfo.getPosition());
            //职位描述
            sourceRecruitInfo.setDescription(recruitInfo.getDescription());
            //职位月薪，见ol_option表TYPE_CODE = SALARY
            sourceRecruitInfo.setSalary(recruitInfo.getSalary());
            //招聘人数
            sourceRecruitInfo.setNumberPeople(recruitInfo.getNumberPeople());
            //工作性质，见ol_option表TYPE_CODE = WORKMODE
            sourceRecruitInfo.setWorkmode(recruitInfo.getWorkmode());
            //联系电话
            sourceRecruitInfo.setTelephone(recruitInfo.getTelephone());
            //联系地址
            sourceRecruitInfo.setAddress(recruitInfo.getAddress());
            //发布日期
            sourceRecruitInfo.setPublicTime(recruitInfo.getPublicTime());
            //学历，见ol_option表TYPE_CODE = EDUCATION
            sourceRecruitInfo.setEducation(recruitInfo.getEducation());
            //标签
            sourceRecruitInfo.setEntLabel(recruitInfo.getEntLabel());
            res = recruitInfoService.save(sourceRecruitInfo,urls);
            recruitInfo = sourceRecruitInfo;
            if(res.getCode() < 0){
                return new Response<Void>(-1,"修改失败!");
            }
        }catch (Exception ex){
            logger.error("Save Method (Update) RecruitInfo Error : " + recruitInfo.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="recruit_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        RecruitInfo recruitInfo = recruitInfoService.get(id).getData();
        if(recruitInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", recruitInfo);
        //查询企业图片
        List<RecruitImage> images = recruitImageService.getByRecruitInfoId(id).getData();
        map.put("imageList", images);
        //APP下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList && appList.size()>1){
            map.put("appList", appList);
        }
        //查询基础数据
        queryBasicData(map);
        return getReturnUrl(request,map,"tiles.module.recruit_info.recruit_info_view");
    }

    //删除
    @RequestMapping(value ="recruit_info_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = recruitInfoService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="recruit_info_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = recruitInfoService.deleteLogic(ids);
        return res;
    }
    
    //修改排序号
    @RequestMapping(value ="/common/update_sort_no")
    @ResponseBody
    public Response<Void> updateSortNo(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,Integer sortNo){
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        Response<Void> res = recruitInfoService.updateSortNo(sortNo==null?Integer.MAX_VALUE:sortNo,sessionUser.getNickname(),sessionUser.getUserId(),id);
        return res;
    }
    
    /**
     * 查询基础数据
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年10月12日下午3:56:32
     */
    private void queryBasicData(Map<String, Object> map){
        //薪资类型
        List<Option> salaryList = basicService.getOptionListByType(Option.TYPECODE13);
        map.put("salaryList", salaryList);
        //工作类型
        List<Option> workmodeList = basicService.getOptionListByType(Option.TYPECODE11);
        map.put("workmodeList", workmodeList);
        //企业性质
        List<Option> natureList = basicService.getOptionListByType(Option.TYPECODE4);
        map.put("natureList", natureList);
        //企业规模
        List<Option> scaleList = basicService.getOptionListByType(Option.TYPECODE10);
        map.put("scaleList", scaleList);
        //学历
        List<Option> educationList = basicService.getOptionListByType(Option.TYPECODE14);
        map.put("educationList", educationList);
    }
    
    //上线
    @ResponseBody
    @RequestMapping(value ="online")
    public Response<Void> online(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,@RequestParam(value = "status") Byte status){
        Response<Void> res = recruitInfoService.updateStatus(status, id);
        return res;
    }
    
    //下线
    @ResponseBody
    @RequestMapping(value ="outline")
    public Response<Void> outline(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id,@RequestParam(value = "status") Byte status){
        Response<Void> res = recruitInfoService.updateStatus(status, id);
        return res;
    }
    
    //批量上线
    @ResponseBody
    @RequestMapping(value ="online_batch")
    public Response<Void> onlineBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids,@RequestParam(value = "status") Byte status){
        Response<Void> res = recruitInfoService.updateStatus(status, ids);
        return res;
    }
    
    //批量下线
    @ResponseBody
    @RequestMapping(value ="outline_batch")
    public Response<Void> outlineBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids,@RequestParam(value = "status") Byte status){
        Response<Void> res = recruitInfoService.updateStatus(status, ids);
        return res;
    }
}