package ${names.managerPackage};

import com.cqliving.framework.common.service.EntityService;
import ${names.domainPackage}.${names.domainClassName};

/**
 * ${table.comment} Manager
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 * @author Code Generator
 */
public interface ${names.managerClassName} extends EntityService<${names.domainClassName}> {
	<#if table.isContainsStatusField >
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	</#if>
}
