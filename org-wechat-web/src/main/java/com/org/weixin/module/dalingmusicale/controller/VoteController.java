/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.org.weixin.module.dalingmusicale.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.exception.BusinessException;
import org.wechat.framework.web.support.JsonResult;

import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.CommonController;
import com.org.weixin.module.dalingmusicale.domain.Contestant;
import com.org.weixin.module.dalingmusicale.domain.UserViewHis;
import com.org.weixin.module.dalingmusicale.dto.ShareDto;
import com.org.weixin.module.dalingmusicale.manager.ContestantManager;
import com.org.weixin.module.dalingmusicale.manager.UserViewHisManager;
import com.org.weixin.module.dalingmusicale.manager.UserVoteManager;

/**
 * Title:
 * <p>Description:投票</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
@Controller
@RequestMapping(value="musicale/{accId}")
public class VoteController extends CommonController{

	@Autowired
	UserVoteManager userVoteManager;
	@Autowired
	ContestantManager contestantManager;
	@Autowired
	UserViewHisManager userViewHisManager; 
	
	private final static String VOTE_INDEX= "/module/daling/vote/vote_renqiwang";
	//现场决赛
	private final static String VOTE_CHAMPION = "/module/daling/vote/vote_champion";
	
	@RequestMapping(value="can_vote")
	@ResponseBody
	public JsonResult canVote(HttpServletRequest request,String contestantCode,@RequestParam Byte voteStep){
		JsonResult jr = new JsonResult();
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		try {
			userVoteManager.canVote(sessionUser.getId(), contestantCode,voteStep);
		} catch (BusinessException e) {
			jr.setCode(e.getErrorCode());
			jr.setMessage(e.getMessage());
			e.printStackTrace();
		}catch(Exception e1){
			e1.printStackTrace();
			jr.setCode(-1);
			jr.setMessage(e1.getMessage());
		}
		return jr;
	}
	
	@RequestMapping(value="vote")
	@ResponseBody
	public JsonResult vote(HttpServletRequest request,String contestantCode,@RequestParam Byte voteStep){
		JsonResult jr = new JsonResult();
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		try {
			userVoteManager.userVote(sessionUser.getId(),contestantCode,voteStep);
		} catch (BusinessException e) {
			jr.setCode(e.getErrorCode());
			jr.setMessage(e.getMessage());
			e.printStackTrace();
		}catch(Exception e1){
			jr.setCode(-1);
			jr.setMessage(e1.getMessage());
			e1.printStackTrace();
		}
		return jr; 
	}
	
	@RequestMapping(value="vote_renqiwang")
	public String voteRenqiwang(HttpServletRequest request,@PathVariable Long accId){
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		request.setAttribute("contestant",contestantManager.findByVoteStep(Contestant.VOTESTEP1));
		request.setAttribute("shareDto", ShareDto.getVoteShare());
		userViewHisManager.saveUserViewHis(request.getRequestURI(), "人气王投票首页", sessionUser.getId(), UserViewHis.VIEWTYPE1);
		super.setUserAndShare(request, accId);
		return VOTE_INDEX;
	}
	
	@RequestMapping(value="vote_champion")
	public String voteChampion(HttpServletRequest request,@PathVariable Long accId){
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		request.setAttribute("contestant",contestantManager.findCampion());
		request.setAttribute("shareDto", ShareDto.getChampionShare());
		userViewHisManager.saveUserViewHis(request.getRequestURI(), "现场投票首页", sessionUser.getId(), UserViewHis.VIEWTYPE3);
		super.setUserAndShare(request, accId);
		return VOTE_CHAMPION;
	}
}
