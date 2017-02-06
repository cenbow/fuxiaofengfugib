package com.cqliving.cloud.online.act.manager;

import java.util.List;

import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.framework.common.service.EntityService;

/**
 * 活动答题表 Manager
 * Date: 2016-06-07 09:22:07
 * @author Code Generator
 */
public interface ActTestManager extends EntityService<ActTest> {
	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	public int deleteLogic(Long[] id);
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids ID
	 * @return
	 */
	public int updateStatus(Byte status,Long... ids);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月12日
	 * @param actTest
	 * @param actTestClassifyList
	 * @param actTestCollectList
	 */
	void save(ActTest actTest, Long[] classifyIds, Integer[] classifySortNos, List<ActTestCollect> actTestCollectList);
	
	/**
	 * Title:根据
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月21日
	 * @param actInfoId
	 * @param actInfoListId
	 * @return
	 */
	ActTest getByInfoList(Long actInfoId, Long actInfoListId);
	
	/**
	 * Title:根据活动分类获得活动配置
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年6月27日
	 * @param actTestClassifyId
	 * @return
	 */
	ActTest getByActTestClassify(Long actTestClassifyId);
}
