package com.cqliving.cloud.controller.module.order;

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
import com.cqliving.cloud.online.order.domain.OrderEvaluate;
import com.cqliving.cloud.online.order.dto.OrderEvaluateDto;
import com.cqliving.cloud.online.order.service.OrderEvaluateService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/order_evaluate")
public class OrderEvaluateController extends CommonController {

    @Autowired
    private OrderEvaluateService orderEvaluateService;
    @Autowired
    private AppInfoService appInfoService;

    //审核列表
    @RequestMapping(value ="order_evaluate_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
    	) {
    	
		Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        
		
        searchMap.put("NOTEQ_status", OrderEvaluate.STATUS99);//排除逻辑删除状态
        
        String searchAppid = (String)searchMap.get("EQ_appId");
        //处理App查询条件以及页面上展示的app下拉框
        SessionUser user = SessionFace.getSessionUser(request);
        
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        if(null!=appList&&appList.size()>1){
            map.put("appList", appList);
        }
        if(StringUtils.isBlank(searchAppid)){
            if(null!=appList&&appList.size()>0){
                searchMap.put("EQ_appId", appList.get(0).getId());
            }else{
                searchMap.put("EQ_appId", user.getAppId());
            }
        }
        PageInfo<OrderEvaluateDto> pageInfo = getPageInfo(request);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("statusNew", true);
        sortMap.put("createTime", false);
        sortMap.put("id", false);
        
        map.put("pageInfo", orderEvaluateService.queryEvaluateForPage(pageInfo, searchMap, sortMap).getData());
        map.put("allStatuss", OrderEvaluate.allStatuss);
        map.put("STATUS3", OrderEvaluate.STATUS3);
        map.put("STATUS_1", OrderEvaluate.STATUS_1);
        map.put("STATUS1", OrderEvaluate.STATUS1);
        
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/order/order_evaluate_list_page";
        }else{
        	return "tiles.module.order.order_evaluate_list";
        }
    }

    //增加-查看
    @RequestMapping(value ="order_evaluate_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	return getReturnUrl(request,map,"tiles.module.order.order_evaluate_detail");
    }


    //增加-保存
    @RequestMapping(value ="order_evaluate_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,OrderEvaluate orderEvaluate){
        //id
        orderEvaluate.setId(null);
        Response<Void> res = orderEvaluateService.save(orderEvaluate);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="order_evaluate_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        OrderEvaluate orderEvaluate = orderEvaluateService.get(id).getData();
        if(orderEvaluate==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", orderEvaluate);
        return getReturnUrl(request,map,"tiles.module.order.order_evaluate_detail");
    }

    //修改-保存
    @RequestMapping(value ="order_evaluate_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,OrderEvaluate orderEvaluate){
        Response<Void> res = Response.newInstance();
        if(orderEvaluate==null || orderEvaluate.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            OrderEvaluate sourceOrderEvaluate = orderEvaluateService.get(orderEvaluate.getId()).getData();
            if(sourceOrderEvaluate==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端_id
            sourceOrderEvaluate.setAppId(orderEvaluate.getAppId());
            //订单id
            sourceOrderEvaluate.setOrderId(orderEvaluate.getOrderId());
            //商品id
            sourceOrderEvaluate.setGoodsId(orderEvaluate.getGoodsId());
            //用户id
            sourceOrderEvaluate.setUserId(orderEvaluate.getUserId());
            //内容
            sourceOrderEvaluate.setContent(orderEvaluate.getContent());
            //商品评价，1颗星20分，最多5颗星100分
            sourceOrderEvaluate.setGoodsScore(orderEvaluate.getGoodsScore());
            //评价图片地址，最多9张，用逗号分开
            sourceOrderEvaluate.setImageUrls(orderEvaluate.getImageUrls());
            //状态
            sourceOrderEvaluate.setStatus(orderEvaluate.getStatus());
            //创建时间
            sourceOrderEvaluate.setCreateTime(orderEvaluate.getCreateTime());
            //创建人
            sourceOrderEvaluate.setCreatorId(orderEvaluate.getCreatorId());
            //创建人姓名
            sourceOrderEvaluate.setCreator(orderEvaluate.getCreator());
            //更新时间
            sourceOrderEvaluate.setUpdateTime(orderEvaluate.getUpdateTime());
            //更新人id
            sourceOrderEvaluate.setUpdatorId(orderEvaluate.getUpdatorId());
            //更新人
            sourceOrderEvaluate.setUpdator(orderEvaluate.getUpdator());
            //审核时间
            sourceOrderEvaluate.setAduitTime(orderEvaluate.getAduitTime());
            //审核人ID
            sourceOrderEvaluate.setAduitUserId(orderEvaluate.getAduitUserId());
            res= orderEvaluateService.save(sourceOrderEvaluate);
            orderEvaluate = sourceOrderEvaluate;
        }catch (Exception ex){
            logger.error("Save Method (Update) OrderEvaluate Error : " + orderEvaluate.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="order_evaluate_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        OrderEvaluateDto orderEvaluate = orderEvaluateService.getById(id).getData();
        if(orderEvaluate==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", orderEvaluate);
        map.put("allStatuss", OrderEvaluate.allStatuss);
        return getReturnUrl(request,map,"tiles.module.order.order_evaluate_view");
    }

    //删除
    @RequestMapping(value ="order_evaluate_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = orderEvaluateService.deleteLogic(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="order_evaluate_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = orderEvaluateService.deleteLogic(ids);
        return res;
    }
    
    //批量删除
    @RequestMapping(value ="auditing", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> auditing(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids,@RequestParam("goodsIds[]") Long[] goodsIds ,Byte status,String auditingContent){
        Response<Void> res = orderEvaluateService.auditing(ids, goodsIds, status, auditingContent);
        return res;
    }
}
