package com.cqliving.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

/**
 * 
 * @author wanghailong
 * add proxy support for post/get
 */
@SuppressWarnings("deprecation")
public class WebServices {
	
	private final static Log log = LogFactory.getLog(WebServices.class);
	private final static String SO_TIMEOUT = "http.socket.timeout"; 
	
	/**
	 * connectionTimeout->3 seconds
	 * socketTimeout->5 seconds
	 * @param serviceURL
	 * @return
	 */
	public static String getJsonResponse(String serviceURL) {
		return getJsonResponse(serviceURL,1*1000,5*1000);
	}
	
	/**
	 * @param serviceURL
	 * @param socketTimeout - in millisecond, it is the timeout for waiting for data.
	 * @return
	 */
	public static String getJsonResponse(String serviceURL,int connectionTimeout,int socketTimeout) {
		return getJsonResponse(serviceURL,null, connectionTimeout,socketTimeout);
	}
	
	
	/**
	 * @param serviceURL
	 * @param parameters
	 * @param connectionTimeout
	 * @param socketTimeout
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String getJsonResponse(String serviceURL,Map<String,String> parameters,int connectionTimeout,int socketTimeout) throws IllegalArgumentException{
		return getJsonResponse(serviceURL,parameters,connectionTimeout,socketTimeout,null,-1);
	}
	/**
	 * 
	 * @param serviceURL
	 * @param parameters
	 * @param connectionTimeout
	 * @param socketTimeout
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String getJsonResponse(String serviceURL,Map<String,String> parameters,int connectionTimeout,int socketTimeout,String proxyServer,int proxyPort) throws IllegalArgumentException{

		DefaultHttpClient client = null;
		try {
			client = new DefaultHttpClient();
			//----------------------------------------
			setProxy(client,proxyServer,proxyPort);
			//----------------------------------------
			client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);

			List<NameValuePair> nameValuePair = null;
			if (parameters != null){
				nameValuePair = new ArrayList<NameValuePair>();
				for (Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();it.hasNext();){
					Map.Entry<String, String> entry = it.next();
					if (entry.getKey()!=null && entry.getValue()!=null){
						//----------------------
						nameValuePair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
						//----------------------
					}else{
						log.warn(":::[postJsonRequest]parameters: key|value is empty,key->"+entry.getKey()+",value.length->"+ (entry.getValue()==null?0:entry.getValue().length()));
					}
				}
			}
			String fullServiceURL = null;
			if (nameValuePair != null && nameValuePair.size()>0){
				fullServiceURL = serviceURL+"?"+URLEncodedUtils.format(nameValuePair, "UTF-8");
			}else{
				fullServiceURL = serviceURL;
			}
			//-----------------------------
			HttpGet getRequest = new HttpGet(fullServiceURL);
			
			getRequest.setHeader("Accept", "application/json");
			getRequest.setHeader("Content-type", "application/json");
			getRequest.getParams().setIntParameter(SO_TIMEOUT,socketTimeout); 
			HttpResponse response = client.execute(getRequest);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine() == null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				if (entity != null) {
					log.error(toString(entity.getContent()));
				}
				throw new IllegalStateException(":::[getJsonResponse] failed! status line is " + response.getStatusLine());
			}
			InputStream is = entity.getContent();
			return toString(is);
		} catch (Exception ex){
			log.error("::: web service error,serviceURL->"+serviceURL+",msg->"+ex.getMessage());
			//ex.printStackTrace();
			throw new  RuntimeException(ex);
		} finally{
			if (client != null){
				client.getConnectionManager().shutdown();
			}
		}
			
	}
	
	/**
	 * @param serviceURL
	 * @param parameters<String,String> - to check whether you need encode the content of value.
	 * @param connectionTimeout - in millisecond
	 * @param socketTimeout - in millisecond
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String postJsonRequest(String serviceURL,Map<String,String> parameters,int connectionTimeout,int socketTimeout) throws IllegalArgumentException{
		return postJsonRequest(serviceURL,parameters,connectionTimeout,socketTimeout,null,-1);
	}	
	
	/**
	 * @param serviceURL
	 * @param parameters
	 * @param connectionTimeout
	 * @param socketTimeout
	 * @param proxyServer - if null or empty string, do not use proxy setting
	 * @param port
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static String postJsonRequest(String serviceURL,Map<String,String> parameters,int connectionTimeout,int socketTimeout,String proxyServer, int proxyPort) throws IllegalArgumentException{

		DefaultHttpClient client = null;
		try {
			//TODO: this connection timeout does not work?
			//method 1
			//HttpParams params = new BasicHttpParams();
			//params.setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
			//client = new DefaultHttpClient(params);
			
			//method 2
			client = new DefaultHttpClient();
			
			setProxy(client,proxyServer,proxyPort);
			
			client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout);
			//-----------------------------
			
			HttpPost post = new HttpPost(serviceURL);
			List<NameValuePair> nameValuePair = null; 
			if (parameters != null){
				nameValuePair = new ArrayList<NameValuePair>();
				for (Iterator<Map.Entry<String, String>> it = parameters.entrySet().iterator();it.hasNext();){
					Map.Entry<String, String> entry = it.next();
					if (entry.getKey()!=null && entry.getValue()!=null){
						//----------------------
						nameValuePair.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
						//----------------------
					}else{
						log.warn(":::[postJsonRequest]parameters: key|value is empty,key->"+entry.getKey()+",value.length->"+ (entry.getValue()==null?0:entry.getValue().length()));
					}
				}
				UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(nameValuePair,"utf-8");
				post.setEntity(requestEntity);
			}
			//---------------------
			post.getParams().setIntParameter(SO_TIMEOUT,socketTimeout);
			//---------------------
			HttpResponse response = client.execute(post);
			HttpEntity responseEntity = response.getEntity();
			//---------------------------------------------
			//BUG FIX: use closed stream
			//if (responseEntity != null){
			//	responseEntity.consumeContent();  //close the stream
			//}
			//---------------------------------------------
			if (response.getStatusLine() == null || response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				String msg = null;
				if (responseEntity != null) {
					msg = toString(responseEntity.getContent());
					log.error(msg);
				}
				throw new IllegalArgumentException("status line:" + response.getStatusLine()+",response->"+msg);
			}else{
				return toString(responseEntity.getContent());
			}
		} catch (Exception ex){
			log.error("::: [postJsonRequest]web service error,serviceURL->"+serviceURL+",msg->"+ex.getMessage());
			throw new  RuntimeException(ex);
		} finally{
			if (client != null){
				client.getConnectionManager().shutdown();
			}
		}
	
		
	}
	
	/**
	 * added by fisherZhang on 2011.6.29 10:16
	 * @param client
	 * @param proxyServer
	 * @param proxyPort
	 */
	private static void setProxy(DefaultHttpClient client,String proxyServer,int proxyPort){
		if (client == null){
			throw new IllegalArgumentException(":::[setProxy]client is null");
		}
		if (proxyServer != null && !"".equals(proxyServer)){
			HttpHost proxy = new HttpHost(proxyServer, proxyPort);
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
	}
	/**
	 * @param request
	 * @return
	 */
	public static String buildFullContextPath(HttpServletRequest request){
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	}
	
	/**
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	public static String toString(InputStream is) throws IOException {
		if (is != null){
			InputStreamReader reader = new InputStreamReader(is, "utf-8");
			BufferedReader r = new BufferedReader(reader);
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = r.readLine()) != null) {
				sb.append(line);
			}
			r.close();
			return sb.toString();
		}else{
			return null;
		}
	}
}
