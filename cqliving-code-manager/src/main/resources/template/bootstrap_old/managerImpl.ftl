package ${names.managerImplPackage};


import ${names.managerPackage}.${names.managerClassName};
import ${names.daoPackage}.${names.daoClassName};
import ${names.domainPackage}.${names.domainClassName};
import com.cqliving.framework.common.service.EntityServiceImpl;

import org.springframework.stereotype.Service;

@Service("${names.managerClassName?uncap_first}")
public class ${names.managerImplClassName} extends EntityServiceImpl<${names.domainClassName}, ${names.daoClassName}> implements ${names.managerClassName} {

}
