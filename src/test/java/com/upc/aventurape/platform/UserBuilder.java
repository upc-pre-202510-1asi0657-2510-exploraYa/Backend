package com.upc.aventurape.platform;

import com.upc.aventurape.platform.iam.domain.model.aggregates.User;

public class UserBuilder {
    private String username;
    private String password;
    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }
    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
    public User build() {
        return new User(username, password);
    }
} 