package com.cqliving.manager.security.shiro.credential;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Hash;

/**
 * Created by Administrator on 2015/5/21.
 *
 * 兼容 security1 的加密
 */
public class B64HashedCredentialsMatcher extends HashedCredentialsMatcher {

    public B64HashedCredentialsMatcher() {
        //security1 默认的加密方案 1024次 sha1 hash
        setHashAlgorithmName("SHA-1");
        setHashIterations(1024);
    }


    protected Hash hashProvidedCredentials(Object credentials, Object salt, int hashIterations) {
        /* //security1 在登陆页面提交密码时进行了base64编码所以在加密时需要对密码进行base64解码
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        byte[] bs = null;
        char[]ss = null;
        try {
            bs = decoder.decodeBuffer(new String((char[])credentials));
            ss = new String(bs,"UTF-8").toCharArray();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return super.hashProvidedCredentials(credentials, salt!=null ? Hex.decode(salt.toString()) : null, hashIterations);
    }

    @Override
    public void setHashAlgorithmName(String hashAlgorithmName) {
        super.setHashAlgorithmName(hashAlgorithmName);
    }

    @Override
    public void setHashIterations(int hashIterations) {
        super.setHashIterations(hashIterations);
    }
}
