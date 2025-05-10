package com.upc.aventurape.platform;

public class UserUtils {
    public static boolean esPasswordSegura(String password) {
        return password != null && password.length() >= 8;
    }
} 