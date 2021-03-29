package com.zqg.consumer_one.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: cloud
 * @description:
 * @author: zqg
 * @create: 2021-03-29 22:31
 **/
@Configuration
public class RibbonConfig {
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}
