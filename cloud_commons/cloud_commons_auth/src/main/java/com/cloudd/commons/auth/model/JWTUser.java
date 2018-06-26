package com.cloudd.commons.auth.model;

import java.io.Serializable;

/**
 * @author zhongming
 * @since 3.0
 * 2018/5/29上午11:19
 */
public class JWTUser implements Serializable {

    private static final long serialVersionUID = 292603672140890700L;
    private String username;
    private String userId;
    private String name;

    public JWTUser() {

    }

    public JWTUser(String username, String userId, String name) {
        this.username = username;
        this.userId = userId;
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
