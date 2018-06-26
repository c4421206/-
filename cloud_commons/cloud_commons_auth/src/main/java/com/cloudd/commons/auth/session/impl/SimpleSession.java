package com.cloudd.commons.auth.session.impl;



import com.cloudd.commons.auth.session.Session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhongming
 * @since 3.0
 * 2018/5/24下午2:03
 */
public class SimpleSession implements Session, Serializable {



    private static final long serialVersionUID = -5899328882011544902L;

    private Serializable id;
    private Long timeout;
    private Map<Object, Object> attributes = new HashMap<Object, Object>();

    public SimpleSession() {

    }

    public SimpleSession(Serializable id) {
        this.id = id;
    }

    @Override
    public Serializable getId() {
        return this.id;
    }

    @Override
    public Object getAttribute(Object key) {
        return attributes.get(key);
    }

    @Override
    public void setAttribute(Object key, Object value) {
        this.attributes.put(key, value);
    }

    @Override
    public Object removeAttribute(Object key) {
        return this.attributes.remove(key);
    }

    @Override
    public Long getTimeout() {
        return timeout;
    }

    @Override
    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }
}
