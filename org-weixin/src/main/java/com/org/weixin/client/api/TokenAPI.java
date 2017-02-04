package com.org.weixin.client.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.weixin.client.bean.base.ticket.Ticket;
import com.org.weixin.client.bean.base.token.Token;
import com.org.weixin.client.net.LocalHttpClient;

public class TokenAPI extends BaseAPI{
	
	private static Logger logger = LoggerFactory.getLogger(TokenAPI.class);

//	/**
//	 * 获取access_token
//	 * @param appid
//	 * @param secret
//	 * @return
//	 */
//	public static Token token(String appid,String secret){
//		HttpUriRequest httpUriRequest = RequestBuilder.post()
//				.setUri(BASE_URI + "/cgi-bin/token")
//				.addParameter("grant_type","client_credential")
//				.addParameter("appid", appid)
//				.addParameter("secret", secret)
//				.build();
//		return LocalHttpClient.executeJsonResult(httpUriRequest,Token.class);
//	}
	/**
	 * 获取access_token
	 * @param appid
	 * @param secret
	 * @return
	 * @throws Exception 
	 */
	public static Token token(String appid,String secret) throws Exception{
		String url = BASE_URI+"/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+secret;

		try{
			HttpUriRequest httpUriRequest = RequestBuilder.get(url).build();
			Token token = LocalHttpClient.executeJsonResult(httpUriRequest,Token.class);
			if(token!=null && Token.SUCCESS.equals(token.getErrcode())){
				logger.info("获取Token 成功！ url="+ url +" 返回："+token);
				return token;
			} else {
				logger.info("获取Token 失败 url="+ url +" 返回："+token);
				throw new Exception("获取token失败");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void main(String args[]) throws Exception {

		Token token = TokenAPI.token("wx9a3dd9b95272d740", "bc8f69f470ac3bba5f1754e8c2a23509");
		System.out.println(token);;

		Ticket tikcet = TicketAPI.ticketGetticket(token.getAccess_token());
		System.out.println(tikcet);;
	}

}
