/**
 * Copyright (c) 2009 FEINNO, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * FEINNO, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with FEINNO.
 */
package com.cqliving.framework.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

/**
 * 获取图表数据帮助类
 * Title:
 * <p>Description:</p>
 * Copyright (c) cqliving 2013-2016
 * @author yuwu on 2016年2月15日
 */
public class SqlHelper {
    /**
     * 截取SQL,
     * 如果SQL中有命名参数条件，例如：where c1.`clazz_id` = :clazzId
     * 当参数:clazzId在param中为空时，则需要将条件处理成where 1 = 1
     * @author yuwu on 2016年2月15日
     */
    public static String processSql(MapSqlParameterSource parameters,Map<String,Object> param,String sql){
        String newSql = sql;
        Iterator<String> ite = param.keySet().iterator();
        for(;ite.hasNext();){
            String name = ite.next();
            Object value = param.get(name);
            if(isNullOrBlank(value)){
                newSql = delSqlCondition(newSql,":" + name);
            }else{
                parameters.addValue(name, value);
            }
        }
        return newSql;
    }
    
    public static boolean isNullOrBlank(Object value){
        if(value == null){
            return true;
        }
        if(value instanceof String){
            return StringUtils.isBlank((String)value);
        }else if(value instanceof Object[]){
            Object[] cs = (Object[]) value;
            return cs.length == 0 ? true:false;
        }else if(value instanceof Collection){
            Collection<?> cs = (Collection<?>) value;
            return cs.isEmpty() ? true:false;
        }
        return false;
    }
    
    public static String delSqlCondition(String strSql, String key) {

        String sql = strSql.replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("  ", " ");
        while (sql.indexOf(key) != -1) {
            int index = sql.indexOf(key);
            boolean hasFunction = false;
            int index_blank[] = { sql.length() + 1, sql.length() + 1, sql.length() };
            int index2[] = { -1, -1, -1, -1 };
            String subSql = "";
            if (sql.lastIndexOf("(", index) != -1)
                if (sql.charAt(sql.lastIndexOf("(", index) - 1) != ' ' && sql.charAt(sql.lastIndexOf("(", index) - 1) != '(') {
//                    index2[0] = sql.lastIndexOf(" ", sql.lastIndexOf("(", index));
//                    if (sql.charAt(index2[0] - 1) == '=' || sql.charAt(index2[0] - 1) == '>' || sql.charAt(index2[0] - 1) == '<' || sql.substring(index2[0] - 4, index2[0]).equals("like"))
//                        if (sql.charAt(index2[0] - 2) == '!')
//                            index2[0] = sql.lastIndexOf(" ", index2[0] - 4);
//                        else
//                            index2[0] = sql.lastIndexOf(" ", index2[0] - 3);
                    hasFunction = true;
                } else {
                    index2[0] = sql.lastIndexOf("(", index) + "(".length();
                }
            if (sql.toUpperCase().lastIndexOf(" WHERE ", index) != -1)
                index2[1] = sql.toUpperCase().lastIndexOf(" WHERE ", index) + " WHERE ".length();
            if (sql.toUpperCase().lastIndexOf(" AND ", index) != -1)
                index2[2] = sql.toUpperCase().lastIndexOf(" AND ", index) + " AND ".length();
            if (sql.toUpperCase().lastIndexOf(" OR ", index) != -1)
                index2[3] = sql.toUpperCase().lastIndexOf(" OR ", index) + " OR ".length();
            if (sql.indexOf(" ", index) != -1)
                index_blank[0] = sql.indexOf(" ", index);
            if (sql.indexOf(")", index) != -1)
                if (hasFunction)
                    index_blank[1] = sql.indexOf(")", index) + 1;
                else
                    index_blank[1] = sql.indexOf(")", index);
            Arrays.sort(index_blank);
            Arrays.sort(index2);
            ArrayUtils.reverse(index2);
            subSql = sql.substring(index2[0], index_blank[0]);
            int orIndexFront = sql.toUpperCase().lastIndexOf(" OR ", index2[0]);
            int andIndexFront = sql.toUpperCase().lastIndexOf(" AND ", index2[0]);
            int orIndexBack = sql.toUpperCase().indexOf(" OR ", index_blank[0]);
            if (andIndexFront <= orIndexFront
                    && (orIndexFront != -1 && StringUtils.isBlank(sql.substring(orIndexFront + " or ".length(), index2[0])) || orIndexBack != -1
                            && StringUtils.isBlank(sql.substring(index_blank[0], orIndexBack))))
                sql = replaceAll(sql, subSql, "1<>1");
            else
                sql = replaceAll(sql, subSql, "1=1");
        }
        return sql;
    }
    
    public static String replaceAll(String src, String regex, String replacement) {

        int indexOf = src.indexOf(regex);
        if (indexOf == -1)
            return src;
        else
            return replaceAll(
                    (new StringBuilder(String.valueOf(src.substring(0, indexOf)))).append(replacement)
                            .append(src.substring(indexOf + regex.length())).toString(), regex, replacement);
    }
    
}
