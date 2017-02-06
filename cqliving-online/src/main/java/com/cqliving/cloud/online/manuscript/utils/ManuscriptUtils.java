package com.cqliving.cloud.online.manuscript.utils;

import com.cqliving.cloud.common.CacheConstant;
import com.cqliving.cloud.online.manuscript.domain.ManuscriptColumns;
import com.cqliving.redis.base.AbstractRedisClient;

/**
 * Title:抓稿所需要的一些工具类
 * <p>Description:</p>
 * Copyright (c) CQLIVING 2016
 * @author DeweiLi on 2017年1月9日
 */
public class ManuscriptUtils {
    
    /**
     * Title:验证缓存是否存在
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param abstractRedisClient
     * @param type
     * @param mc
     * @param key
     * @return true:存在,false:不存在
     */
    public static boolean addCache(AbstractRedisClient abstractRedisClient, String type, ManuscriptColumns mc, String key){
        String cacheDomain = getCacheDomain(type, mc);
        if("1".equals(abstractRedisClient.getHSet(cacheDomain, key))){
            return true;
        }
        abstractRedisClient.setHSet(cacheDomain, key, "1");
        return false;
    }
    
    /**
     * Title:验证缓存是否存在
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param abstractRedisClient
     * @param mc
     * @param key
     * @return true:存在,false:不存在
     */
    public static boolean addCache(AbstractRedisClient abstractRedisClient, ManuscriptColumns mc, String key){
        return addCache(abstractRedisClient, null, mc, key);
    }
    
    /**
     * Title:删除缓存
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param abstractRedisClient
     * @param type
     * @param mc
     * @param key
     * @return
     */
    public static Long delHsetCache(AbstractRedisClient abstractRedisClient, String type, ManuscriptColumns mc, String key){
        return abstractRedisClient.delHSet(getCacheDomain(type, mc), key);
    }
    
    /**
     * Title:删除缓存
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param abstractRedisClient
     * @param mc
     * @param key
     * @return
     */
    public static Long delHsetCache(AbstractRedisClient abstractRedisClient, ManuscriptColumns mc, String key){
        return delHsetCache(abstractRedisClient, null, mc, key);
    }
    
    /**
     * Title:删除整个key
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param abstractRedisClient
     * @param appId
     * @param manuscriptColumnsId
     * @return
     */
    public static boolean delCache(AbstractRedisClient abstractRedisClient, String type, ManuscriptColumns mc){
        return abstractRedisClient.del(getCacheDomain(type, mc));
    }
    
    /**
     * Title:删除整个key
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param abstractRedisClient
     * @param mc
     * @return
     */
    public static boolean delCache(AbstractRedisClient abstractRedisClient, ManuscriptColumns mc){
        return delCache(abstractRedisClient, null, mc);
    }
    
    /**
     * Title:获取抓稿专有的缓存key
     * <p>Description:</p>
     * @author DeweiLi on 2017年1月9日
     * @param type 是id还是title 也就是CacheConstant.MANUSCRIPT_ID_HASH 或CacheConstant.MANUSCRIPT_TITLE_HASH
     * @param appId
     * @param manuscriptColumnsId
     * @return
     */
    private static String getCacheDomain(String type, ManuscriptColumns mc){
        if("title".equals(type))
            return CacheConstant.getKey(CacheConstant.MANUSCRIPT_TITLE_HASH, mc.getAppId().toString(), mc.getId().toString());
        else
            return CacheConstant.getKey(CacheConstant.MANUSCRIPT_ID_HASH, mc.getAppId().toString(), mc.getId().toString());
    }
}
