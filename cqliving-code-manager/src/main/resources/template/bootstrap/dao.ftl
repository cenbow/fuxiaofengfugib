package ${names.daoPackage};

<#if table.isContainsStatusField >
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
</#if>

import com.cqliving.framework.common.dao.jpa.EntityJpaDao;
import ${names.domainPackage}.${names.domainClassName};

/**
 * ${table.comment} JPA Dao
 * Date: ${datetime("yyyy-MM-dd HH:mm:ss")}
 * @author Code Generator
 */
public interface ${names.daoClassName} extends EntityJpaDao<${names.domainClassName}, Long> {
	<#if table.isContainsStatusField >
	/**
	 * 修改状态
	 * @author Code Generator
     * @param ids
     * @return
     */
	@Modifying
    @Query("update ${names.domainClassName} set status = ?1 where id in ?2")
    public int updateStatus(Byte status,List<Long> ids);
    </#if>
}
