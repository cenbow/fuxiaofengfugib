package com.cqliving.cloud.online.info.manager.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqliving.cloud.online.app.dao.AppResouseDao;
import com.cqliving.cloud.online.app.domain.AppResouse;
import com.cqliving.cloud.online.info.dao.InfoClassifyCommentDao;
import com.cqliving.cloud.online.info.dao.InfoClassifyDao;
import com.cqliving.cloud.online.info.dao.InfoClassifyListDao;
import com.cqliving.cloud.online.info.dao.InformationContentDao;
import com.cqliving.cloud.online.info.dao.InformationDao;
import com.cqliving.cloud.online.info.domain.InfoClassify;
import com.cqliving.cloud.online.info.domain.InfoClassifyComment;
import com.cqliving.cloud.online.info.domain.InfoClassifyList;
import com.cqliving.cloud.online.info.domain.Information;
import com.cqliving.cloud.online.info.domain.InformationContent;
import com.cqliving.cloud.online.info.manager.InfoClassifyCommentManager;
import com.cqliving.cloud.online.survey.dao.SurveyInfoDao;
import com.cqliving.cloud.online.survey.dao.SurveyQuestionAnswerAttachDao;
import com.cqliving.cloud.online.survey.dao.SurveyQuestionAnswerDao;
import com.cqliving.cloud.online.survey.dao.SurveyQuestionDao;
import com.cqliving.cloud.online.survey.domain.SurveyInfo;
import com.cqliving.cloud.online.survey.domain.SurveyQuestion;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswer;
import com.cqliving.cloud.online.survey.domain.SurveyQuestionAnswerAttach;
import com.cqliving.framework.common.service.EntityServiceImpl;
import com.cqliving.tool.common.util.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service("infoClassifyCommentManager")
public class InfoClassifyCommentManagerImpl extends EntityServiceImpl<InfoClassifyComment, InfoClassifyCommentDao> implements InfoClassifyCommentManager {
	
	@Autowired
	private AppResouseDao appResouseDao;;
	@Autowired
	private InfoClassifyDao infoClassifyDao;
	@Autowired
	private InfoClassifyListDao infoClassifyListDao;
	@Autowired
	private InformationContentDao informationContentDao;
	@Autowired
	private InformationDao informationDao;
	@Autowired
	private SurveyInfoDao surveyInfoDao;
	@Autowired
	private SurveyQuestionAnswerAttachDao surveyQuestionAnswerAttachDao;
	@Autowired
	private SurveyQuestionAnswerDao surveyQuestionAnswerDao;
	@Autowired
	private SurveyQuestionDao surveyQuestionDao;

	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void ignore(Long id) {
		getEntityDao().changeStatus(id, InfoClassifyComment.STATUS1);
	}
	
	@Override
	@Transactional(value="transactionManager",rollbackFor=Throwable.class)
	public void publishToColumn(Long appId, Long infoClassifyId, Long id, Long appColumnId, String userName, Long userId) {
		Date now = DateUtil.now();
		InfoClassify sourceInfoClassify = infoClassifyDao.get(infoClassifyId);
		Long sourceInformationId = sourceInfoClassify.getInformationId();
		Information sourceInformation = informationDao.get(sourceInformationId);
		//保存资讯详情表
		Information information = new Information();
		BeanUtils.copyProperties(sourceInformation, information, "id");
		information.setAppId(appId);
		information.setViewCount(0);
		information.setReplyCount(0);
		information.setCreateTime(now);
		information.setCreator(userName);
		information.setCreatorId(userId);
		information.setUpdateTime(now);
		information.setUpdator(userName);
		information.setUpdatorId(userId);
		informationDao.save(information);
		Long informationId = information.getId();
		//保存资讯表
		InfoClassify infoClassify = new InfoClassify();
		BeanUtils.copyProperties(sourceInfoClassify, infoClassify, "id");
		infoClassify.setAppId(appId);
		infoClassify.setColumnsId(appColumnId);
		infoClassify.setCreateTime(now);
		infoClassify.setCreator(userName);
		infoClassify.setCreatorId(userId);
		infoClassify.setSendStatus(InfoClassify.SENDSTATUS0);
		infoClassify.setInformationId(informationId);
		infoClassify.setReplyCount(0);
		infoClassify.setSourceAppId(sourceInfoClassify.getAppId());
		infoClassify.setSourceInfoClassifyId(infoClassifyId);
		infoClassify.setUpdateTime(now);
		infoClassify.setUpdator(userName);
		infoClassify.setUpdatorId(userId);
		infoClassify.setViewCount(0);
		infoClassifyDao.save(infoClassify);
		Long icid = infoClassify.getId();
		//保存资讯栏目列表图片表
		List<InfoClassifyList> sourceInfoClassifyListList = infoClassifyListDao.getByInfoClassify(sourceInfoClassify.getId());
		if (CollectionUtils.isNotEmpty(sourceInfoClassifyListList)) {
			InfoClassifyList infoClassifyList;
			List<InfoClassifyList> infoClassifyLists = Lists.newArrayList();
			for (InfoClassifyList obj : sourceInfoClassifyListList) {
				infoClassifyList = new InfoClassifyList();
				BeanUtils.copyProperties(obj, infoClassifyList, "id");
				infoClassifyList.setClassifyId(icid);
				infoClassifyList.setColumnsId(appColumnId);
				infoClassifyList.setInformationId(informationId);
				infoClassifyLists.add(infoClassifyList);
			}
			infoClassifyListDao.save(infoClassifyLists);
		}
		//保存资讯内容表
		List<InformationContent> sourceInformationContents = informationContentDao.getByInformation(sourceInformationId);
		Map<Long, Long> infoContentIdMap = Maps.newHashMap();
		if (CollectionUtils.isNotEmpty(sourceInformationContents)) {
			InformationContent informationContent;
			for (InformationContent obj : sourceInformationContents) {
				informationContent = new InformationContent();
				BeanUtils.copyProperties(obj, informationContent, "id");
				informationContent.setAppId(appId);
				informationContent.setInformationId(informationId);
				informationContentDao.save(informationContent);
				infoContentIdMap.put(obj.getId(), informationContent.getId());	//记录原对象及新对象的 id 关系
			}
		}
		//保存资讯资源表
		List<AppResouse> sourceAppResourses = appResouseDao.findByInformationId(sourceInformationId);
		if (CollectionUtils.isNotEmpty(sourceAppResourses)) {
			AppResouse appResouse;
			List<AppResouse> appResouses = Lists.newArrayList();
			for (AppResouse obj : sourceAppResourses) {
				appResouse = new AppResouse();
				BeanUtils.copyProperties(obj, appResouse, "id");
				appResouse.setAppId(appId);
				appResouse.setInformationContentId(infoContentIdMap.get(obj.getInformationContentId()));
				appResouse.setInformationId(informationId);
				appResouses.add(appResouse);
			}
			appResouseDao.save(appResouses);
		}
		//保存调研表
		List<SurveyInfo> sourceSurveyInfos = surveyInfoDao.getByInformation(sourceInformationId);
		if (CollectionUtils.isNotEmpty(sourceSurveyInfos)) {
			SurveyInfo surveyInfo;
			for (SurveyInfo obj : sourceSurveyInfos) {
				surveyInfo = new SurveyInfo();
				BeanUtils.copyProperties(obj, surveyInfo, "id");
				surveyInfo.setAppId(appId);
				surveyInfo.setInformationId(informationId);
				surveyInfo.setInformationContentId(infoContentIdMap.get(obj.getInformationContentId()));
				surveyInfoDao.save(surveyInfo);
				//保存调研问题表
				List<SurveyQuestion> sourceSurveyQuestions = surveyQuestionDao.findBySurveyId(obj.getId());
				if (CollectionUtils.isNotEmpty(sourceSurveyQuestions)) {
					SurveyQuestion surveyQuestion;
					for (SurveyQuestion obk : sourceSurveyQuestions) {
						surveyQuestion = new SurveyQuestion();
						BeanUtils.copyProperties(obk, surveyQuestion, "id");
						surveyQuestion.setAppId(appId);
						surveyQuestion.setSurveyId(surveyInfo.getId());
						surveyQuestion.setCreateTime(now);
						surveyQuestionDao.save(surveyQuestion);
						//保存调研答案表
						List<SurveyQuestionAnswer> sourceSurveyQuestionAnswers = surveyQuestionAnswerDao.getByQuestion(obk.getId());
						if (CollectionUtils.isNotEmpty(sourceSurveyQuestionAnswers)) {
							SurveyQuestionAnswer surveyQuestionAnswer;
							for (SurveyQuestionAnswer obl : sourceSurveyQuestionAnswers) {
								surveyQuestionAnswer = new SurveyQuestionAnswer();
								BeanUtils.copyProperties(obl, surveyQuestionAnswer, "id");
								surveyQuestionAnswer.setQuestionId(surveyQuestion.getId());
								surveyQuestionAnswerDao.save(surveyQuestionAnswer);
								//保存调研答案附加属性
								List<SurveyQuestionAnswerAttach> sourceSurveyQuestionAnswerAttachs = surveyQuestionAnswerAttachDao.getByAnswer(obl.getId());
								if (CollectionUtils.isNotEmpty(sourceSurveyQuestionAnswerAttachs)) {
									SurveyQuestionAnswerAttach surveyQuestionAnswerAttach;
									for (SurveyQuestionAnswerAttach obm : sourceSurveyQuestionAnswerAttachs) {
										surveyQuestionAnswerAttach = new SurveyQuestionAnswerAttach();
										BeanUtils.copyProperties(obm, surveyQuestionAnswerAttach, "id");
										surveyQuestionAnswerAttach.setAppId(appId);
										surveyQuestionAnswerAttach.setSurveyId(surveyInfo.getId());
										surveyQuestionAnswerAttach.setQuestionId(surveyQuestion.getId());
										surveyQuestionAnswerAttach.setAnswerId(surveyQuestionAnswer.getId());
										surveyQuestionAnswerAttachDao.save(surveyQuestionAnswerAttach);
									}
								}
							}
						}
					}
				}
			}
		}
		//将新发布的新闻id回写到推荐表中，并修改推荐状态
		InfoClassifyComment comment = get(id);
		comment.setNewInfoClassifyId(icid);
		comment.setStatus(InfoClassifyComment.STATUS2);
		update(comment);
	}

}