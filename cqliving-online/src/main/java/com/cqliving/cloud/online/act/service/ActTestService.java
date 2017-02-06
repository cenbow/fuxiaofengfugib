package com.cqliving.cloud.online.act.service;

import java.util.List;
import java.util.Map;

import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 活动答题表 Service
 * Date: 2016-06-07 09:22:07
 * @author Code Generator
 */
public interface ActTestService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<ActTest>> queryForPage(PageInfo<ActTest> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<ActTest> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> deleteLogic(Long... id);
	public Response<Void> updateStatus(Byte status,Long... ids);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月12日
	 * @param actTest
	 * @param actTestClassifyList
	 * @param actTestCollectList
	 * @return
	 */
	public Response<Void> save(ActTest actTest, Long[] classifyIds, Integer[] classifySortNos, List<ActTestCollect> actTestCollectList);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月21日
	 * @param actInfoId
	 * @param actInfoListId
	 * @return
	 */
	Response<ActTest> getByInfoList(Long actInfoId, Long actInfoListId);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月27日
	 * @param actTestClassifyId
	 * @return
	 */
	Response<ActTest> getByActTestClassify(Long actTestClassifyId);
}
