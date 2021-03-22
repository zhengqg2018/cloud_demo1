package com.zqg.consumer_one.config;

import java.lang.annotation.*;

/**
 *
 * @author zqg
 * @date 2020/10/26 19:48
 */
@Inherited
@Documented
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    //当前可用令牌
    int currPermits() default 1;

    //最大令牌
    int maxBurst() default 3;

    //每秒生产几个令牌
    int rate() default 3;
}
