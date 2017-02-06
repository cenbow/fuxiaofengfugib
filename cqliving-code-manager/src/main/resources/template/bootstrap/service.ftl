package ${names.servicePackage};

import java.util.Map;

import com.cqliving.framework.common.dao.support.PageInfo;
import ${names.domainPackage}.${names.domainClassName};
import com.cqliving.tool.common.Response;

/**
 * ${table.comment} Service
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 * @author Code Generator
 */
public interface ${names.serviceClassName} {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<${names.domainClassName}>> queryForPage(PageInfo<${names.domainClassName}> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<${names.domainClassName}> get(Long id);
	public Response<Void> delete(Long... id);
	<#if table.isContainsStatusField >
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	</#if>
	public Response<Void> save(${names.domainClassName} domain);
	/** @author Code Generator *****end*****/
	
}
