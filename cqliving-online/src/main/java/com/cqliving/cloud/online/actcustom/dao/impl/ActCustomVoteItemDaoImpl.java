package com.cqliving.cloud.online.actcustom.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cqliving.cloud.online.actcustom.dao.ActCustomVoteItemDaoCustom;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteDto;
import com.cqliving.cloud.online.actcustom.dto.ActCustomVoteItemDto;
import com.cqliving.cloud.online.interfacc.dto.ActExportDto;
import com.cqliving.cloud.online.shopping.domain.ShoppingUserAddress;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.ScrollPage;
import com.google.common.collect.Maps;

public class ActCustomVoteItemDaoImpl implements ActCustomVoteItemDaoCustom {
	@Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
	@Override
	public List<ActCustomVoteDto> queryActeDetail(String actQrcodeCode) {
		List<ActCustomVoteDto> actExport=new ArrayList<>();
		String sql ="SELECT aq.*, ifnull(uac.uNum, 0) AS userNum,"+
	                " ifnull(uac.tNum, 0) AS voteTatalNum FROM act_qrcode AS aq "+
                    " LEFT JOIN ( SELECT act_qrcode_code, count(DISTINCT user_id) AS uNum, count(user_id) AS tNum "+
	                " FROM user_act_custom_vote GROUP BY act_qrcode_code) AS uac ON uac.act_qrcode_code = aq.`code`"+
                    " WHERE aq. CODE ='"+ actQrcodeCode+"'";
		actExport=mysqlPagedJdbcTemplateV2.queryForList(sql, ActCustomVoteDto.class);
		return actExport;
	}

	@Override
	public List<ActCustomVoteItemDto> queryActeList(String actQrcodeCode,String itemTitle) {
		List<ActCustomVoteItemDto> actExport=new ArrayList<>();
		String sql ="SELECT * from act_custom_vote_item where act_qrcode_code='"+actQrcodeCode+
				"' and status=3 ";

//		if(StringUtils.isNotBlank(itemTitle)){
//			sql+=" and number like '%"+itemTitle+"%' or item_title like '%"+itemTitle+"%'";	
//				}
		sql +=" ORDER BY act_value DESC ,sort_no";
		actExport=mysqlPagedJdbcTemplateV2.queryForList(sql, ActCustomVoteItemDto.class);
		return actExport;
	}

	@Override
	public ScrollPage<ActCustomVoteItemDto> queryAddressPage(ScrollPage<ActCustomVoteItemDto> page, String itemTitle) {
		String sql = "Select * From (Select acvi.*,(@rowNum:=@rowNum+1) as ranking"+
                     " From act_custom_vote_item acvi, (Select (@rowNum :=0)) b where `status`=3 ORDER BY act_value DESC ,sort_no)"+
				     " as a where 1=1 ";
		if(StringUtils.isNotBlank(itemTitle)){
			sql+=" and (number='"+itemTitle+"' or item_title like '%"+itemTitle+"%')";	
		}
		Map<String, Object> conditions = Maps.newHashMap();
		mysqlPagedJdbcTemplateV2.queryPage(ActCustomVoteItemDto.class, page, sql.toString(), conditions);
		return page;
	}

}
