package com.cqliving.cloud.online.info.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.info.dao.InfoSurveyRelationDao;
import com.cqliving.cloud.online.info.domain.InfoSurveyRelation;
import com.cqliving.cloud.online.info.manager.InfoSurveyRelationManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("infoSurveyRelationManager")
public class InfoSurveyRelationManagerImpl extends EntityServiceImpl<InfoSurveyRelation, InfoSurveyRelationDao> implements InfoSurveyRelationManager {

}
