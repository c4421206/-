package com.cloudd.commons.auth.config;

import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户认证相关信息.
 */
public class UserAuthConfig {

    //用户公钥路径
    @Value("${auth.user.pub-key.path}")
    private String pubKeyPath;

    @Value("${auth.user.tokenExpire:3700}")
    private Long tokenExpire;

    //移动端token有效期 默认7天
    @Value("${auth.user.mobileTokenExpire:604800}")
    private Long mobileTokenExpire;

    //token在请求头中的key
    @Value("${auth.user.token-header}")
    private String tokenHeader;



    public String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
    }

    public String getPubKeyPath() {
        return pubKeyPath;
    }

    public void setPubKeyPath(String pubKeyPath) {
        this.pubKeyPath = pubKeyPath;
    }

    public String getToken(HttpServletRequest request){
        return request.getHeader(this.getTokenHeader());
    }


    public Long getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Long tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public Long getMobileTokenExpire() {
        return mobileTokenExpire;
    }

    public void setMobileTokenExpire(Long mobileTokenExpire) {
        this.mobileTokenExpire = mobileTokenExpire;
    }
}
