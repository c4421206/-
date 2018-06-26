package com.cloudd.commons.auth.session;

import java.io.Serializable;

/**
 * session 接口
 * @author zhongming
 * @since 3.0
 * 2018/5/24下午1:58
 */
public interface Session {

    Serializable getId();

    Object getAttribute(Object key);

    void setAttribute(Object key, Object value);

    Object removeAttribute(Object key);

    Long getTimeout();

    void setTimeout(Long timeout);
}
