package com.example.demo.util;

import com.example.demo.model.FCUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import javax.annotation.Resource;
import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

@Component
public class FCAuthUtil {
    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 验证提交数据连锁代码和redis中记录的登录信息是否一致
     * @param request
     * @return
     * @throws AuthException
     */
    public FCUser getLoginInfo(HttpServletRequest request) throws Exception {
        String access_token = request.getHeader("access_token");
        if (access_token == null || access_token.isEmpty()) {
            access_token = request.getHeader("accessToken");
            if (access_token == null || access_token.isEmpty()) {
                throw new AuthException("token不能为空");
            }
        }
        FCUser user = JSON.parseObject(redisTemplate.opsForValue().get("FCloud:"+access_token), FCUser.class);
        if (user != null) {
            return user;
        }
        else {
            throw new AuthException("登录信息无效");
        }

    }
}
