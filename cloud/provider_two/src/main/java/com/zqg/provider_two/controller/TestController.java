package com.zqg.provider_two.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zqg
 * @create: 2020/7/9
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/{str}")
    public String test(@PathVariable("str") String string){
        System.out.println("request Parmas：" + string);
        return "hello world "+ string;
    }
}