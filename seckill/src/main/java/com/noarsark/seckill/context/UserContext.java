package com.noarsark.seckill.context;

import com.noarsark.seckill.domain.User;

/**
 * @description: 用于保存User的ThreadLocal
 * @author: noarsark
 * @email: noarsark817@163.com
 * @date: 2019/5/10 2:31 PM
 * @project: seckill
 */
public class UserContext {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    public static User getUser() {
        return userThreadLocal.get();
    }
}
