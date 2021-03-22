package com.zqg.consumer_one.fegin.factory;

import com.zqg.consumer_one.fegin.ProviderFegin;
import com.zqg.consumer_one.fegin.fallback.ProviderFeginFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author: zqg
 * @create: 2020/7/13
 **/
@Component
public class ProviderFeginFactory implements FallbackFactory<ProviderFegin> {

    public ProviderFegin create(Throwable throwable) {
        ProviderFeginFallback feginFallback = new ProviderFeginFallback();
        feginFallback.setThrowable(throwable);
        return feginFallback;
    }
}