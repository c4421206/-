package com.cloudd.commons.auth.interceptor;

import com.cloudd.commons.auth.annotation.RequiresPermissions;
import com.cloudd.commons.auth.config.ServiceAuthConfig;
import com.cloudd.commons.auth.model.JWTUser;
import com.cloudd.commons.auth.session.Session;
import com.cloudd.commons.auth.util.JWTHelper;
import com.cloudd.commons.auth.util.Logical;
import com.cloudd.commons.auth.util.SessionUtil;
import com.clouddo.commons.common.constatns.CommonConstants;
import com.clouddo.commons.common.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 服务端鉴权
 * @author zhongming
 * @since 3.0
 * 2018/5/24上午11:23
 */
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {

    private static  Logger logger = LoggerFactory.getLogger(ServiceAuthRestInterceptor.class);

    //服务端认证信息
    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    //redis服务
    @Autowired
    private RedisService redisService;

    /**
     * 请求前认证当前用户是否有权限
     * 整体思路
     * 1、通过token从redis中获取session，并存储到sessionUtil，方便之后使用
     * 2、认证token解密信息与session存储的信息是否一致，不一致则token无效
     * 3、获取访问方法上标识权限的注解RequiresPermissions，如果没有注解，则无需权限
     * 4、获取需要的权限，并获取权限认证策略 AND OR
     * 5、从session中获取服务端拥有的权限，并认证
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            //获取方法上的注解RequiresPermissions，获取所需权限
            RequiresPermissions requiresPermissions = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
            //如果没有注解，则不拦截
            if(requiresPermissions == null) {
                return super.preHandle(request, response, handler);
            }

            //获取token，并解析
            String token = request.getHeader(serviceAuthConfig.getTokenHeader());
            JWTUser jwtInfo = null;
            try {
                jwtInfo = JWTHelper.getInfoFromToken(token, serviceAuthConfig.getPubKeyPath());
            } catch (Exception e) {
                e.printStackTrace();
                this.authErrorMessage("token解析失败", response);
                logger.debug("token解析失败，token:{}",token);
            }
            //获取session
            Session session = (Session) redisService.get(token);
            if(session == null) {
                this.authErrorMessage("token认证无效", response);
                logger.debug("token认证无效，未找到session，token:{}",token);
                return false;
            }
            SessionUtil.setServerSession(session);
            //token解密信息与session信息是否一致
            if(!authTokenSession(jwtInfo)) {
                this.authErrorMessage("token认证无效", response);
                logger.debug("token认证无效，session中存储的token信息与token解析信息不一致，token:{}",token);
                return false;
            }

            //获取权限集合
            String[] permissions = requiresPermissions.value();
            if(permissions.length == 0) {
                return super.preHandle(request, response, handler);
            }
            //获取权限是全部拥有才可访问还是满足其中一条就可访问
            Logical logical = requiresPermissions.logical();
            boolean needAll = true; //标识是否需要所有权限
            if(logical.name().equals("OR")) {
                needAll = false;
            }

            if(needAll) {
                if(this.hasAllPermissions(permissions)) {
                    return super.preHandle(request, response, handler);
                }
            } else {
                if(this.hasOnePermission(permissions)) {
                    return super.preHandle(request, response, handler);
                }
            }
            logger.debug("token认证无效，token:{}",token);
            return false;
        }
        return super.preHandle(request, response, handler);

    }

    /**
     * 请求完成后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        if(!StringUtils.isEmpty(request.getHeader(serviceAuthConfig.getTokenHeader()))) {
//            //存储session信息
//            this.redisService.put(SessionUtil.getServiceSession().getId().toString(), SessionUtil.getServiceSession(), serviceAuthConfig.getTokenExpire());
//        }
        if(SessionUtil.getServiceSession() != null) {
            this.redisService.put(SessionUtil.getServiceSession().getId().toString(), SessionUtil.getServiceSession(), serviceAuthConfig.getTokenExpire());
        }
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 是否含有其中一项权限
     * @param permissions
     * @return
     */
    private boolean hasOnePermission(String[] permissions) {
        //从session中获取用户拥有的所有权限
        List<String> userPermissions = (List<String>) SessionUtil.getServiceSession().getAttribute(CommonConstants.USER_PERMISSIONS);
        if(userPermissions != null) {
            for(String permission : permissions) {
                if(userPermissions.contains(permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否含有所有权限
     * @return
     */
    private boolean hasAllPermissions(String[] permissions) {
        //从session中获取用户拥有的所有权限
        List<String> userPermissions = (List<String>) SessionUtil.getServiceSession().getAttribute(CommonConstants.USER_PERMISSIONS);
        if(userPermissions != null) {
            for(String permission : permissions) {
                if(!userPermissions.contains(permission)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private void authErrorMessage(String message, HttpServletResponse response) throws IOException {
        response.sendError(401, message);
    }

    //认证token解密信息与session存储信息是否一致
    private boolean authTokenSession(JWTUser jwtInfo) {
        String username = jwtInfo.getUsername();
        JWTUser jwtInfoSession = (JWTUser) SessionUtil.getServiceSession().getAttribute(CommonConstants.CONTEXT_KEY_JWTUSER);
        return username.equals(jwtInfoSession == null ? "" : jwtInfoSession.getUsername());
    }
}
