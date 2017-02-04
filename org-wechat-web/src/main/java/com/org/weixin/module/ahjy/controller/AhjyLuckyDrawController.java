package com.org.weixin.module.ahjy.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.module.ahjy.dto.AhjyLuckyDrawDto;
import com.org.weixin.module.ahjy.service.AhjyActivityService;

@Controller
@RequestMapping(value = "ahjy/{accId}")
public class AhjyLuckyDrawController{

	@Autowired
	private AhjyActivityService ahjyActivityService;

	//夺冠抽奖后进入二维码页面
    private static final String LD_WIN_CODE = "/module/ahjy/pages/luckyDraw/ld_win_code";
    //未夺冠
    private static final String LD_LOSE = "/module/ahjy/pages/luckyDraw/ld_lose";
    //夺冠页面
    private static final String LD_WIN = "/module/ahjy/pages/luckyDraw/ld_win";
    //抽奖结果为积分加优惠券
    private static final String LD_WIN_INTEGRAL = "/module/ahjy/pages/luckyDraw/ld_win_integral";
    //抽奖结果为实物礼品加优惠券
    private static final String LD_WIN_PHYSICAL = "/module/ahjy/pages/luckyDraw/ld_win_physical";
    //抽奖机会已用尽
    private static final String LD_HAS_AWARD = "/module/ahjy/pages/luckyDraw/ld_has_award";
    //未参加活动或未中奖
    private static final String LD_UNAWARD = "/module/ahjy/pages/luckyDraw/ld_unaward";
    

    //输入手机号码面面
    private static final String RECEIVE = "/module/ahjy/pages/luckyDraw/receive";
    //奖品领取成功
    private static final String RECEIVE_SUCCESS = "/module/ahjy/pages/luckyDraw/receive_success";
    
	private static final Logger logger = LoggerFactory.getLogger(AhjyGameController.class);
	
	
	//抽奖首页
	@RequestMapping(value="awardindex")
	public String awardIndex(HttpServletRequest request,@PathVariable String accId){
		
		request.setAttribute("accId",accId);
		return "/module/ahjy/pages/luckyDraw/award_index";
	}
	
	/**
     * Title:公众号过来
     * @author yuwu on 2016年3月27日
     * @param request
     * @param activityId
     * @return
     */
    @RequestMapping({"linkAward"})
    public String linkAward(HttpServletRequest request, Model model,@PathVariable String accId) {
        SessionUser user = SessionFace.getSessionUser(request);
        try {
            AhjyLuckyDrawDto result = ahjyActivityService.linkAward(user.getId());
            request.setAttribute("accId",accId);
            //AhjyLuckyDrawDto result = ahjyActivityService.linkAward(1L);
            model.addAttribute("luckyDraw", result);
            return returnView(result);
        } catch (Exception e) {
            logger.error("抽奖失败", e);
            return "/error/400";
        }
    }
    
	/**
     * Title:抽奖页面
     * @author yuwu on 2016年3月27日
     * @param request
     * @param activityId
     * @return
     */
    @RequestMapping({"luckyDraw"})
    public String luckyDraw1(HttpServletRequest request, Model model,@PathVariable String accId) {
        SessionUser user = SessionFace.getSessionUser(request);
        try {
            AhjyLuckyDrawDto result = ahjyActivityService.luckyDraw(user.getId());
            //AhjyLuckyDrawDto result = ahjyActivityService.luckyDraw(1L);
            model.addAttribute("luckyDraw", result);
            request.setAttribute("accId",accId);
            return returnView(result);
        } catch (Exception e) {
            logger.error("抽匀失败", e);
            return "/error/400";
        }
    }
    
    public String returnView(AhjyLuckyDrawDto result){
        if(result.getResultFlag() == AhjyLuckyDrawDto.RESULT_FLAG_1){//用户未参加该活动或未中奖
            return LD_UNAWARD;
        }else if(result.getResultFlag() == AhjyLuckyDrawDto.RESULT_FLAG_2){//用户已经领取过奖
            return LD_HAS_AWARD;
        }else if(result.getResultFlag() == AhjyLuckyDrawDto.RESULT_FLAG1){//桃子页面
            return LD_WIN;
        }else if(result.getResultFlag() == AhjyLuckyDrawDto.RESULT_FLAG2){//抽奖结果为实物礼品加优惠券
            return LD_WIN_PHYSICAL;
        }else if(result.getResultFlag() == AhjyLuckyDrawDto.RESULT_FLAG3){//抽奖结果为积分加优惠券
            return LD_WIN_INTEGRAL;
        }
        return "/error/400";
    }
    /**
     * Title:1、夺冠页面
     */
    @RequestMapping({"luckyDraw/{activityId}/ld_win_code"})
    public String winCode(HttpServletRequest request, @PathVariable Long activityId, Model model) {
        return LD_WIN_CODE;
    }
    /**
     * Title:2、未夺冠页面
     */
    @RequestMapping({"luckyDraw/{activityId}/ld_lose"})
    public String lose(HttpServletRequest request, @PathVariable Long activityId, Model model) {
        return LD_LOSE;
    }
    
    /**
     * Title:抽奖页面
     */
    @RequestMapping({"luckyDraw/receive"})
    public String receive(HttpServletRequest request, Model model,@PathVariable String accId) {
    	request.setAttribute("accId", accId);
        return RECEIVE;
    }
    /**
     * Title:抽奖页面
     */
    @RequestMapping({"luckyDraw/re_success"})
    public String receiveSuccess(HttpServletRequest request, Model model) {
        return RECEIVE_SUCCESS;
    }
}
