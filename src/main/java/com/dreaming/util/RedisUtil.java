package com.dreaming.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author lucky
 * create on 2017/12/9
 */
public class RedisUtil {

    @Autowired
    private static StringRedisTemplate redisTemplate;
}
