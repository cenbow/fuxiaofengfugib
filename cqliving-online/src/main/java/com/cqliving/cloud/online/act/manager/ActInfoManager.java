package com.cqliving.cloud.online.act.manager;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.dto.ActInfoDto;
import com.cqliving.cloud.online.interfacc.dto.ActExportDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动表 Manager
 * Date: 2016-06-07 09:21:22
 * @author Code Generator
 */
public interface ActInfoManager extends EntityService<ActInfo> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public void deleteLogic(String updator,Long updateUserId, Long... ids);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	public void updateStatus(Byte status,String updator,Long updateUserId,Long... ids);
	
	public ActInfoDto findById(Long id);
	
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月7日上午10:37:12
     */
    public PageInfo<ActInfoDto> queryPage(PageInfo<ActInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
	//保存及修改
	public ActInfoDto saveOrUpdate(ActInfoDto actInfoDto);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
	public void updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids);
	
	/**
	 * Title:根据活动答题ID获得活动信息
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月30日
	 * @param actInfoListId
	 * @return
	 */
	ActInfoDto findByActTest(Long actInfoListId);
	
	/**
	 * Title:根据actinfolist表获得活动信息
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年7月7日
	 * @param actInfoListId
	 * @param appId
	 * @return
	 */
	ActInfoDto findByActInfoListId(Long actInfoListId);
	
    /**
     * Title:推荐到首页
     * <p>Description:</p>
     * @author huxiaoping on 2016年7月14日
     * @param ActInfo
     * @return
     */
    public void recommend(ActInfo act);
    
    /**
     * Title:取消推荐到首页
     * <p>Description:</p>
     * @author huxiaoping on 2016年7月14日
     * @param updator
     * @param updateUserId
     * @param id
     * @return
     */
    public void cancelRecommend(String updator,Long updateUserId,Long id);
    /**
     * Title:数据导出
     * <p>Description:</p>
     * @author FangHiLin on 2016年12月7日
     * @param classfyId
     * @return
     */
    public List<ActExportDto> queryExportList(Long classfyId);
}
