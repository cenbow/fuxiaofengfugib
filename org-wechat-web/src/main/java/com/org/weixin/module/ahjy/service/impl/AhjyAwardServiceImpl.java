package com.org.weixin.module.ahjy.service.impl;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.module.ahjy.dao.AhjyAwardDao;
import com.org.weixin.module.ahjy.domain.AhjyAward;
import com.org.weixin.module.ahjy.service.AhjyAwardService;

@Service("ahjyAwardService")
public class AhjyAwardServiceImpl extends EntityServiceImpl<AhjyAward,AhjyAwardDao> implements AhjyAwardService {

}
