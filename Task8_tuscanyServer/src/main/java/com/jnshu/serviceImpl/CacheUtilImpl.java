package com.jnshu.serviceImpl;

import com.google.gson.Gson;
import com.jnshu.service.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by hfismyangel@163.com on 2017/7/11.
 */
@Component
public class CacheUtilImpl implements CacheUtil {


    @Autowired
    private StringRedisTemplate redisTemplate;//redis操作模板

    public void put(String key, String value) {
        if (key == null || "".equals(key)) {
            return;
        }
        redisTemplate.opsForHash().put(key, key, value);
    }


    public void put(String key, Object value) {
        if (key == null || "".equals(key)) {
            return;
        }
        redisTemplate.opsForHash().put(key, key, new Gson().toJson(value));

    }


    public <T> T get(String key, Class<T> className) {
        Object obj = redisTemplate.opsForHash().get(key, key);
        if (obj == null) {
            return null;
        }
        return new Gson().fromJson("" + obj, className);
    }


    public String get(String key) {
        if (redisTemplate == null) {
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
        Object obj = redisTemplate.opsForHash().get(key, key);
        if (obj == null) {
            return null;
        } else {
            return String.valueOf(obj);
        }
    }
}
