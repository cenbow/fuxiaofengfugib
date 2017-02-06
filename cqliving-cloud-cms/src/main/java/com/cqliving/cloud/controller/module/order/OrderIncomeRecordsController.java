package com.cqliving.cloud.controller.module.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cqliving.cloud.online.order.domain.OrderIncomeRecords;
import com.cqliving.cloud.online.order.dto.OrderIncomeRecordsDto;
import com.cqliving.cloud.online.order.service.OrderIncomeRecordsService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.file.ExcelEntityUtil;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/order")
public class OrderIncomeRecordsController extends CommonController {

    @Autowired
    private OrderIncomeRecordsService orderIncomeRecordsService;
    @Autowired
    private AppInfoService appInfoService;

    //列表
    @RequestMapping(value ="order_list")
    public String list(HttpServletRequest request, Map<String, Object> map,
    	@RequestParam(value = "p", required = false) String isAjaxPage
        ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
        ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
    	) {
        PageInfo<OrderIncomeRecordsDto> pageInfo = getPageInfo(request);
        queryPage(request, pageInfo);
        map.put("pageInfo", pageInfo);
        map.put("allPayModes", OrderIncomeRecords.allPayModes);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/order/order_list_page";
        }else{
        	return "tiles.module.order.order_list";
        }
    }
    
    private void queryPage(HttpServletRequest request,PageInfo<OrderIncomeRecordsDto> pageInfo){
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("createTime", false);
        Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        SessionUser user = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(user.getUsertype(), user.getUserId()).getData();
        userDate(user, appList, searchMap);
        orderIncomeRecordsService.queryByPage(pageInfo, searchMap, sortMap);
    }

    /**
     * 导出
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年12月8日上午9:56:34
     */
    @RequestMapping(value ={"order_export"})
    public void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map
            ,@RequestParam(value="search_GTE_createTime", required=false) Date search_GTE_createTime
            ,@RequestParam(value="search_LT_createTime", required=false) Date search_LT_createTime
            ) {
        
        PageInfo<OrderIncomeRecordsDto> pageInfo =  new PageInfo<OrderIncomeRecordsDto>(Integer.MAX_VALUE, 1);
        queryPage(request, pageInfo);
        ExcelEntityUtil.doExportForXls(response, "收支记录", pageInfo.getPageResults());
    }

    //增加-查看
    @RequestMapping(value ="order_add", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Map<String, Object> map){
    	return getReturnUrl(request,map,"tiles.module.order.order_detail");
    }


    //增加-保存
    @RequestMapping(value ="order_add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postAdd(HttpServletRequest request, Map<String, Object> map,OrderIncomeRecords orderIncomeRecords){
        //主键
        orderIncomeRecords.setId(null);
        Response<Void> res = orderIncomeRecordsService.save(orderIncomeRecords);
        if(res.getCode() < 0){
        	return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage(new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_SUCCESS));
        return res;
    }

    //修改-查看
    @RequestMapping(value ="order_update", method = RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        OrderIncomeRecords orderIncomeRecords = orderIncomeRecordsService.get(id).getData();
        if(orderIncomeRecords==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", orderIncomeRecords);
        return getReturnUrl(request,map,"tiles.module.order.order_detail");
    }

    //修改-保存
    @RequestMapping(value ="order_update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> postUpdate(HttpServletRequest request, Map<String, Object> map,OrderIncomeRecords orderIncomeRecords){
        Response<Void> res = Response.newInstance();
        if(orderIncomeRecords==null || orderIncomeRecords.getId()==null){
            //没有记录
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
        }
        try{
            OrderIncomeRecords sourceOrderIncomeRecords = orderIncomeRecordsService.get(orderIncomeRecords.getId()).getData();
            if(sourceOrderIncomeRecords==null){
                //没有记录
                return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND));
            }

            //客户端id
            sourceOrderIncomeRecords.setAppId(orderIncomeRecords.getAppId());
            //订单id，表order_info的主键
            sourceOrderIncomeRecords.setOrderId(orderIncomeRecords.getOrderId());
            //订单号
            sourceOrderIncomeRecords.setOrderNo(orderIncomeRecords.getOrderNo());
            //金额（单位：分），有正负
            sourceOrderIncomeRecords.setMoney(orderIncomeRecords.getMoney());
            //收支总金额（单位：分），把当前app之前所有收支记录的money相加，再加上本次money的值
            sourceOrderIncomeRecords.setTotalMoney(orderIncomeRecords.getTotalMoney());
            //支付渠道
            sourceOrderIncomeRecords.setPayMode(orderIncomeRecords.getPayMode());
            //创建时间
            sourceOrderIncomeRecords.setCreateTime(orderIncomeRecords.getCreateTime());
            res= orderIncomeRecordsService.save(sourceOrderIncomeRecords);
            orderIncomeRecords = sourceOrderIncomeRecords;
        }catch (Exception ex){
            logger.error("Save Method (Update) OrderIncomeRecords Error : " + orderIncomeRecords.toString(), ex);
            //修改失败
            return new Response<Void>(-1,new RequestContext(request).getMessage(Constant.I18nMessage.SAVE_FAILURE));
        }
        res.setMessage("保存成功！");
        return res;
    }

    //查看
    @RequestMapping(value ="order_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        OrderIncomeRecords orderIncomeRecords = orderIncomeRecordsService.get(id).getData();
        if(orderIncomeRecords==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", orderIncomeRecords);
        return getReturnUrl(request,map,"tiles.module.order.order_view");
    }

    //删除
    @RequestMapping(value ="order_delete", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> delete(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        Response<Void> res = orderIncomeRecordsService.delete(id);
        return res;
    }

	//批量删除
    @RequestMapping(value ="order_delete_batch", method = RequestMethod.POST)
    @ResponseBody
    public Response<Void> deleteBatch(HttpServletRequest request, Map<String, Object> map,@RequestParam("ids[]") Long[] ids){
        Response<Void> res = orderIncomeRecordsService.delete(ids);
        return res;
    }
}
