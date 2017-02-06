/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.qiniu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author YUWU on 2016年4月16日
 */
public class QiNiuUtil {
    public static final Log logger = LogFactory.getLog(QiNiuUtil.class);

    /**
     * Title:文件上传,会返回一个key值
     * <p>Description:</p>
     * @author yuwu on 2016年4月16日
     * @param filePath 文件路,全路径，包括文件名及后缀
     * @param bucketName 七牛资源名称
     * @return
     */
    public static String upload(String filePath,String bucketName){
        // 创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            // 调用put方法上传
            Response res = uploadManager.put(filePath, null, QiNiuToken.getUpToken(bucketName));
            //body的内容：{"hash":"FgFa8oVqs1DdQnjUnhM0nRRVZiiZ","key":"FgFa8oVqs1DdQnjUnhM0nRRVZiiZ"}
            return parseResponse(res);
        } catch (QiniuException e) {
            catchQiniuException(e);
        }
        return null;
    }
    public static String uploadFile(InputStream inputStream,String bucketName) {    
        UploadManager uploadManager = new UploadManager();
        try {
            String token = QiNiuToken.getUpToken(bucketName);  
            byte[] byteData = IOUtils.toByteArray(inputStream);  
            Response response = uploadManager.put(byteData, null, token);  
            return parseResponse(response);
        } catch (QiniuException e) {
            catchQiniuException(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
    
    /**
     * Title:上传文件，上传时指定一个文件名，如果文件名已经存在，则覆盖上传
     * <p>Description:</p>
     * @author yuwu on 2016年4月16日
     * @param filePath
     * @param bucketName
     * @param key 文件名
     * @return
     */
    public static String upload(String filePath,String bucketName,String key){
        // 创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            // 调用put方法上传
            Response res = uploadManager.put(filePath, key, QiNiuToken.getUpToken(bucketName,key));
            return parseResponse(res);
        } catch (QiniuException e) {
            catchQiniuException(e);
        }
        return null;
    }
    public static String upload(InputStream inputStream,String bucketName,String key) {    
        UploadManager uploadManager = new UploadManager();
        try {
            String token = QiNiuToken.getUpToken(bucketName,key);
            byte[] byteData = IOUtils.toByteArray(inputStream);  
            Response response = uploadManager.put(byteData, key, token);  
            return parseResponse(response);
        } catch (QiniuException e) {
            catchQiniuException(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
    
    /**
     * Title:上传文件，上传时指定一个文件名，如果文件名已经存在，则失败
     * <p>Description:</p>
     * @author yuwu on 2016年4月16日
     * @param filePath
     * @param bucketName
     * @param key 文件名，在七牛服务器指定空间中不能存在，如果存在，则上传失败
     * @return
     */
    public static String upload(String filePath,String bucketName,String key,boolean insertOnly){
        // 创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            // 调用put方法上传
            Response res = uploadManager.put(filePath, key, QiNiuToken.getUpToken(bucketName,key,insertOnly));
            return parseResponse(res);
        } catch (QiniuException e) {
            catchQiniuException(e);
        }
        return null;
    }
    public static String upload(InputStream inputStream,String bucketName,String key,boolean insertOnly) {    
        UploadManager uploadManager = new UploadManager();
        try {
            String token = QiNiuToken.getUpToken(bucketName,key,true);
            byte[] byteData = IOUtils.toByteArray(inputStream);  
            Response response = uploadManager.put(byteData, key, token);  
            return parseResponse(response);
        } catch (QiniuException e) {
            catchQiniuException(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
    
    /**
     * Title:上传文件并回调
     * <p>Description:</p>
     * @author yuwu on 2016年4月16日
     * @param filePath
     * @param bucketName
     * @param key 文件名
     * @param callback 上传文件后的回调地址
     * @return
     */
    public static String uploadAndCallback(String filePath,String bucketName,String key,String callback){
        // 创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            StringMap map = new StringMap();
            map.put("callbackUrl",callback)
            .put("callbackBody", "filename=$(fname)&filesize=$(fsize)");
            String token = QiNiuToken.getUpToken(bucketName,key,map);
            // 调用put方法上传
            Response res = uploadManager.put(filePath, key, token);
            return parseResponse(res);
        } catch (QiniuException e) {
            catchQiniuException(e);
        }
        return null;
    }
    public static String uploadAndCallback(InputStream inputStream,String bucketName,String key,String callback) {    
        UploadManager uploadManager = new UploadManager();
        try {
            StringMap map = new StringMap();
            map.put("callbackUrl",callback)
            .put("callbackBody", "filename=$(fname)&filesize=$(fsize)");
            String token = QiNiuToken.getUpToken(bucketName,key,map);
            byte[] byteData = IOUtils.toByteArray(inputStream);  
            Response response = uploadManager.put(byteData, key, token);  
            return parseResponse(response);
        } catch (QiniuException e) {
            catchQiniuException(e);
        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }
    
    /**
     * Title:获取下载地址
     * @author yuwu on 2016年4月16日
     * @param url
     */
    public static String download(String url) {
        Auth auth = QiNiuToken.getAuth();
        // 调用privateDownloadUrl方法生成下载链接,第二个参数可以设置Token的过期时间
        //7xohpi.com1.z0.glb.clouddn.com?e=1460902803&token=hBuIe2EKsFgiWzqWJZQD5rEoE1oycZDkSM9o-Ouj:V79f0irHv3qxGjYBQHrVXMoFC-c=
        String downloadRUL = auth.privateDownloadUrl(url, 3600);
        return downloadRUL;
    }
    
    /***
     * Title: 文件分块上传
     * @author yuwu on 2016年4月16日
     * @param filePath
     * @param bucketName
     * @return
     * @throws IOException
     */
    /*public static String uploadChuck(String filePath, String bucketName,String key) throws IOException {
        // 设置断点记录文件保存在指定文件夹或的File对象
        String recordPath = "/Users/yuwu/Pictures/桌面背景图/";
        // 实例化recorder对象
        Recorder recorder = new FileRecorder(recordPath);
        // 实例化上传对象，并且传入一个recorder对象
        UploadManager uploadManager = new UploadManager(recorder);
        try {
            //调用put方法上传
            Response res = uploadManager.put(filePath, key, QiNiuToken.getUpToken(bucketName,key));
            return parseResponse(res);
        } catch (QiniuException e) {
            catchQiniuException(e);
        }
        return null;
    }*/
    
    
    /**
     * Title:获取七牛帐号的所有空间
     * <p>Description:</p>
     * @author yuwu on 2016年4月16日
     * @return
     * @throws QiniuException
     */
    public static String[] listBucket() throws QiniuException {  
        Auth auth = QiNiuToken.getAuth();
        BucketManager bucketManager = new BucketManager(auth);  
        return bucketManager.buckets();  
    }
    
    /**
     * Title:获取指定空间下的文件列表 
     * @author yuwu on 2016年4月16日
     * @param bucketName 空间名称 
     * @param prefix 文件名前缀 
     * @param limit 每次迭代的长度限制，最大1000，推荐值 100[即一个批次从七牛拉多少条] 
     * @return List<FileInfo> 
     */
    public static List<FileInfo> listFileOfBucket(String bucketName,String prefix,int limit) {  
        Auth auth = QiNiuToken.getAuth();
        BucketManager bucketManager = new BucketManager(auth);  
        BucketManager.FileListIterator it = bucketManager.createFileListIterator(bucketName, prefix, limit, null);  
        List<FileInfo> list = new ArrayList<FileInfo>();  
        while (it.hasNext()) {  
            FileInfo[] items = it.next();  
            if (null != items && items.length > 0) {  
                list.addAll(Arrays.asList(items));  
            }  
        }  
        return list;  
    }  
    
    /**
     * Title:抓取指定地址的文件，已指定名称保存在指定空间。
     * 要求指定url可访问。
     * 大文件不建议使用此接口抓取。可先下载再上传。
     * @author yuwu on 2016年4月16日
     * @param url 网络地址
     * @param bucketName 空间名称 
     * @param key 空间内文件的key[唯一的]
     * @throws QiniuException
     */
    public static void fetchToBucket(String url,String bucketName,String key) throws QiniuException {  
        Auth auth = QiNiuToken.getAuth();  
        BucketManager bucketManager = new BucketManager(auth);  
        bucketManager.fetch(url, bucketName, key);  
    }
      
    /**
     * Title:对于设置了镜像存储的空间，从镜像源站抓取指定名称的资源并存储到该空间中。
     * 如果该空间中已存在该名称的资源，则会将镜像源站的资源覆盖空间中相同名称的资源
     * @author yuwu on 2016年4月16日
     * @param bucketName 空间名称 
     * @param key 空间内文件的key[唯一的]
     * @throws QiniuException
     */
    public void prefetch(String bucketName, String key) throws QiniuException {
        Auth auth = QiNiuToken.getAuth();  
        BucketManager bucketManager = new BucketManager(auth);  
        bucketManager.prefetch(bucketName, key);  
    }
    
    /**
     * Title:获取指定空间下的某个文件
     * @author yuwu on 2016年4月16日
     * @param bucketName 空间名称
     * @param key 空间内文件的key[唯一的]
     * @return FileInfo
     * @throws QiniuException
     */
    public static FileInfo findOneFile(String bucketName,String key) throws QiniuException {  
        Auth auth = QiNiuToken.getAuth();  
        BucketManager bucketManager = new BucketManager(auth);  
        FileInfo info = bucketManager.stat(bucketName, key);
        return info;  
    }  
    
    /**
     * Title:七牛空间内文件移动
     * @author yuwu on 2016年4月16日
     * @param bucket 源空间名称
     * @param key 源空间里文件的key(唯一的)
     * @param targetBucket 目标空间
     * @param targetKey 目标空间里文件的key(唯一的)
     * @throws QiniuException
     */
    public static void moveFile(String bucketName, String key, String targetbucketName, String targetKey) throws QiniuException {  
        Auth auth = QiNiuToken.getAuth();  
        BucketManager bucketManager = new BucketManager(auth);  
        bucketManager.move(bucketName, key, targetbucketName, targetKey);  
    }  
    
    /**
     * Title:七牛空间内文件复制 
     * @author yuwu on 2016年4月16日
     * @param bucket 源空间名称
     * @param key 源空间里文件的key(唯一的)
     * @param targetBucket 目标空间
     * @param targetKey 目标空间里文件的key(唯一的)
     * @throws QiniuException
     */
    public static void copyFile(String bucketName, String key, String targetbucketName, String targetKey) throws QiniuException {  
        Auth auth = QiNiuToken.getAuth();  
        BucketManager bucketManager = new BucketManager(auth);  
        bucketManager.copy(bucketName, key, targetbucketName, targetKey);  
    }  
    
    /**
     * Title:七牛空间内文件删除
     * @author yuwu on 2016年4月16日
     * @param bucketName 空间名称
     * @param key 空间内文件的key[唯一的]
     * @throws QiniuException
     */
    public static void deleteFile(String bucketName, String key) throws QiniuException {  
        Auth auth = QiNiuToken.getAuth();  
        BucketManager bucketManager = new BucketManager(auth);  
        bucketManager.delete(bucketName, key);  
    }
      
    /***
     * Title:七牛空间内文件重命名
     * @author yuwu on 2016年4月16日
     * @param bucket 空间名称
     * @param key 原key
     * @param targetKey 修改后的key
     * @throws QiniuException
     */
    public static void renameFile(String bucketName, String key, String targetKey) throws QiniuException {  
        Auth auth = QiNiuToken.getAuth();  
        BucketManager bucketManager = new BucketManager(auth);  
        bucketManager.rename(bucketName, key, targetKey);  
    }  
    
    
    public static void catchQiniuException(QiniuException e){
        Response r = e.response;
        // 请求失败时打印的异常的信息
        logger.error(r.toString());
        try {
            // 响应的文本信息
            logger.error(r.bodyString());
        } catch (QiniuException e1) {
            // ignore
        }
    }
    
    @SuppressWarnings("unchecked")
    public static String parseResponse(Response res) throws QiniuException{
        //body的内容：{"hash":"FgFa8oVqs1DdQnjUnhM0nRRVZiiZ","key":"FgFa8oVqs1DdQnjUnhM0nRRVZiiZ"}
        String body = res.bodyString();
        // 打印返回的信息
        logger.info(body);
        ObjectMapper mapper = new ObjectMapper();  
        Map<String, String> productMap;
        try {
            //转成map
            productMap = mapper.readValue(body,HashMap.class);
            return productMap.get("key");
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
    
    /*public static String uploadAndSaveas(String filePath,String bucketName,String key) {    
        // 创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            //设置转码操作参数
            //String fops = "avthumb/mp4/s/640x360/vb/1.25m";
            String fops = "imageView2/1/w/200/h/200/interlace/1";
            //设置转码的队列
            String pipeline = "test_pipeline";
            //可以对转码后的文件进行使用saveas参数自定义命名，当然也可以不指定文件会默认命名并保存在当前空间。
            //String urlbase64 = UrlSafeBase64.encodeToString("目标Bucket_Name:自定义文件key");
            String urlbase64 = UrlSafeBase64.encodeToString("bucketName:" + key + "640x360");
            String pfops = fops + "|saveas/"+ urlbase64;
            
            StringMap map = new StringMap();
            map.putNotEmpty("persistentOps", pfops)
            .putNotEmpty("persistentPipeline", pipeline);
            
            String token = QiNiuToken.getUpToken(bucketName,key,map);
            
            // 调用put方法上传
            Response res = uploadManager.put(filePath, key, token);
            return parseResponse(res);
        } catch (QiniuException e) {
            catchQiniuException(e);
        }
        return null;
    }*/
}