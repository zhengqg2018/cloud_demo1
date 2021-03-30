package com.zqg.consumer_one;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author zqg
 * @date 2020/7/9 17:22
 */
//@EnableApolloConfig
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerOneApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOneApplication.class, args);
    }

    @Bean
    public IRule loadBalancer(){
        return new AvailabilityFilteringRule();
    }
}
