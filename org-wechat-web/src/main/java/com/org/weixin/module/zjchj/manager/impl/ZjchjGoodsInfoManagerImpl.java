package com.org.weixin.module.zjchj.manager.impl;

import org.springframework.stereotype.Service;
import org.wechat.framework.service.EntityServiceImpl;

import com.org.weixin.module.zjchj.dao.ZjchjGoodsInfoDao;
import com.org.weixin.module.zjchj.domain.ZjchjGoodsInfo;
import com.org.weixin.module.zjchj.manager.ZjchjGoodsInfoManager;

@Service("zjchjGoodsInfoManager")
public class ZjchjGoodsInfoManagerImpl extends EntityServiceImpl<ZjchjGoodsInfo, ZjchjGoodsInfoDao> implements ZjchjGoodsInfoManager {
}
