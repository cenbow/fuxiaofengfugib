package com.cqliving.cloud.online.act.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.act.dao.ActTestQuestionDaoCustom;
import com.cqliving.cloud.online.act.domain.ActTestQuestionDto;
import com.cqliving.framework.common.dao.jdbc.MysqlExtendJdbcTemplate;

public class ActTestQuestionDaoImpl implements ActTestQuestionDaoCustom{
	
	@Autowired
	private MysqlExtendJdbcTemplate mysqlExtendJdbcTemplate;

	@Override
	public List<ActTestQuestionDto> getListAndAnswer(Long classifyId, Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select");
		sql.append(" a.ID id, a.act_test_classify_id, a.question, a.image_url, a.`type`, a.score,");
		sql.append(" b.ID answer_id, b.answer, b.image_url answer_image_url, b.answer_desc, b.`type` answer_type, b.is_right_answer");
		if(userId != null && userId > 0)
			sql.append(" ,c.id user_answer_id, c.answer_content");
		sql.append(" from act_test_question a left join act_test_answer b on a.ID=b.act_test_question_id");
		if(userId != null && userId > 0){
			sql.append(" left join user_act_test_classify d on d.act_test_classify_id=a.act_test_classify_id and d.user_id=?");
			sql.append(" left join user_act_test c on d.test_classify_history_id=c.test_classify_history_id and b.act_test_question_id=c.act_test_question_id and b.ID=c.act_test_answer_id and c.user_id=?");
		}
		sql.append(" where a.act_test_classify_id=?");
		sql.append(" order by a.sort_no, b.sort_no");
		if(userId != null && userId > 0){
			return mysqlExtendJdbcTemplate.queryForList(ActTestQuestionDto.class, sql.toString(), userId, userId, classifyId);
		}else{
			return mysqlExtendJdbcTemplate.queryForList(ActTestQuestionDto.class, sql.toString(), classifyId);
		}
	}
	
	@Override
	public List<ActTestQuestionDto> getQuestionRightAnswer(Long classifyId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.id, a.score, b.ID as answer_id from act_test_question a left join act_test_answer b on a.ID=b.act_test_question_id");
		sql.append(" where a.act_test_classify_id=? and b.is_right_answer=1");
		return mysqlExtendJdbcTemplate.queryForList(ActTestQuestionDto.class, sql.toString(), classifyId);
	}

	@Override
	public List<ActTestQuestionDto> validateNullAnswer(Long actInfoListId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select b.id, c.id as answer_id from act_test_classify a");
		sql.append(" left join act_test_question b on a.id=b.act_test_classify_id");
		sql.append(" left join act_test_answer c on b.ID=c.act_test_question_id and c.is_right_answer=1");
		sql.append(" where a.act_info_list_id=?");
		sql.append(") a where answer_id is null");
		return mysqlExtendJdbcTemplate.queryForList(ActTestQuestionDto.class, sql.toString(), actInfoListId);
	}
}
