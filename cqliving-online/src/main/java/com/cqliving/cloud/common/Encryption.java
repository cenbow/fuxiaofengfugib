package com.cqliving.cloud.common;

import com.cqliving.cloud.online.security.domain.SysUser;
import com.cqliving.framework.common.exception.BusinessException;
import com.cqliving.manager.security.support.Cryptos;
import java.util.regex.Pattern;

/**
 * 密码处理类
 * 
 * @author huxiaoping
 *
 */
public class Encryption {
    
    private static final int SALT_SIZE = 8;
    private static final int HASH_INTERATIONS = 1024;
    
    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     * 
     * @param user
     */
    public static void entryptPassword(SysUser user) {
        user.setSalt(Cryptos.generatorSalt(SALT_SIZE));
        user.setPassword(Cryptos.sha1(user.getPassword(), user.getSalt(), HASH_INTERATIONS));
    }
    
    /**
     * 用户名校验
     * @Description 
     * @Company 
     * @parameter 待验证的字符串
     * @return 合法返回 <b>true </b>,不合法返回为 <b>false </b>
     * @author huxiaoping 2017年1月11日上午9:39:46
     */
    public static Boolean validateUserName(String userName){
        String regex = "^[0-9a-zA-Z]{6,}$";
        return Pattern.matches(regex, userName); 
    }
    
    /**
     * 验证加密后的密码是否相同
     * @param user
     * @param plaintPassword
     * @return
     * @throws BusinessException
     */
    public static boolean validatePassword(SysUser user, String plaintPassword)
            throws BusinessException {
        return entryptPassword(plaintPassword, user.getSalt()).equals(user.getPassword());
    }

    private static String entryptPassword(String plainPassword, String salt) {
        return Cryptos.sha1(plainPassword, salt, HASH_INTERATIONS);
    }
}
