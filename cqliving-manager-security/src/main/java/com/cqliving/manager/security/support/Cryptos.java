package com.cqliving.manager.security.support;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;

/**
 * Created by Administrator on 2015/5/20.
 *
 * 密码加密工具，
 * 本类对shiro提供的加密算法进行了简单包装
 * 使用的加密方法需要与 shiro 的 Realm 中配置的 credentialsMatcher 加密算法一致
 *
 */
public class Cryptos {

    /**
     * SHA-512 算法
     * @param pwd 密码
     * @param salt 盐值
     * @param iterations hash次数
     * @return String 加密后的16进制串
     */
    public static String sha512(String pwd, String salt, int iterations){
        return new DefaultHashService().
                computeHash(
                        new HashRequest.Builder().
                                setAlgorithmName("SHA-512").
                                setSource(pwd).
                                setSalt(salt).setIterations(iterations)
                                .build()
                ).toHex();
    }

    /**
     * SHA-512 算法 hash 1次
     * @param pwd 密码
     * @param salt 盐值
     * @return String 加密后的16进制串
     */
    public static String sha512(String pwd, String salt){
        return sha512(pwd, salt, 1);
    }

    /**
     * SHA-512 算法 hash 1次
     * @param pwd 密码
     * @return String 加密后的16进制串
     */
    public static String sha512(String pwd){
        return sha512(pwd, null);
    }




    /**
     * SHA-256 算法
     * @param pwd 密码
     * @param salt 盐值
     * @param iterations hash次数
     * @return String 加密后的16进制串
     */
    public static String sha256(String pwd, String salt, int iterations){
        return new Sha256Hash(pwd, salt, iterations).toHex();
    }

    /**
     * SHA-256 算法 hash 1次
     * @param pwd 密码
     * @param salt 盐值
     * @return String 加密后的16进制串
     */
    public static String sha256(String pwd, String salt){
        return sha256(pwd, salt, 1);
    }

    /**
     * SHA-256 算法 hash 1次
     * @param pwd 密码
     * @return String 加密后的16进制串
     */
    public static String sha256(String pwd){
        return sha256(pwd, null);
    }


    /**
     * MD5 算法
     * @param pwd 密码
     * @param salt 盐值
     * @param iterations hash次数
     * @return String 加密后的16进制串
     */
    public static String md5(String pwd, String salt, int iterations){
        return new Md5Hash(pwd, salt, iterations).toHex();
    }

    /**
     * MD5 算法 hash 1次
     * @param pwd 密码
     * @param salt 盐值
     * @return String 加密后的16进制串
     */
    public static String md5(String pwd, String salt){
        return md5(pwd, salt, 1);
    }

    /**
     * MD5 算法 hash 1次
     * @param pwd 密码
     * @return String 加密后的16进制串
     */
    public static String md5(String pwd){
        return md5(pwd, null);
    }



    /**
     * SHA-1 算法
     * @param pwd 密码
     * @param salt 盐值
     * @param iterations hash次数
     * @return String 加密后的16进制串
     */
    public static String sha1(String pwd, String salt, int iterations){
        return new Sha1Hash(pwd, salt, iterations).toHex();
    }

    /**
     * SHA-1 算法 hash 1次
     * @param pwd 密码
     * @param salt 盐值
     * @return String 加密后的16进制串
     */
    public static String sha1(String pwd, String salt){
        return sha1(pwd, salt, 1);
    }

    /**
     * SHA-1 算法 hash 1次
     * @param pwd 密码
     * @return String 加密后的16进制串
     */
    public static String sha1(String pwd){
        return sha1(pwd, null);
    }


    /**
     * 生成随机盐值
     * @param numBytes 字节数
     * @return String 16进制盐值
     */
    public static String generatorSalt(int numBytes){
        return new SecureRandomNumberGenerator().nextBytes(numBytes).toHex();
    }

    /**
     * 生成8字节的随机盐值
     * @return String 16个字符的16进制盐值
     */
    public static String generatorSalt8Byte(){
        return generatorSalt(8);
    }

    public static void main(String[] args) {
        System.out.println(Cryptos.sha512("admin"));
        System.out.println(Cryptos.sha256("admin"));
        System.out.println(Cryptos.sha1("admin"));
        System.out.println(Cryptos.md5("admin"));
        System.out.println(Cryptos.generatorSalt(8));
        String salt = Cryptos.generatorSalt(8);
        System.out.println(Cryptos.sha1("test", salt, 1024) + " == " + salt);
        System.out.println(Cryptos.md5("admin", "admin"+salt, 2));
    }
}
