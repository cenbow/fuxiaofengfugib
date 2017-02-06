package com.cqliving.cloud.online.info.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.info.dao.InfoClassifyCommentHisDao;
import com.cqliving.cloud.online.info.domain.InfoClassifyCommentHis;
import com.cqliving.cloud.online.info.manager.InfoClassifyCommentHisManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("infoClassifyCommentHisManager")
public class InfoClassifyCommentHisManagerImpl extends EntityServiceImpl<InfoClassifyCommentHis, InfoClassifyCommentHisDao> implements InfoClassifyCommentHisManager {

}
