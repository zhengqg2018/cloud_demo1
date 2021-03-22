package com.zqg.provider_one.controller;

import com.zqg.provider_one.entity.Degree;
import com.zqg.provider_one.entity.User;
import com.zqg.provider_one.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author: zqg
 * @create: 2020/7/9
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;
    @GetMapping("/{str}")
    public String test(@PathVariable("str") String string){
        System.out.println("request Parmas：" + string);
        User user = new User();
        //user.setId(1L);
        user.setName("lufei");
        user.setAge(20);
        user.setAddress("北京");
        Degree degree1 = new Degree();
        degree1.setSchoolName("清华");
        degree1.setTime(new Date());
        Degree degree2 = new Degree();
        degree2.setSchoolName("北大");
        degree2.setTime(new Date());
        List<Degree> degrees = new ArrayList<>();
        degrees.add(degree1);
        degrees.add(degree2);
        user.setDegrees(degrees);
        Map<String, String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        user.setMap(map);
        userService.saveUser(user);
        return "hello world "+ string;
    }
    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable("id") String id){
        return userService.findUserById(id);
    }

    @PostMapping("/user")
    public void add(@RequestParam("resumeId") String resumeId,@RequestBody User user){
        log.info("resumeId:{},user:{}",resumeId,user);
    }
}