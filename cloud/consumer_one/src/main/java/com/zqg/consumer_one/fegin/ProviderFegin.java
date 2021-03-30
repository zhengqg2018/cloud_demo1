package com.zqg.consumer_one.fegin;

import com.zqg.consumer_one.fegin.factory.ProviderFeginFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "provider",path ="/test" ,fallbackFactory = ProviderFeginFactory.class)
public interface ProviderFegin {
    @LoadBalanced
    @GetMapping("/{str}")
    String test(@PathVariable("str") String string);
}
