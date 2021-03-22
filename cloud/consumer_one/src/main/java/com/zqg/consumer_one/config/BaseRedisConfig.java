package com.zqg.consumer_one.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;

/**
 * @author: zqg
 * @create: 2020/10/28
 **/
@Configuration
public class BaseRedisConfig {

    @Bean("rateLimitExist")
    public DefaultRedisScript getExistRedisScript(){
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("rateLimitExist.lua"));
        redisScript.setResultType(Long.class);
        return redisScript;
    }

    @Bean("rateLimitInit")
    public DefaultRedisScript getInitRedisScript(){
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("rateLimitInit.lua"));
        redisScript.setResultType(Long.class);
        return redisScript;
    }

    @Bean("rateLimit")
    public DefaultRedisScript getRedisScript(){
        DefaultRedisScript redisScript = new DefaultRedisScript();
        redisScript.setLocation(new ClassPathResource("rateLimit.lua"));
        redisScript.setResultType(Long.class);
        return redisScript;
    }

    @Bean
    @Primary
    public GenericObjectPoolConfig objectPoolConfig(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        return poolConfig;
    }


}