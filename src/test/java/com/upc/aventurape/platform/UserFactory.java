package com.upc.aventurape.platform;

import com.upc.aventurape.platform.iam.domain.model.aggregates.User;

public class UserFactory {
    public static User crearUsuario(String username, String password) {
        return new User(username, password);
    }
}

