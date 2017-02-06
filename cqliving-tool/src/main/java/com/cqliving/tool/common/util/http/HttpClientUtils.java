package com.cqliving.tool.common.util.http;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.google.common.collect.Lists;

/**
 * Title:HttpClientUtil工具类
 * <p>Description:
 * 1、类采用HttpClient发送HTTP请求。
 * 2、采用HttpClient4.5.1
 * </p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi<lidewei@cqliving.com> on 2017年1月9日
 */
public class HttpClientUtils {

    private static PoolingHttpClientConnectionManager cm;
    private static RequestConfig config;
    private static HttpRequestRetryHandler retryHandler;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";
    
    /**
     * Title:初始化
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     */
    public static void init(){
        if(cm == null){
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);//将最大连接数增加到50
            cm.setDefaultMaxPerRoute(5);//将每个路由基础的连接增加到20，默认是2
            config = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(10000).setSocketTimeout(20000).setExpectContinueEnabled(true).build();
            
            //请求重试处理
            retryHandler = new HttpRequestRetryHandler(){
                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                    if(executionCount > 5){//如果已经重试了5次，那么就放弃
                        return false;
                    }
                    if(exception instanceof NoHttpResponseException){//如果服务器丢掉了连接，那么重试
                        return true;
                    }
                    if(exception instanceof SSLHandshakeException){//不重试SSL握手异常
                        return false;
                    }
                    if(exception instanceof InterruptedIOException){//超时
                        return false;
                    }
                    if(exception instanceof UnknownHostException){//目标服务器不可达
                        return false;
                    }
                    if(exception instanceof ConnectTimeoutException){//连接被拒绝
                        return false;
                    }
                    if(exception instanceof SSLException){
                        return false;
                    }
                    HttpClientContext clientContext = HttpClientContext.adapt(context);
                    HttpRequest request = clientContext.getRequest();
                    if(!(request instanceof HttpEntityEnclosingRequest)){//如果请求是幂等的，就再次尝试
                        return true;
                    }
                    return false;
                }
            };
        }
    }
    
    /**
     * Title:通过连接池获取HttpClient
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @return
     */
    private static CloseableHttpClient getHttpClient(){
        init();
        return HttpClients.custom().setConnectionManager(cm).setDefaultRequestConfig(config).setRetryHandler(retryHandler).build();
    }
    
    /**
     * Title:处理http请求
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request){
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity, UTF_8);
                return result;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return EMPTY_STR;
    }
    
    
    /**
     * Title:参数转换
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param params
     * @return
     */
    private static List<NameValuePair> covertParams(Map<String, Object> params){
        List<NameValuePair> list = Lists.newArrayList();
        for(Map.Entry<String, Object>  param : params.entrySet()){
            list.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return list;
    }
    
    /**
     * Title: GET 请求
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param url
     * @return
     */
    public static String get(String url){
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }
    
    /**
     * Title: GET请求
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param url
     * @param params
     * @return
     * @throws URISyntaxException
     */
    public static String get(String url, Map<String, Object> params) throws URISyntaxException{
        URIBuilder uri = new URIBuilder();
        uri.setPath(url);
        List<NameValuePair> pairs = covertParams(params);
        uri.setParameters(pairs);
        HttpGet httpGet = new HttpGet(uri.build());
        return getResult(httpGet);
    }
    
    /**
     * Title: GET请求
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws URISyntaxException
     */
    public static String get(String url, Map<String, Object> headers, Map<String, Object> params) throws URISyntaxException{
        URIBuilder uri = new URIBuilder();
        uri.setPath(url);
        List<NameValuePair> pairs = covertParams(params);
        uri.setParameters(pairs);
        HttpGet httpGet = new HttpGet(uri.build());
        for(Map.Entry<String, Object> header : headers.entrySet()){
            httpGet.addHeader(header.getKey(), String.valueOf(header.getValue()));
        }
        return getResult(httpGet);
    }
    
    /**
     * Title: POST请求
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param url
     * @return
     */
    public static String post(String url){
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }
    
    /**
     * Title: POST请求
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param url
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String post(String url, Map<String, Object> params) throws UnsupportedEncodingException{
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> pairs = covertParams(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }
    
    /**
     * Title: POST请求
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String post(String url, Map<String, Object> headers, Map<String, Object> params) throws UnsupportedEncodingException{
        HttpPost httpPost = new HttpPost(url);
        for(Map.Entry<String, Object> header : headers.entrySet()){
            httpPost.addHeader(header.getKey(), String.valueOf(header.getValue()));
        }
        List<NameValuePair> pairs = covertParams(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getResult(httpPost);
    }
}
