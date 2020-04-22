package com.hs.utils;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 生成随机id
     */
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

    /**
     * 生成随机码
     */
    public static String getToken() {
        return getId();
    }

    public static void main(String[] args) {
        System.out.println(getId());
    }
}
