package com.cqliving.cloud.controller.module.order;

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
import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.common.SessionFace;
import com.cqliving.cloud.common.SessionUser;
import com.cqliving.cloud.controller.common.CommonController;
import com.cqliving.cloud.online.app.dto.AppInfoDto;
import com.cqliving.cloud.online.app.service.AppInfoService;
import com.cqliving.cloud.online.order.domain.OrderFlow;
import com.cqliving.cloud.online.order.domain.OrderGoods;
import com.cqliving.cloud.online.order.domain.OrderInfo;
import com.cqliving.cloud.online.order.domain.OrderRefund;
import com.cqliving.cloud.online.order.dto.OrderGoodsData;
import com.cqliving.cloud.online.order.dto.OrderInfoDto;
import com.cqliving.cloud.online.order.service.OrderFlowService;
import com.cqliving.cloud.online.order.service.OrderGoodsService;
import com.cqliving.cloud.online.order.service.OrderInfoService;
import com.cqliving.cloud.online.order.service.OrderRefundService;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.web.support.Servlets;
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.util.date.DateUtil;
import com.cqliving.tool.utils.CurrencyUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping(value = "/module/order_info")
public class OrderInfoController extends CommonController {

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private OrderFlowService orderFlowService;
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private OrderRefundService orderRefundService;

    /**
     * Title:列表
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月1日
     * @param request
     * @param map
     * @param isAjaxPage
     * @return
     */
    @RequestMapping(value ="order_info_list")
    public String list(HttpServletRequest request, Map<String, Object> map, @RequestParam(value = "p", required = false) String isAjaxPage) {
    	Map<String, Object> searchMap = Servlets.getParametersStartingWith(request, SEARCHE_PREFIX);
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("createTime", false);
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
        List<AppInfoDto> appList = appInfoService.getBySysUser(sessionUser.getUsertype(), sessionUser.getUserId()).getData();
        map.put("appList",appList);
        Long appId = sessionUser.getAppId() == null ? (appList != null && appList.size() > 0) ? appList.get(0).getId() : null : sessionUser.getAppId();
        if(StringUtils.isBlank(request.getParameter("search_EQ_appId")) && CollectionUtils.isNotEmpty(appList) && appList.size() > 0){
         	searchMap.put("EQ_appId", appId);
         	request.setAttribute("search_EQ_appId", appId);
        }
        
        PageInfo<OrderInfoDto> pageInfo = getPageInfo(request);
        searchMap.put("NOTEQ_status", OrderInfo.STATUS99);//排除逻辑删除状态
        map.put("pageInfo", orderInfoService.queryForPage(pageInfo, searchMap, sortMap).getData());
        
        map.put("allPayforStatuss", OrderInfo.allPayforStatuss);
        map.put("allPayModes", OrderInfo.allPayModes);
	                    
        //查询按钮和点击页面是ajax操作。
        if(StringUtils.isNotBlank(isAjaxPage)){
        	return "/module/order/order_info_list_page";
        }else{
        	return "tiles.module.order.order_info_list";
        }
    }
    
    /**
     * Title:查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月8日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="order_info_view")
    public String show(HttpServletRequest request, Map<String, Object> map,@RequestParam(value = "id") Long id){
        OrderInfo orderInfo = orderInfoService.get(id).getData();
        if(orderInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        map.put("item", orderInfo);
        //商品列表
        List<OrderGoods> goodsList = orderGoodsService.getByOrder(orderInfo.getId()).getData();
        List<OrderGoodsData> list = Lists.newArrayList();
        if(goodsList != null && goodsList.size() > 0){
        	OrderGoodsData data;
        	for(OrderGoods og : goodsList){
        		data = new OrderGoodsData();
        		data.setGoodsId(og.getGoodsId());
        		data.setGoodsName(og.getGoodsName());
        		data.setQuantity(og.getQuantity());
        		data.setGoodsPrice(CurrencyUtil.format(og.getGoodsPrice(), 2, 2, true));
        		data.setRefundStatus(og.getRefundStatus());
        		list.add(data);
        	}
        }
        map.put("goodsList", list);
        
        //支付时间
        OrderFlow orderFlow = orderFlowService.getOneRecoreByOrderAndStatus(id, OrderFlow.OPERATETYPE4).getData();
        map.put("payTime", orderFlow != null ? DateUtil.format(orderFlow.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) : "");
        
        //发货时间
        orderFlow = orderFlowService.getOneRecoreByOrderAndStatus(id, OrderFlow.OPERATETYPE5).getData();
        map.put("deliverGoodsTime", orderFlow != null ? DateUtil.format(orderFlow.getCreateTime(), DateUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) : "");
        
        map.put("allPayforStatuss", OrderInfo.allPayforStatuss);
        map.put("allPayModes", OrderInfo.allPayModes);
        map.put("shippingFare", CurrencyUtil.format(orderInfo.getShippingFare(), 2, 2, true));
        map.put("totalMoney", CurrencyUtil.format(orderInfo.getOrderAmount(), 2, 2, true));
        //获取订单支付时间
        
        return getReturnUrl(request,map,"tiles.module.order.order_info_view");
    }
    
    /**
     * Title:发货-查看
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月5日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="order_info_deliver_goods", method = RequestMethod.GET)
    public String deliverGoodsGet(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
    	OrderInfo orderInfo = orderInfoService.get(id).getData();
    	if(orderInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
    	map.put("item", orderInfo);
    	return getReturnUrl(request,map,"tiles.module.order.deliverGoods");
    }
    
    
    /**
     * Title:发货-保存
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月6日
     * @param request
     * @param map
     * @param id
     * @param expressCompany
     * @param expressNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="order_info_deliver_goods", method = RequestMethod.POST)
    public Response<Void> deliverGoodsPost(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id, @RequestParam String expressCompany, @RequestParam String expressNo){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	if(id == null || StringUtils.isBlank(expressNo) || StringUtils.isBlank(expressCompany)){
    		return new Response<>(ErrorCodes.FAILURE, "快递单号和快递公司不能为空");
    	}
    	Response<Void> rs = orderInfoService.deliverGoods(id, expressCompany, expressNo, sessionUser.getUserId());
    	return rs;
    }
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月18日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value ="order_info_refund", method = RequestMethod.GET)
    public String refund(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
        List<OrderRefund> orderRefunds = orderRefundService.getRefundingByOrderId(id).getData();
        map.put("orderRefunds", orderRefunds);
        return getReturnUrl(request,map,"tiles.module.order.order_info_refund");
    }
    
    /**
     * Title:确认退款和拒绝退款
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月6日
     * @param request
     * @param map
     * @param id
     * @param goodsId
     * @param content
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="order_info_refund", method = RequestMethod.POST)
    public Response<Void> refundPost(HttpServletRequest request, Map<String, Object> map, @RequestParam(value="ids") Long[] ids, String[] refuseReasons, @RequestParam String type){
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	if(ids == null || StringUtils.isBlank(type)){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	Response<Void> rs = orderInfoService.refundByAdmin(ids, type, refuseReasons, sessionUser.getUserId());
    	return rs;
    }
    
    /**
     * Title:取消订单
     * <p>Description:</p>
     * @author DeweiLi on 2016年12月6日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value ="order_info_cancel", method = RequestMethod.POST)
    public Response<Void> cancelByAdmin(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
    	if(id == null){
    		return new Response<>(ErrorCodes.CommonErrorEnum.INVALID_PARAM.getCode(), ErrorCodes.CommonErrorEnum.INVALID_PARAM.getDesc());
    	}
    	SessionUser sessionUser = SessionFace.getSessionUser(request);
    	Long[] ids = new Long[]{id};
    	Response<Void> rs = orderInfoService.cancelByAdmin(sessionUser.getUserId(), ids);
    	return rs;
    }
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月11日
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value="order_info_update", method=RequestMethod.GET)
    public String update(HttpServletRequest request, Map<String, Object> map, @RequestParam Long id){
        OrderInfo orderInfo = orderInfoService.get(id).getData();
        if(orderInfo==null){
            //没有记录
            return super.operFailure(new RequestContext(request).getMessage(Constant.I18nMessage.RECORD_NOT_FOUND), map);
        }
        String shippingFare = CurrencyUtil.format(orderInfo.getShippingFare());
        map.put("shippingFare", shippingFare);
        map.put("item", orderInfo);
        return getReturnUrl(request,map,"tiles.module.order.order_info_detail");
    }
    
    /**
     * Title:
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月11日
     * @param request
     * @param map
     * @param orderInfo
     * @param fare 运费
     * @return
     */
    @ResponseBody
    @RequestMapping(value="order_info_update", method=RequestMethod.POST)
    public Response<Void> updatePost(HttpServletRequest request, Map<String, Object> map, OrderInfo orderInfo, Double fare){
        Response<Void> rs = Response.newInstance();
        if(orderInfo == null || orderInfo.getId() == null){
            rs.setCode(ErrorCodes.FAILURE);
            rs.setMessage("参数错误");
            return rs;
        }
        if(fare == null){
            fare = 0.0;
        }
        Double f = (fare * 100);
        orderInfo.setShippingFare(f.intValue());
        SessionUser sessionUser = SessionFace.getSessionUser(request);
        orderInfo.setUpdaterId(sessionUser.getUserId());
        rs = orderInfoService.saveByAdmim(orderInfo);
        return rs;
    }
}
