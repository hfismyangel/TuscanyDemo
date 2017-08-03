package com.jnshu.service;

/**
 * Created by hfismyangel@163.com on 2017/7/11.
 */
public interface CacheUtil {
    public void put(String key, String value);
    public void put(String key, Object value);
    public <T> T get(String key, Class<T> className);
    public String get(String key);
}
