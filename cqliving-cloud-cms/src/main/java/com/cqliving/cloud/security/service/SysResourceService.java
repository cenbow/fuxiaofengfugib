package com.cqliving.cloud.security.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.security.domain.SysResource;
import com.cqliving.cloud.online.security.dto.SysResourceDto;
import com.cqliving.framework.common.service.EntityService;

/**
 * 系统资源表 Service
 *
 * Date: 2015-05-12 15:39:57
 *
 * @author Code Generator
 *
 */
public interface SysResourceService extends EntityService<SysResource> {

    public int updateStatusById(Long id, Byte status);

    /**
     * 初始化资源数据，不需要事务，初始化失败不影响正常业务
     * @author yuwu 20160705
     */
    public void initAllResourceCache();
    
    /**
     * 批量修改排序值
     * @param ids
     * @param sortNums
     * @param parentIds
     */
    public void sort(Long[] ids, Integer[] sortNums, Long[] parentIds,Long[] sysResTypeIds);

    public List<SysResourceDto> findByConditions(Map<String,Object> searchMap,Map<String,Boolean> sortMap);
    
    public SysResourceDto findLevelOneResource(Long resId);
    
    //一级资源修改资源类型时修改其子资源的资源类型
    public void updateSysResTypeId(Long rescId);
    //按照资源类型分组
    public Map<Long,List<SysResourceDto>> findByGroupResType(Map<String,Object> searchMap,Map<String,Boolean> sortMap);
}
