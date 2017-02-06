package com.cqliving.cloud.online.manuscript.manager.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.manuscript.manager.ManuscriptLogManager;
import com.cqliving.cloud.online.manuscript.dao.ManuscriptLogDao;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptLog;
import com.cqliving.framework.common.service.EntityServiceImpl;

import org.springframework.stereotype.Service;

@Service("manuscriptLogManager")
public class ManuscriptLogManagerImpl extends EntityServiceImpl<ManuscriptLog, ManuscriptLogDao> implements ManuscriptLogManager {
}
