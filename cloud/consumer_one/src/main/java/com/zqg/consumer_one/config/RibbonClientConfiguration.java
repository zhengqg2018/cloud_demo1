package com.zqg.consumer_one.config;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @program: cloud
 * @description:
 * @author: zqg
 * @create: 2021-03-29 22:21
 **/
@RibbonClient(name = "consumer", configuration = RibbonConfig.class)
public class RibbonClientConfiguration {
}
