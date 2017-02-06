package com.cqliving.cloud.controller.module.analysis;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.analysis.domain.AnalysisNananData;
import com.cqliving.cloud.online.analysis.service.AnalysisNananDataService;
import com.cqliving.cloud.online.analysis.service.AnalysisNananTimesService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.common.util.file.ExcelEntityUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/analysis")
public class AnalysisNananDataController extends CommonController {

    @Autowired
    private AnalysisNananDataService analysisNananDataService;
    @Autowired
    private AnalysisNananTimesService analysisNananTimesService;

    //列表
    @RequestMapping(value ="analysis_nanan_dada_list")
    public String list(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map, 
    		@RequestParam(value = "p", required = false) String isAjaxPage, String isDownload) {
    	
    	//查询期数表
    	map.put("analysisNananTimesList", analysisNananTimesService.getAll().getData());
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("score", false);
        sortMap.put("id", false);
        
        PageInfo<AnalysisNananData> pageInfo = getPageInfo(request);

        //导出明细数据
        if ("true".equals(isDownload)) {
        	//设置一次查询最大值，获取所有数据
        	pageInfo.setCountOfCurrentPage(Integer.MAX_VALUE);
        	PageInfo<AnalysisNananData> page = analysisNananDataService.queryForPage(pageInfo, searchMap, sortMap).getData();
        	List<AnalysisNananData> list = page.getPageResults();
            String exportFileName = "讲学赞积分统计表";
            ExcelEntityUtil.doExportForXls(response, exportFileName, list);
            return null;
        } else {
	        map.put("pageInfo", analysisNananDataService.queryForPage(pageInfo, searchMap, sortMap).getData());
	        //查询按钮和点击页面是ajax操作。
	        if (StringUtils.isNotBlank(isAjaxPage)) {
	        	return "/module/analysis/analysis_nanan_dada_list_page";
	        } else {
	        	return "tiles.module.analysis.analysis_nanan_dada_list";
	        }
        }
    }

    //增加-查看
    @RequestMapping(value ="analysis_nanan_dada_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	return getReturnUrl(request,map,"tiles.module.analysis.analysis_nanan_dada_detail");
    }


    //增加-保存
    @RequestMapping(value ="analysis_nanan_dada_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,AnalysisNananData analysisNananData){
        //ID
        analysisNananData.setId(null);
        Response<Void> res = analysisNananDataService.save(analysisNananData);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="analysis_nanan_dada_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AnalysisNananData analysisNananData = analysisNananDataService.get(id).getData();
        if(analysisNananData==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", analysisNananData);
        return getReturnUrl(request,map,"tiles.module.analysis.analysis_nanan_dada_detail");
    }

    //修改-保存
    @RequestMapping(value ="analysis_nanan_dada_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,AnalysisNananData analysisNananData){
        Response<Void> res = Response.newInstance();
        if(analysisNananData==null || analysisNananData.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            AnalysisNananData sourceAnalysisNananData = analysisNananDataService.get(analysisNananData.getId()).getData();
            if(sourceAnalysisNananData==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //统计期数ID，表analysis_nanan_times的主键
            sourceAnalysisNananData.setAnalysisNananTimesId(analysisNananData.getAnalysisNananTimesId());
            //用户ID，表user_account的主键
            sourceAnalysisNananData.setUserId(analysisNananData.getUserId());
            //用户手机
            sourceAnalysisNananData.setUserTelephone(analysisNananData.getUserTelephone());
            //用户姓名
            sourceAnalysisNananData.setUserName(analysisNananData.getUserName());
            //评论数
            sourceAnalysisNananData.setCommentNum(analysisNananData.getCommentNum());
            //点赞数
            sourceAnalysisNananData.setPraiseNum(analysisNananData.getPraiseNum());
            //分享数
            sourceAnalysisNananData.setShareNum(analysisNananData.getShareNum());
            //分数=分享数*5+评论数*3+点赞数
            sourceAnalysisNananData.setScore(analysisNananData.getScore());
            //创建时间
            sourceAnalysisNananData.setCreateTime(analysisNananData.getCreateTime());
            res= analysisNananDataService.save(sourceAnalysisNananData);
            analysisNananData = sourceAnalysisNananData;
        }catch (Exception ex){
            logger.error("Save Method (Update) AnalysisNananData Error : " + analysisNananData.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="analysis_nanan_dada_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        AnalysisNananData analysisNananData = analysisNananDataService.get(id).getData();
        if(analysisNananData==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", analysisNananData);
        return getReturnUrl(request,map,"tiles.module.analysis.analysis_nanan_dada_view");
    }

    //删除
    @RequestMapping(value ="analysis_nanan_dada_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = analysisNananDataService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="analysis_nanan_dada_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = analysisNananDataService.delete(ids);
        return res;
    }
    
    /**
     * <p>Description: 生成期数</p>
     * @author Tangtao on 2016年11月10日
     * @param request
     * @param map
     * @param beginDate 开始日期（yyyy-MM-dd）
     * @param endDate 结束日期（yyyy-MM-dd）
     * @return
     * @throws ParseException 
     */
    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"analysis_nanan_create_times"})
    public Response<Void> createTimes(HttpServletRequest request, Map<String, Object> map, 
    		@RequestParam("bd") String beginDate, 
    		@RequestParam("ed") String endDate) throws ParseException {
    	Date bd = DateUtils.parseDate(beginDate, DateUtil.FORMAT_YYYY_MM_DD);
    	Date ed = DateUtils.parseDate(endDate, DateUtil.FORMAT_YYYY_MM_DD);
    	Calendar cbd = Calendar.getInstance();
    	Calendar ced = Calendar.getInstance();
    	cbd.setTime(bd);
    	ced.setTime(ed);
    	ced.add(Calendar.DATE, 1);
    	ced.add(Calendar.SECOND, -1);
    	beginDate = DateUtil.format(cbd.getTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
    	endDate = DateUtil.format(ced.getTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS);
    	
    	
        Response<Void> res = analysisNananTimesService.createTimes(beginDate, endDate, 952L);	//南岸APP讲学赞栏目id：952
        return res;
    }
    
}