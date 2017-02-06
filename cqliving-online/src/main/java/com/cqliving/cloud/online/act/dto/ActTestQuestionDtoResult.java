package com.cqliving.cloud.online.act.dto;

import java.util.List;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月29日
 */
public class ActTestQuestionDtoResult {
	
	/** 答题用时多少分钟 **/
	private Long useTime;
	/** 答错题数 **/
	private Integer errorCount;
	/** 获得分数 **/
	private Integer score;
	/** 开始答题时间 **/
	private Long startTime;
	/** 问题列表 **/
	private List<ActTestQuestionResult> questionList;
	public Long getUseTime() {
		return useTime;
	}
	public void setUseTime(Long useTime) {
		this.useTime = useTime;
	}
	public Integer getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public List<ActTestQuestionResult> getQuestionList() {
		return questionList;
	}
	public void setQuestionList(List<ActTestQuestionResult> questionList) {
		this.questionList = questionList;
	}
	
}
