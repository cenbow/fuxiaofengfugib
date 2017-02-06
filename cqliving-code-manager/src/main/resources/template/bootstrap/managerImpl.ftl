package ${names.managerImplPackage};

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ${names.managerPackage}.${names.managerClassName};
import ${names.daoPackage}.${names.daoClassName};
import ${names.domainPackage}.${names.domainClassName};
import com.cqliving.framework.common.service.EntityServiceImpl;

import org.springframework.stereotype.Service;

@Service("${names.managerClassName?uncap_first}")
public class ${names.managerImplClassName} extends EntityServiceImpl<${names.domainClassName}, ${names.daoClassName}> implements ${names.managerClassName} {
	<#if table.isContainsStatusField >
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(${names.domainClassName}.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
	@Transactional(value="transactionManager")
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(status, idList);
	}
	</#if>
}
