package com.cqliving.cloud.online.act.dto;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2016年6月23日
 */
public class UserActTestClassifyResult {
	
	/** 用户分类答题历史表（user_act_test_classify_historyt表主键） */
	private Long testClassifyHistoryId;
	/** 答题表ID（act_test_id表主键） */
	private Long actTestId;
	/** 答题分类表ID（act_test_classify表主键） */
	private Long actTestClassifyId;
	/** 用户答题获得的分数 */
	private Integer score;
	/** 答题所有时间*/
	private Long usedTime;
	/** 是否已完成 */
	private Byte isFinish;
	
	public Long getTestClassifyHistoryId() {
		return testClassifyHistoryId;
	}
	public void setTestClassifyHistoryId(Long testClassifyHistoryId) {
		this.testClassifyHistoryId = testClassifyHistoryId;
	}
	public Long getActTestId() {
		return actTestId;
	}
	public void setActTestId(Long actTestId) {
		this.actTestId = actTestId;
	}
	public Long getActTestClassifyId() {
		return actTestClassifyId;
	}
	public void setActTestClassifyId(Long actTestClassifyId) {
		this.actTestClassifyId = actTestClassifyId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Long getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(Long usedTime) {
		this.usedTime = usedTime;
	}
	public Byte getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(Byte isFinish) {
		this.isFinish = isFinish;
	}
	
	
}
