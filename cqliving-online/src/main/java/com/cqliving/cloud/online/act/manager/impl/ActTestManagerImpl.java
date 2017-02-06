package com.cqliving.cloud.online.act.manager.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.act.dao.ActInfoListDao;
import com.cqliving.cloud.online.act.dao.ActTestCollectDao;
import com.cqliving.cloud.online.act.dao.ActTestDao;
import com.cqliving.cloud.online.act.domain.ActInfoList;
import com.cqliving.cloud.online.act.domain.ActTest;
import com.cqliving.cloud.online.act.domain.ActTestClassify;
import com.cqliving.cloud.online.act.domain.ActTestCollect;
import com.cqliving.cloud.online.act.manager.ActTestClassifyManager;
import com.cqliving.cloud.online.act.manager.ActTestManager;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("actTestManager")
public class ActTestManagerImpl extends EntityServiceImpl<ActTest, ActTestDao> implements ActTestManager {
	
	@Autowired
	private ActTestClassifyManager actTestClassifyManager;
	@Autowired
	private ActTestCollectDao actTestCollectDao;
	@Autowired
	private ActTestDao actTestDao;
	@Autowired
	private ActInfoListDao actInfoListDao;
	
	/**
	 * 逻辑删除
	 * @param ids
	 * @return
	 */
	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int deleteLogic(Long[] ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ActTest.STATUS99, idList);
	}
	
	/**
	 * 修改状态
	 * @param status 状态
	 * @param ids
	 * @return
	 */
	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public int updateStatus(Byte status,Long... ids){
		List<Long> idList = Arrays.asList(ids);
		return this.getEntityDao().updateStatus(ActTest.STATUS99, idList);
	}

	@Override
    @Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void save(ActTest actTest, Long[] classifyIds, Integer[] classifySortNos, List<ActTestCollect> actTestCollectList) {
		Date date = new Date();
		List<ActTestCollect> collectListOld = null;
		//保存act_info_list 表
		if(actTest.getActInfoListId() == null){
			ActInfoList actInfoList = new ActInfoList();
			actInfoList.setActInfoId(actTest.getActInfoId());
			actInfoList.setAppId(actTest.getAppId());
			actInfoList.setType(ActInfoList.TYPE4);
			actInfoList.setStatus(ActInfoList.STATUS1);
			actInfoList.setStartTime(actTest.getStartTime());
			actInfoList.setEndTime(actTest.getEndTime());
			actInfoList.setCreateTime(date);
			actInfoList.setCreator(actTest.getCreator());
			actInfoList.setCreatorId(actTest.getCreatorId());
			actInfoList.setUpdateTime(date);
			actInfoList.setUpdatorId(actTest.getUpdatorId());
			actInfoList.setUpdator(actTest.getUpdator());
			actInfoList.setShowType(ActInfoList.TYPE4);
			actInfoListDao.save(actInfoList);
			actTest.setActInfoListId(actInfoList.getId());
		}else{
			ActInfoList actInfoList = actInfoListDao.get(actTest.getActInfoListId());
			actInfoList.setStartTime(actTest.getStartTime());
			actInfoList.setEndTime(actTest.getEndTime());
			actInfoList.setUpdateTime(date);
			actInfoList.setUpdatorId(actTest.getUpdatorId());
			actInfoList.setUpdator(actTest.getUpdator());
			actInfoListDao.update(actInfoList);
		}
		//答题保存
		if(actTest.getId() != null){//修改
			actTest.setUpdateTime(date);
			update(actTest);
			
			Map<String, Object> map = Maps.newHashMap();
			map.put("EQ_actTestId", actTest.getId());
			collectListOld = actTestCollectDao.query(map, null);
		}else{//新增
			actTest.setStatus(ActTest.STATUS3);
			actTest.setCreateTime(date);
			actTest.setUpdateTime(date);
			actTest = save(actTest);
		}
		
		//分类保存--只是修改排序和actTestId
		if(classifyIds != null){
			List<Long> ids = Arrays.asList(classifyIds);
			List<ActTestClassify>  list = actTestClassifyManager.getByIds(ids);
			for(ActTestClassify actTestClassify : list){
				for(int i = 0; i < classifyIds.length; i ++){
					if(classifyIds[i].equals(actTestClassify.getId())){
						actTestClassify.setSortNo(classifySortNos[i]);
						break;
					}
				}
				actTestClassify.setActTestId(actTest.getId());
				actTestClassify.setActInfoListId(actTest.getActInfoListId());
				actTestClassify.setCreateTime(date);
				actTestClassifyManager.update(actTestClassify);
			}
		}
		
		//收集信息保存--如果是修改，前面已经删除旧数据
		ActTestCollect tmpCollent = null;
		if(actTestCollectList != null){
			List<ActTestCollect> listTmp = Lists.newArrayList();
			for(ActTestCollect actTestCollect : actTestCollectList){
				tmpCollent = null;
				if(collectListOld != null){
					for(ActTestCollect tmp : collectListOld){
						if(actTestCollect.getActCollectInfoId().equals(tmp.getActCollectInfoId())){
							tmpCollent = tmp;
						}
					}
				}
				if(tmpCollent == null){
					//新增
					actTestCollect.setActTestId(actTest.getId());
					actTestCollect.setCreateTime(date);
					listTmp.add(actTestCollect);
				}else{
					//修改
					tmpCollent.setIsRequired(actTestCollect.getIsRequired());
					actTestCollectDao.update(tmpCollent);
				}
			}
			if(listTmp != null){
				actTestCollectDao.saves(listTmp);
			}
			
		}
		//删除
		if(collectListOld != null){
			for(ActTestCollect tmp : collectListOld){
				tmpCollent = null;
				if(actTestCollectList != null){
					for(ActTestCollect actTestCollect : actTestCollectList){
						if(actTestCollect.getActCollectInfoId().equals(tmp.getActCollectInfoId())){
							tmpCollent = tmp;
						}
					}
				}
				if(tmpCollent == null){
					actTestCollectDao.delete(tmp);
				}
			}
		}
	}

	@Override
	public ActTest getByInfoList(Long actInfoId, Long actInfoListId) {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = new Date();
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("EQ_actInfoId", actInfoId);
		map.put("EQ_actInfoListId", actInfoListId);
//		map.put("LTE_startTime", sdf.format(date));
//		map.put("GTE_endTime", sdf.format(date));
		
		Map<String, Boolean> sortMap = Maps.newHashMap();
		List<ActTest> list = this.query(map, sortMap);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public ActTest getByActTestClassify(Long actTestClassifyId) {
		return actTestDao.getByActTestClassify(actTestClassifyId);
	}
}
