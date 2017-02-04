package com.org.weixin.module.ahjy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.weixin.module.ahjy.service.AhjyAwardService;

@Controller
@RequestMapping(value = "ahjy/{accId}/award")
public class AhjyAwardManagerController {

	@Autowired
	private AhjyAwardService ahjyAwardService;

	

}
