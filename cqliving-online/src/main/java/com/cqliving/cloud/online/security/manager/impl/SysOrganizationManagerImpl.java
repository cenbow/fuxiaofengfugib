package com.cqliving.cloud.online.security.manager.impl;


import org.springframework.stereotype.Service;

import com.cqliving.cloud.online.security.dao.SysOrganizationDao;
import com.cqliving.cloud.online.security.domain.SysOrganization;
import com.cqliving.cloud.online.security.manager.SysOrganizationManager;
import com.cqliving.framework.common.service.EntityServiceImpl;

@Service("sysOrganizationManager")
public class SysOrganizationManagerImpl extends EntityServiceImpl<SysOrganization, SysOrganizationDao> implements SysOrganizationManager {

}
