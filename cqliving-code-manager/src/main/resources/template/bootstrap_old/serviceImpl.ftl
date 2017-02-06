package ${names.serviceImplPackage};

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqliving.cloud.common.ErrorCodes;
import com.cqliving.framework.common.dao.support.PageInfo;
import ${names.domainPackage}.${names.domainClassName};
import ${names.managerPackage}.${names.managerClassName};
import ${names.servicePackage}.${names.serviceClassName};
import com.cqliving.tool.common.Response;
import com.cqliving.tool.common.web.annotation.ServiceHandleMapping;
import com.cqliving.tool.common.web.annotation.ServiceMethodHandle;
import com.cqliving.tool.common.web.annotation.ExceptionHandle;
import com.cqliving.framework.common.exception.BusinessException;

@Service("${names.serviceClassName?uncap_first}")
@ServiceHandleMapping(managerClass = ${names.managerClassName}.class)
public class ${names.serviceImplClassName} implements ${names.serviceClassName} {

	private static final Logger logger = LoggerFactory.getLogger(${names.serviceImplClassName}.class);
	
	@Autowired
	private ${names.managerClassName} ${names.managerClassName?uncap_first};
	
	@ServiceMethodHandle(managerMethodName="query",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="分页查询${table.comment}失败")})
	public Response<PageInfo<${names.domainClassName}>> queryForPage(PageInfo<${names.domainClassName}> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="get",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="查询${table.comment}失败")})
	public Response<${names.domainClassName}> get(Long id) {
		return null;
	}
	
	@ServiceMethodHandle(managerMethodName="removeById",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="删除${table.comment}失败")})
	public Response<Void> delete(Long id) {
		return null;
	}

	@Override
	@ServiceMethodHandle(managerMethodName="save",exceptionClass={@ExceptionHandle(exception=BusinessException.class,errorCode=ErrorCodes.FAILURE,errorMsg="保存${table.comment}失败")})
	public Response<Void> save(${names.domainClassName} ${names.domainClassName?uncap_first}) {
		return null;
	}
}
