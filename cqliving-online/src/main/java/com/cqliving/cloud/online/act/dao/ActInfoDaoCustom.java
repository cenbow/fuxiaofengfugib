package com.cqliving.cloud.online.act.dao;

import java.util.Map;

import com.cqliving.cloud.online.act.dto.ActInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;

public interface ActInfoDaoCustom {
    
    /**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月7日上午10:37:12
     */
    public PageInfo<ActInfoDto> queryPage(PageInfo<ActInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
    
    public ActInfoDto findById(Long id);
    
    /**
     * Title:根据actInfoList表的id获得活动信息
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月22日
     * @param actInfoListId
     * @return
     */
    ActInfoDto findByActInfoListId(Long actInfoListId);
    
    /**
     * Title:根据活动答题id获得活动信息
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月30日
     * @param actInfoListId
     * @return
     */
    ActInfoDto findByActTest(Long actInfoListId);
}
