package com.cqliving.cloud.online.security.manager.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.cloud.online.security.dao.SysRoleDao;
import com.cqliving.cloud.online.security.domain.SysResource;
import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.cloud.online.security.manager.SysRoleService;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.Response;

@Service("sysRoleService")
public class SysRoleServiceImpl extends EntityServiceImpl<SysRole, SysRoleDao> implements SysRoleService {

    //private Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
    public void update(SysRole o) throws BusinessException {
        super.update(o);
    }

	@Override
	public List<SysRole> findByAppId(Long appId,Byte usertype) {
		
		if(null != usertype && SysUser.USERTYPE1.byteValue()== usertype.byteValue()){
			return this.getEntityDao().findManagerRole();
		}
		
		if(null == appId || appId <= 0){
			return this.getAll();
		}
		
		return this.getEntityDao().findByAppIdOrderByIdAsc(appId);
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.security.service.SysRoleService#giveCommonRoleByAppId(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void giveCommonRoleByAppId(Long appId) {
		
		//公共角色
		List<SysRole> commonRoles = this.findCommonRole();
		//APP拥有的角色
		List<SysRole> appIdRole = this.getEntityDao().findByAppIdOrderByIdAsc(appId);
		
		for(SysRole commonRole : commonRoles){
			
			if(!appIdRole.contains(commonRole)){//没有包含公共角色
				SysRole sysRole = new SysRole();
				sysRole.setAppId(appId);
				sysRole.setCommonRoleCode(commonRole.getCommonRoleCode());
				sysRole.setDescn(commonRole.getDescn());
				sysRole.setOrgId(commonRole.getOrgId());
				sysRole.setRoleName(commonRole.getRoleName());
				sysRole.setType(SysRole.TYPE2);
				
				Set<SysResource> commonSysResource = commonRole.getRescs();
				
				if(CollectionUtils.isNotEmpty(commonSysResource)){
					
					Set<SysResource> newResource = new HashSet<SysResource>(commonSysResource);
					sysRole.setRescs(newResource);
				}
				this.getEntityDao().saveAndFlush(sysRole);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.security.service.SysRoleService#findCommonRole()
	 */
	@Override
	public List<SysRole> findCommonRole() {
		
		return this.getEntityDao().findCommonRole();
	}

	/**
     * Title:
     * <p>Description:通过appId和公共角色CODE查找角色</p>
     * @author huxiaoping on 2016年5月19日
     * @return
     */
    @Override
    public SysRole getByAppIdAndCode(Long appId, String commonRoleCode) {
        List<SysRole> roleList = this.getEntityDao().getByAppIdAndCode(appId, commonRoleCode);
        if(null!=roleList&&roleList.size()>0){
            return roleList.get(0);
        }
        return null;
    }

	/* (non-Javadoc)
	 * @see com.cqliving.cloud.online.security.manager.SysRoleService#deleteRole(java.lang.Long)
	 */
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public Response<Void> deleteRole(Long roleId) {
		Response<Void> rp = Response.newInstance();
		try {
			SysRole role = this.get(roleId);
			
			if(null != role && SysRole.TYPE1.byteValue() == role.getType().byteValue()){
				rp.setCode(ErrorCodes.FAILURE);
				rp.setMessage("该角色为公共角色，不能删除");
				return rp;
			}
			if(StringUtils.isNotBlank(role.getCommonRoleCode())){
				rp.setCode(ErrorCodes.FAILURE);
				rp.setMessage("系统内置角色，不能删除");
				return rp;
			}
			List<SysRole>  list = this.getEntityDao().findSysUserRoleByRoleId(roleId);
			if(CollectionUtils.isEmpty(list)){
				this.getEntityDao().delete(roleId);
			}else{
				rp.setCode(ErrorCodes.FAILURE);
				rp.setMessage("该角色已被用户使用，不能删除");
			}
		} catch (BusinessException e) {
			rp.setCode(ErrorCodes.FAILURE);
			rp.setMessage("删除角色失败");
		}
		return rp;
	}
}
