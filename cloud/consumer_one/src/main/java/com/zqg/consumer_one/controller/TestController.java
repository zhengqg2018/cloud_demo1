package com.zqg.consumer_one.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.zqg.consumer_one.config.AccessLimit;
import com.zqg.consumer_one.entity.User;
import com.zqg.consumer_one.fegin.ProviderFegin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zqg
 * @create: 2020/7/9
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    @Qualifier("objectRedisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ProviderFegin providerFegin;


    private static volatile Integer TOTAL = 10;

    //@Value("${datasource.name:aa}")
    private String dataSourceName;

    @Value("${name}")
    private String infoName;

    @GetMapping("/echo/{str}")
    public String test(@PathVariable("str") String string){
        String test = providerFegin.test(string);
        System.out.println(test);
        return test;
    }
    @GetMapping("/getDatabaseInfo")
    public String getDatabaseInfo(){
        System.out.println("............:"+dataSourceName);
        return dataSourceName;
    }
    @AccessLimit
    @GetMapping("/{key}")
    public String getRedisByKey(@PathVariable("key") String key){
        log.info("com.zqg.consumer_one.controller.TestController.getRedisByKey key:{}", key);
        /*User user = new User();
        user.setId(1);
        user.setName("李四");
        user.setAddress("北京");
        redisTemplate.opsForValue().set("zqg",user);
        User value = (User) redisTemplate.opsForValue().get("zqg");
        log.info("{}", value);*/

        return key.toString();
    }
    @GetMapping("/infoName")
    public String getInfoName(){
        log.info(".............");
        return "";
    }

}