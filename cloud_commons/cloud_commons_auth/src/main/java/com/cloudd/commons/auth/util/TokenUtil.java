package com.cloudd.commons.auth.util;

import com.cloudd.commons.auth.config.UserAuthConfig;
import com.cloudd.commons.auth.model.JWTUser;
import com.cloudd.commons.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhongming
 * @since 3.0
 * 2018/5/29下午3:45
 */
@Component
public class TokenUtil {

    @Value("${auth.user.pri-key.path:null}")
    private String priKeyPath;

    @Autowired
    private UserAuthConfig userAuthConfig;

    public String generateUserToken(JWTUser jwtInfo, Long timeout) throws Exception {
        if(timeout == null) {
            timeout = userAuthConfig.getTokenExpire();
        }
        return JWTHelper.generateToken(jwtInfo,priKeyPath,timeout);
    }

    public JWTUser getInfoFromToken(String token) throws Exception {
        return JWTHelper.getInfoFromToken(token,userAuthConfig.getPubKeyPath());
    }

    /**
     * 通过用户信息生成token
     * @param user
     * @return
     * @throws Exception
     */
    public String generateUserToken(User user, Long timeout) throws Exception {
        JWTUser jwtInfo = UserUtil.convertUserToJwt(user);
        return generateUserToken(jwtInfo, timeout);
    }
}
