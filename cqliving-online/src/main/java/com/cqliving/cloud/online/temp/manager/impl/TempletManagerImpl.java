package com.cqliving.cloud.online.temp.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.temp.dao.TempletDao;
import com.cqliving.cloud.online.temp.domain.Templet;
import com.cqliving.cloud.online.temp.manager.TempletManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("templetManager")
public class TempletManagerImpl extends EntityServiceImpl<Templet, TempletDao> implements TempletManager {

}
