package com.org.weixin.client.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.weixin.client.bean.base.ticket.Ticket;
import com.org.weixin.client.net.LocalHttpClient;

/**
 * JSAPI ticket
 */
public class TicketAPI extends BaseAPI{
	private static Logger logger = LoggerFactory.getLogger(TicketAPI.class);

	/**
	 * 获取 jsapi_ticket
	 * @param access_token
	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx2301a8a2c28d2e0c&secret=6081e93984c39fbb1362bbf9f7201ec4
	 * @return
	 * @throws Exception 
	 */
//	public static Ticket ticketGetticket(String access_token){
//		HttpUriRequest httpUriRequest = RequestBuilder.post()
//				.setUri(BASE_URI + "/cgi-bin/ticket/getticket")
//				.addParameter("access_token",access_token)
//				.addParameter("type", "jsapi")
//				.build();
//		return LocalHttpClient.executeJsonResult(httpUriRequest,Ticket.class);
//	}
	
	public static Ticket ticketGetticket(String access_token) throws Exception {
		String url =BASE_URI+"/cgi-bin/ticket/getticket?access_token="+access_token+"&type=jsapi";
		try{
			HttpUriRequest httpUriRequest = RequestBuilder.get(url).build();
			Ticket ticket = LocalHttpClient.executeJsonResult(httpUriRequest,Ticket.class);
			
			if(ticket!=null &&Ticket.SUCCESS.equals(ticket.getErrcode())){
				logger.info("获取Ticket成功 requestUrl="+url+ " 返回："+ticket);
				return ticket;
			} else {
				logger.error("获取Ticket失败 requestUrl="+url+ " 返回："+ticket);
				throw new Exception("获取Ticket失败 access_token requestUrl="+url+ " 返回："+ ticket);
			}
		} catch (Exception e) {
			logger.error("获取Ticket失败 requestUrl="+url, e);
			throw e;
		}
	}
}
