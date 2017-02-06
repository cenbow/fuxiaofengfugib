/**
 * Copyright (c) 2016 CQLIVING, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * CQLIVING, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with CQLIVING.
 */
package com.cqliving.qiniu.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

/**
 * Title:
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author YUWU on 2016年4月16日
 */
public class QiNiuToken {
    public static final Log logger = LogFactory.getLog(QiNiuToken.class);

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken(String bucketname) {
        // 密钥配置
        Auth auth = getAuth();
        return auth.uploadToken(bucketname);
    }
    
    /**
     * Title:覆盖上传,已经存在key，将之前的覆盖
     * @author yuwu on 2016年4月16日
     * @param bucketname
     * @param key
     * @return
     */
    public static String getUpToken(String bucketname,String key) {
        // 密钥配置
        Auth auth = getAuth();
        return auth.uploadToken(bucketname, key);
    }

    /**
     * Title:覆盖上传,已经存在key，再上传则报错
     * @author yuwu on 2016年4月16日
     * @param bucketname
     * @param key
     * @param insertOnly {true:文件已经存在，再上传则报错，false:文件已经存在，再上传则覆盖}
     * @return
     */
    public static String getUpToken(String bucketname,String key,boolean insertOnly) {
        // <bucket>:<key>，表示只允许用户上传指定key的文件。在这种格式下文件默认允许“修改”，已存在同名资源则会被本次覆盖。
        // 如果希望只能上传指定key的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1。
        // 第三个参数是token的过期时间
        // 密钥配置
        Auth auth = getAuth();
        StringMap map = null;
        if(insertOnly){
            map = new StringMap();
            map.put("insertOnly", 1);
        }
        return auth.uploadToken(bucketname, key, 3600, map);
    }
    
    /**
     * Title:上传时指定策略
     * @author yuwu on 2016年4月16日
     * @param bucketname
     * @param key
     * @param map 策略map
     * @return
     */
    public static String getUpToken(String bucketname,String key,StringMap map) {
        Auth auth = getAuth();
        return auth.uploadToken(bucketname, key, 3600, map);
    }
    
    /**
     * Title:
     * <p>Description:</p>
     * @author fuxiaofeng on 2016年5月24日
     * @param bucketname
     * @param map
     * @return
     */
    public static String getUpToken(String bucketname,StringMap map) {
        Auth auth = getAuth();
        
        return auth.uploadToken(bucketname,null, 3600, map);
    }
    
    public static Auth getAuth() {
        // 密钥配置
        Auth auth = Auth.create(QiNiuConfig.ACCESS_KEY, QiNiuConfig.SECRET_KEY);
        return auth;
    }
}