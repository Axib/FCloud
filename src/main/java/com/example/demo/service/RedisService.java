package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    @Resource(name = "stringRedisTemplate")
    private HashOperations<String, String, String> hashOps;
    @Resource(name = "stringRedisTemplate")
    private RedisOperations<String, String> redisOps;
    @Resource(name = "stringRedisTemplate")
    private ValueOperations<String, String> valueOps;
    @Resource(name = "stringRedisTemplate")
    private ListOperations<String, String> listOps;

    public RedisService() {
    }

    public String hget(String key, String field, int expire, TimeUnit timeUnit) {
        if (expire > 0) {
            this.redisOps.expire(key, (long)expire, timeUnit);
        }

        return (String)this.hashOps.get(key, field);
    }

    public Map<String, String> hgetAll(String key) {
        return this.hashOps.entries(key);
    }

    public void hset(String key, String field, String val, int expire, TimeUnit timeUnit) {
        this.hashOps.put(key, field, val);
        if (expire > 0) {
            this.redisOps.expire(key, (long)expire, timeUnit);
        }

    }

    public void inc(String key, String field, int num) {
        this.hashOps.increment(key, field, (long)num);
    }

    public void expireAt(String key, Date date) {
        this.redisOps.expireAt(key, date);
    }

    public boolean hasKey(String key) {
        return this.redisOps.hasKey(key);
    }

    public HashOperations<String, String, String> getHashOps() {
        return this.hashOps;
    }

    public RedisOperations<String, String> getRedisOps() {
        return this.redisOps;
    }

    public void setHashOps(HashOperations<String, String, String> hashOps) {
        this.hashOps = hashOps;
    }

    public void setRedisOps(RedisOperations<String, String> redisOps) {
        this.redisOps = redisOps;
    }

    public ValueOperations<String, String> getValueOps() {
        return this.valueOps;
    }

    public void setValueOps(ValueOperations<String, String> valueOps) {
        this.valueOps = valueOps;
    }

    public ListOperations<String, String> getListOps() {
        return this.listOps;
    }

    public void setListOps(ListOperations<String, String> listOps) {
        this.listOps = listOps;
    }
}
