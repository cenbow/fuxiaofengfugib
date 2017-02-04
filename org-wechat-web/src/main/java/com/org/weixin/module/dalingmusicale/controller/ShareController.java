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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wechat.framework.web.support.JsonResult;

import com.cqliving.framework.utils.Dates;
import com.org.common.SessionFace;
import com.org.common.SessionUser;
import com.org.weixin.module.dalingmusicale.domain.UserShareHis;
import com.org.weixin.module.dalingmusicale.manager.UserShareHisManager;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author fuxiaofeng on 2016年9月16日
 */
@Controller
@RequestMapping(value="musicale/{accId}")
public class ShareController {

	@Autowired
	UserShareHisManager userShareHisManager;
	
	@RequestMapping(value="share")
	@ResponseBody
	public JsonResult share(HttpServletRequest request,Byte shareType){
		
		JsonResult jr = new JsonResult();
		SessionUser sessionUser = SessionFace.getSessionUser(request);
		UserShareHis userShare = new UserShareHis();
		userShare.setShareTime(Dates.now());
		userShare.setShareType(shareType);
		userShare.setUserId(sessionUser.getId());
		userShareHisManager.save(userShare);
		return jr;
	}
}
