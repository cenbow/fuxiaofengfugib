package com.cqliving.cloud.controller.module.act;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.support.RequestContext;

import com.cqliving.cloud.common.Constant;
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.controller.common.UploadController;
import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.cloud.online.act.domain.ActTestQuestion;
import com.cqliving.cloud.online.act.domain.ActTestQuestionDto;
import com.cqliving.cloud.online.act.dto.ActTestQuestionExcelOption;
import com.cqliving.cloud.online.act.service.ActTestClassifyService;
import com.cqliving.cloud.online.act.service.ActTestQuestionService;
import com.cqliving.cloud.online.act.service.ActTestService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.file.ExcelTemplate;
import com.cqliving.tool.common.util.file.UploadExcel;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/act/common")
public class ActTestQuestionController extends UploadController {

	@Autowired
	private ActTestService actTestService;
	@Autowired
	private ActTestQuestionService actTestQuestionService;
    @Autowired
    private ActTestClassifyService actTestClassifyService;
    @Resource
    private List<ExcelTemplate> actTestQuestionTemplate;

    /**
     * Title:列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月12日
     * @param request
     * @param map
     * @param actTestId 返回用
     * @param isAjaxPage
     * @return
     */
    @RequestMapping(value ="act_test_questionlist")
    public String list(HttpServletRequest request, Map<String, Object> map, @RequestParam Long classifyId, @RequestParam Long actTestId, @RequestParam(value = "p", required = false) String isAjaxPage
    	) {
		Map<String, Object> searchMap = Maps.newHashMap();
		searchMap.put("EQ_actTestClassifyId", classifyId);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sortNo", true);
        
        PageInfo<ActTestQuestion> pageInfo = getPageInfo(request);
        pageInfo.setCountOfCurrentPage(500);
        map.put("pageInfo", actTestQuestionService.queryForPage(pageInfo, searchMap, sortMap).getData());
        map.put("allTypes", ActTestQuestion.allTypes);
        map.put("actTestClassifyId", classifyId);
        map.put("actTestId", actTestId);//返回用
        //是否设置分值
        ActTestClassify actTestClasify = actTestClassifyService.get(classifyId).getData();
        map.put("isSetScore", actTestClasify.getIsSetScore());
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/act/act_test_questionlist_page";
        }else{
        	ActTest actTest = actTestService.getByActTestClassify(classifyId).getData();
        	map.put("actTest", actTest);//只是为了页面上的导航
        	return "tiles.module.act.act_test_questionlist";
        }
    }

    /**
     * <p>Description:增加-保存</p>
     * @author DeweiLi on 2016年6月16日
     * @param request
     * @param map
     * @param actTestQuestion
     * @param imageUrls
     * @return
     */
    @RequestMapping(value ="act_test_questionadd", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,ActTestQuestion actTestQuestion){
        //ID
        actTestQuestion.setId(null);
        actTestQuestion.setCreateTime(new Date());
        
        Response<Void> res = actTestQuestionService.save(actTestQuestion);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    /**
     * Title:修改-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月16日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="act_test_questionupdate", method = RequestMethod.GET)
    public Response<ActTestQuestion> update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<ActTestQuestion> res = actTestQuestionService.get(id);
        if(res.getData()==null){
            //没有记录
            res.setCode(ErrorCodes.FAILURE);
            res.setMessage(Constant.I18nMessage.RECORD_NOT_FOUND);
        }
        return res;
    }

    /**
     * Title:修改-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月16日
     * @param request
     * @param map
     * @param actTestQuestion
     * @return
     */
    @RequestMapping(value ="act_test_questionupdate", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,ActTestQuestion actTestQuestion){
        Response<Void> res = Response.newInstance();
        if(actTestQuestion==null || actTestQuestion.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            ActTestQuestion sourceActTestQuestion = actTestQuestionService.get(actTestQuestion.getId()).getData();
            if(sourceActTestQuestion==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //问题名称（问题描述）
            sourceActTestQuestion.setQuestion(actTestQuestion.getQuestion());
            //问题图片,多张用,分隔
            sourceActTestQuestion.setImageUrl(actTestQuestion.getImageUrl());
            //问题类型
            sourceActTestQuestion.setType(actTestQuestion.getType());
            //问题分值
            sourceActTestQuestion.setScore(actTestQuestion.getScore());
            res= actTestQuestionService.save(sourceActTestQuestion);
            actTestQuestion = sourceActTestQuestion;
        }catch (Exception ex){
            logger.error("Save Method (Update) ActTestQuestion Error : " + actTestQuestion.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    /**
     * Title:删除
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月16日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="act_test_questiondelete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = actTestQuestionService.delete(id);
        return res;
    }
    
    /**
     * Title:排序
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月16日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="act_test_questionsort", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> sort(HttpServletRequest request, Map<String, Object> map, @RequestParam("questionList") String questionList, @RequestParam("answerList") String answerList){
    	Response<Void> res = actTestQuestionService.sort(questionList, answerList);
    	return res;
    }
    
    /**
     * Title:excel导入
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月17日
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="act_test_excelupload", method = RequestMethod.POST)
    public Response<Void> excelUpload(HttpServletRequest request, @RequestParam Long classifyId, @RequestParam String content){
    	Response<Void> rs = Response.newInstance();
    	try {
    		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("excelFile");
    		if(files != null && files.size() > 0){
    			content = files.get(0).getOriginalFilename();
    		}
    		Response<List<ActTestQuestionExcelOption>> res = UploadExcel.importExcel(request, actTestQuestionTemplate, ActTestQuestionExcelOption.class);
    		rs.setCode(res.getCode());
    		rs.setMessage(res.getMessage());
			List<ActTestQuestionExcelOption> list = res.getData();
			if(res.getCode() >= 0 && list != null && list.size() > 0){
				rs = actTestQuestionService.excelImport(classifyId, content, list);
			}
		}catch(BusinessException e){
            return new Response<Void>(-1,e.getMessage());
        }catch(Exception e){
        	e.printStackTrace();
            return new Response<Void>(-1,"上传失败，请稍后重试");
        }
    	return rs;
    }
    
    /**
     * Title:答题激活的时候验证
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月27日
     * @param request
     * @param actInfoListId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="validate_null_answer")
    public Response<Void> validateNullAnswer(HttpServletRequest request, @RequestParam Long actInfoListId){
    	Response<List<ActTestQuestionDto>> rs = actTestQuestionService.validateNullAnswer(actInfoListId);
    	Response<Void> res = new Response<>(rs.getCode(), rs.getMessage());
    	if(CollectionUtils.isNotEmpty(rs.getData()) && rs.getData().size() > 0){
    		res.setCode(-1);
    		res.setMessage("有问题未设置正确答案，请配置好了再激活。");
    	}
    	return res;
    }
}
