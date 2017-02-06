package com.cqliving.cloud.online.order.dao.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.cqliving.cloud.online.order.dao.OrderEvaluateDaoCustom;
import com.cqliving.cloud.online.order.domain.OrderEvaluate;
import com.cqliving.cloud.online.order.dto.OrderEvaluateDto;
import com.cqliving.framework.common.dao.jdbc.v2.MysqlPagedJdbcTemplateV2;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.framework.common.dao.support.ScrollPage;

public class OrderEvaluateDaoImpl implements OrderEvaluateDaoCustom {
    
    @Autowired
    private MysqlPagedJdbcTemplateV2 mysqlPagedJdbcTemplateV2;
    
     /**
     * 分页查询评论信息
     SELECT a.*,b.name shopping_goods_name,c.name user_name ,CASE a.status WHEN -1 THEN 4 WHEN 1 THEN 1 WHEN 3 THEN 3 ELSE 99 END status_new FROM order_evaluate a 
     LEFT JOIN shopping_goods b ON a.goods_id = b.id 
     LEFT JOIN user_info c ON a.user_id = c.id
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:37:43
     */
    @Override
    public PageInfo<OrderEvaluateDto> queryEvaluateForPage(PageInfo<OrderEvaluateDto> pageInfo,
            Map<String, Object> map, Map<String, Boolean> orderMap){
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.*,b.name shopping_goods_name,c.name user_name ");
        sql.append(",CASE a.status WHEN -1 THEN 4 WHEN 1 THEN 1 WHEN 3 THEN 3 ELSE 99 END status_new FROM order_evaluate a ");
        sql.append("LEFT JOIN shopping_goods b ON a.goods_id = b.id ");
        sql.append("LEFT JOIN user_info c ON a.user_id = c.id ");
        mysqlPagedJdbcTemplateV2.queryForPage(OrderEvaluateDto.class, sql.toString(), map, pageInfo, orderMap);
        return pageInfo;
    }
    
    /**
     * 通过Id查询评论信息
     * @Description 
     * @Company 
     * @parameter 
     * @return
     * @author huxiaoping 2016年11月25日上午11:40:42
     */
    @Override
    public OrderEvaluateDto getById(Long id) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT a.*,b.name shopping_goods_name,c.name user_name ,d.NICKNAME aduitor FROM order_evaluate a ");
        sql.append("LEFT JOIN shopping_goods b ON a.goods_id = b.id ");
        sql.append("LEFT JOIN user_info c ON a.user_id = c.id ");
        sql.append("LEFT JOIN sys_user d ON a.aduit_user_id = d.id ");
        sql.append("where a.id=? ");
        return mysqlPagedJdbcTemplateV2.queryForObject(sql.toString(), BeanPropertyRowMapper.newInstance(OrderEvaluateDto.class), id);
    }

	@Override
	public ScrollPage<OrderEvaluateDto> queryForScrollPage(ScrollPage<OrderEvaluateDto> scrollPage, Map<String, Object> conditionMap) {
		//查询数据
		String sql = ""
				+ "SELECT "
				+ "	a.*, "
				+ "	b.img_url AS avatar, "
				+ "	b.name AS user_name "
				+ "FROM "
				+ "	order_evaluate a "
				+ "LEFT JOIN user_info b ON a.user_id = b.id "
				+ "WHERE "
				+ "	a.status = " + OrderEvaluate.STATUS3;
		scrollPage = mysqlPagedJdbcTemplateV2.queryPage(OrderEvaluateDto.class, scrollPage, sql, conditionMap);
		return scrollPage;
	}
	
}