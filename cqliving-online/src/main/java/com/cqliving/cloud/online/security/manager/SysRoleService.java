package com.cqliving.cloud.online.security.manager;

import java.util.List;

import com.cqliving.cloud.online.security.domain.SysRole;
import com.cqliving.framework.common.service.EntityService;
import com.cqliving.tool.common.Response;

/**
 * 系统角色表 Service
 *
 * Date: 2015-05-12 15:40:19
 *
 * @author Code Generator
 *
 */
public interface SysRoleService extends EntityService<SysRole> {

	
	/**
	 * Title:
	 * <p>Description:根据用户ID取角色列表</p>
	 * @author fuxiaofeng on 2016年4月20日
	 * @param userId
	 * @return
	 */
	public List<SysRole> findByAppId(Long appId,Byte usertype);
	
	/**
	 * Title:
	 * <p>Description:给APPID赋予公共角色</p>
	 * @author fuxiaofeng on 2016年5月19日
	 * @param appId
	 */
	public void giveCommonRoleByAppId(Long appId);
	
	/**
	 * Title:
	 * <p>Description:查找公共角色</p>
	 * @author fuxiaofeng on 2016年5月19日
	 * @return
	 */
	public List<SysRole> findCommonRole();
	
	/**
	 * Title:
	 * <p>Description:通过appId和公共角色CODE查找角色</p>
	 * @author huxiaoping on 2016年5月19日
	 * @return
	 */
	public SysRole getByAppIdAndCode(Long appId,String commonRoleCode);
	
	//删除角色
	public Response<Void> deleteRole(Long roleId);
}
