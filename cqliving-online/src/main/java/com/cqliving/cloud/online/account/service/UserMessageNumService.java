package com.cqliving.cloud.online.account.service;

import java.util.Map;

import com.cqliving.cloud.online.account.domain.UserMessageNum;
import com.cqliving.framework.common.dao.support.PageInfo;
import com.cqliving.tool.common.Response;

/**
 * 用户消息通知数量表 Service
 * Date: 2016-05-12 11:23:50
 * @author Code Generator
 */
public interface UserMessageNumService {

	/** @author Code Generator *****start*****/
	public Response<PageInfo<UserMessageNum>> queryForPage(PageInfo<UserMessageNum> pageInfo, Map<String, Object> map,Map<String, Boolean> orderMap);
	public Response<UserMessageNum> get(Long id);
	public Response<Void> delete(Long... id);
	public Response<Void> save(UserMessageNum domain);
	/** @author Code Generator *****end*****/
	
	/**
	 * Title:获得数量
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月12日
	 * @param appId
	 * @param token
	 * @param type
	 * @return
	 */
	Response<Integer> getNum(Long appId, String token, Byte type);
	
	/**
	 * Title:
	 * <p>Description:</p>
	 * @author DeweiLi on 2016年5月12日
	 * @param appId
	 * @param token
	 * @param type
	 * @param num 为null或0的时候表示删除这条数据，也是清零的一种方式，其他情况下在原来的数量上做增加减少
	 * @return
	 */
    Response<Void> modifyNum(Long appId, String token, Byte type, Integer num);
}
