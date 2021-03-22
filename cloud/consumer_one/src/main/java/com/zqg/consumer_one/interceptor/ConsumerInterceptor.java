package com.zqg.consumer_one.interceptor;

import com.zqg.consumer_one.config.AccessLimit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/**
 * @author: zqg
 * @create: 2020/9/24
 **/
@Slf4j
@Component
public class ConsumerInterceptor implements HandlerInterceptor {

    private static final String RATE_LIMIT = "rate_limit_";

    @Autowired
    @Qualifier("objectRedisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    @Qualifier("rateLimitExist")
    private RedisScript<Long> rateLimitExist;

    @Autowired
    @Qualifier("rateLimitInit")
    private RedisScript<Long> rateLimitInit;

    @Autowired
    @Qualifier("rateLimit")
    private RedisScript<Long> rateLimit;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if(!method.isAnnotationPresent(AccessLimit.class)){
                return true;
            }
            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
            if(accessLimit == null){
                return true;
            }
            int currPermits = accessLimit.currPermits();
            int maxBurst = accessLimit.maxBurst();
            int rate = accessLimit.rate();

            if(!exist(request)){
                log.info("exist not");
                return init(request,currPermits,maxBurst,rate);
            }
            if(!acq(request)){
                log.info("请求频繁");
             return false;
            }

        }
        return true;
    }

    private boolean acq(HttpServletRequest request) {
        List<String> key =  Collections.singletonList(generateKey(request.getRequestURI()));
        log.info("key.....{}",key.toString());
        Long aLong = redisTemplate.execute(rateLimit, key, 1, currentTime());
        return aLong != null && aLong ==1;
    }

    private boolean init(HttpServletRequest request, int currPermits, int maxBurst, int rate) {
        List<String> key =  Collections.singletonList(generateKey(request.getRequestURI()));
        log.info("key.....{}",key.toString());
        Long aLong = redisTemplate.execute(rateLimitInit, key,currentTime(), currPermits, maxBurst, rate);
        log.info("初始化 URL：{},aLong:{}", request.getRequestURI(),aLong);
        return aLong != null && aLong ==1;

    }

    public boolean exist(HttpServletRequest request){
        List<String> key =  Collections.singletonList(generateKey(request.getRequestURI()));
        log.info("key.....{}",key.toString());
        Long o = redisTemplate.execute(rateLimitExist, key);
        if(o == 1){
            return true;
        }else{
            return false;
        }
    }
    private long currentTime() {
        return redisTemplate.execute(
                (RedisCallback<Long>) redisConnection -> redisConnection.time());
    }
    private String generateKey(String key) {
        return RATE_LIMIT + key;
    }
}