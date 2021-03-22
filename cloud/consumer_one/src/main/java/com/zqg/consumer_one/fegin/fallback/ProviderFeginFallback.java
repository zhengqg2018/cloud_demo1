package com.zqg.consumer_one.fegin.fallback;

import com.zqg.consumer_one.fegin.ProviderFegin;
import org.springframework.stereotype.Component;

/**
 * @author: zqg
 * @create: 2020/7/13
 **/
@Component
public class ProviderFeginFallback implements ProviderFegin {

    private Throwable throwable;

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public String test(String string) {
        return throwable.getMessage();
    }
}