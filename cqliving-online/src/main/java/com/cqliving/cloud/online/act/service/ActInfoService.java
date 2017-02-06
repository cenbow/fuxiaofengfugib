package com.cqliving.cloud.online.act.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActInfo;
import com.cqliving.cloud.online.act.dto.ActInfoDto;
import com.cqliving.cloud.online.interfacc.dto.ActExportDto;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动表 Service
 * Date: 2016-06-07 09:21:22
 * @author Code Generator
 */
public interface ActInfoService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActInfo>> queryForPage(PageInfo<ActInfo> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActInfo> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(String updator,Long updateUserId,Long... id);
	public Response<Void> updateStatus(Byte status,String updator,Long updateUserId,Long... ids);
	public Response<Void> save(ActInfo domain);
	/** @author Code Generator *****end*****/
	public Response<ActInfoDto> findById(Long id);
	/**
     * 分页查询
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年6月7日上午10:37:12
     */
    public Response<PageInfo<ActInfoDto>> queryPage(PageInfo<ActInfoDto> pageInfo,Map<String, Object> map, Map<String, Boolean> orderMap);
	
	//保存及修改
	public Response<ActInfoDto> saveOrUpdate(ActInfoDto actInfoDto);
	
	/**
     * 修改排序号
     * @author huxiaoping
     * @param sortNo
     * @param updator
     * @param updatorId
     * @param ids
     * @return
     */
    public Response<Void> updateSortNo(Integer sortNo,String updator,Long updateUserId,Long... ids);
    
    /**
     * Title:根据活动答题ID获得活动信息
     * <p>Description:</p>
     * @author DeweiLi on 2016年6月30日
     * @param actInfoListId
     * @return
     */
    Response<ActInfoDto> findByActTest(Long actInfoListId);
    
    /**
     * Title:根据actinfolistId获得活动信息
     * <p>Description:</p>
     * @author DeweiLi on 2016年7月7日
     * @param actInfoListId
     * @return
     */
    Response<ActInfoDto> findByActInfoListId(Long actInfoListId);
    
    /**
     * Title:推荐到首页
     * <p>Description:</p>
     * @author huxiaoping on 2016年7月14日
     * @param ActInfo
     * @return
     */
    public Response<Void> recommend(ActInfo act);
    
    /**
     * Title:取消推荐到首页
     * <p>Description:</p>
     * @author huxiaoping on 2016年7月14日
     * @param updator
     * @param updateUserId
     * @param id
     * @return
     */
    public Response<Void> cancelRecommend(String updator,Long updateUserId,Long id);
    /**
     * Title:数据导出
     * <p>Description:</p>
     * @author FangHuiLin on 2016年12月7日
     * @param classfyId
     * @return
     */
	public Response<List<ActExportDto>> actExportList(Long classfyId);
}
