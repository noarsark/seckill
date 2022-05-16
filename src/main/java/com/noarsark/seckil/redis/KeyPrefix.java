package com.noarsark.seckil.redis;

/**
 * 接口 -> 抽象类 -> 实现类
 */
public interface KeyPrefix {

    public int expireSeconds();

    public String getPrefix();

}
