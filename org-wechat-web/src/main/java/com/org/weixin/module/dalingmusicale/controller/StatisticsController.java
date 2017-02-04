/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.framework.utils.Dates;
import com.cqliving.tool.common.util.date.DateUtil;
import com.org.common.SessionFace;
import com.org.weixin.module.dalingmusicale.domain.Contestant;
import com.org.weixin.module.dalingmusicale.domain.UserViewHis;
import com.org.weixin.module.dalingmusicale.manager.ContestantManager;
import com.org.weixin.module.dalingmusicale.manager.UserTicketManager;
import com.org.weixin.module.dalingmusicale.manager.UserViewHisManager;
import com.org.weixin.module.dalingmusicale.manager.UserVoteManager;
import com.org.weixin.system.domain.SysWechatUser;
import com.org.weixin.system.service.SysWechatUserService;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月18日
 */
@Controller
public class StatisticsController {

	@Autowired
	SysWechatUserService sysWechatUserService;
	@Autowired
	UserViewHisManager userViewHisManager;
	@Autowired
	UserTicketManager userTicketManager; 
	@Autowired
	ContestantManager contestantManager;
	@Autowired
	UserVoteManager userVoteManager; 
	
	//门票统计页面
	private final static String TICKET_STATISTICS = "/module/daling/musicale/ticket_statistics";
	private final static String TIME_KEY = "dalingmusicale_time_key";
	
	@RequestMapping(value="ticket_statistics/{accId}/{moduleId}")
	public String ticketStatistics(HttpServletRequest request,@PathVariable Long accId,@PathVariable Long moduleId){
		
		//用户点击量
		List<UserViewHis> viewNum = userViewHisManager.findByViewType(UserViewHis.VIEWTYPE2);
		request.setAttribute("viewNum",CollectionUtils.isEmpty(viewNum) ? 0 : viewNum.size());
		//参与用户量
		List<SysWechatUser> userNum = sysWechatUserService.findByAccIdAndModuleId(accId, moduleId);
		request.setAttribute("userNum",CollectionUtils.isEmpty(userNum) ? 0 : userNum.size());
		//参与抽奖用户量
		request.setAttribute("joinedUserNum",userTicketManager.findJoinedUserTotal());
		//参与抽奖人次
		request.setAttribute("joinNum",userTicketManager.findUserJoinNum());
		request.setAttribute("userTickets", userTicketManager.statistics(null));
		return TICKET_STATISTICS;
	}
	
	@RequestMapping(value="constestant_name_to_spell")
	@ResponseBody
	public JsonResult nameToSpell(){
		JsonResult jr = new JsonResult();
		try {
			jr.appendData("size",contestantManager.nameToSpell());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}
	
	@RequestMapping(value="vote_statistics/{accId}/{moduleId}")
	public String voteStatistics(HttpServletRequest request,@PathVariable Long accId,@PathVariable Long moduleId){
		
		request.setAttribute("contestants",contestantManager.findByVoteStep(Contestant.VOTESTEP1));
		//用户点击量
		List<UserViewHis> viewNum = userViewHisManager.findByViewType(UserViewHis.VIEWTYPE1);
		request.setAttribute("viewNum",CollectionUtils.isEmpty(viewNum) ? 0 : viewNum.size());
		//参与用户量
		List<SysWechatUser> userNum = sysWechatUserService.findByAccIdAndModuleId(accId, moduleId);
		request.setAttribute("userNum",CollectionUtils.isEmpty(userNum) ? 0 : userNum.size());
		request.setAttribute("joinedUserNum", userVoteManager.findJoinedUserNum(Contestant.VOTESTEP1));
		return "/module/daling/vote/vote_statistics";
	}
	
	@RequestMapping(value="vote_share_index")
	public String vote_share(HttpServletRequest request){
		
		return "/module/daling/vote/vote_share_index";
	}
	
	@RequestMapping(value="campion_share_index")
	public String campionShare(HttpServletRequest request){
		
		return "/module/daling/vote/campion_share_index";
	}
	
	@RequestMapping(value="vote_champion/{accId}/{moduleId}")
	public String voteChampion(HttpServletRequest request,@PathVariable Long accId,@PathVariable Long moduleId){
		
		request.setAttribute("contestants",contestantManager.findByVoteStep(Contestant.VOTESTEP2));
		//用户点击量
		List<UserViewHis> viewNum = userViewHisManager.findByViewType(UserViewHis.VIEWTYPE3);
		request.setAttribute("viewNum",CollectionUtils.isEmpty(viewNum) ? 0 : viewNum.size());
		//参与用户量
		List<SysWechatUser> userNum = sysWechatUserService.findByAccIdAndModuleId(accId, moduleId);
		request.setAttribute("userNum",CollectionUtils.isEmpty(userNum) ? 0 : userNum.size());
		request.setAttribute("joinedUserNum", userVoteManager.findJoinedUserNum(Contestant.VOTESTEP2));
		
		return "/module/daling/vote/champion_statistics";
	}
	
	@RequestMapping(value="begin_vote")
	@ResponseBody
	public JsonResult beginVote(HttpServletRequest request,Byte voteStep){
		
		JsonResult jr = new JsonResult();
		Date now = Dates.now();
		String dateStr = DateUtil.formatDateDefault(now);
		jr.appendData("dateNow",dateStr);
		SessionFace.setAttribute(request,TIME_KEY,dateStr);
		try {
			contestantManager.beginVote(voteStep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}
	
	@RequestMapping(value="end_vote")
	@ResponseBody
	public JsonResult endVote(HttpServletRequest request,Byte voteStep){
		
		JsonResult jr = new JsonResult();
		SessionFace.removeAttribute(request, TIME_KEY);
		try {
			contestantManager.endVote(voteStep);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jr;
	}
}
