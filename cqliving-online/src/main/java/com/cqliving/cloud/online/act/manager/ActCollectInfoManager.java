package com.cqliving.cloud.online.act.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActCollectInfo;
import com.cqliving.cloud.online.act.dto.ActCollectInfoDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动信息收集表 Manager
 * Date: 2016-06-07 09:14:12
 * @author Code Generator
 */
public interface ActCollectInfoManager extends EntityService<ActCollectInfo> {
	
	/**
	 * Title:根据app获得收集集合
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月8日
	 * @param appId
	 * @return
	 */
	List<ActCollectInfo> getByApp(Long appId);
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    public PageInfo<ActCollectInfoDto> queryPage(PageInfo<ActCollectInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
    
    /**
     * 通过id查询单个信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    public ActCollectInfoDto findById(Long id);
    
    /**
     * 保存
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月12日上午11:30:22
     */
    public void saveInfo(ActCollectInfo domain,String[] value);
    
    /**
     * 通过id删除
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月13日上午10:19:18
     */
    public void delById(Long... ids);
}
