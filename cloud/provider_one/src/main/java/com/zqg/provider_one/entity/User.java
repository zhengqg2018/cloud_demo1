package com.zqg.provider_one.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * @author: zqg
 * @create: 2020/12/17
 **/
@Data
@Document(collection = "user")
public class User {

    private String id;

    private String name;

    private Integer age;

    private String address;

    private List<Degree> degrees;

    private Map<String, String> map;

}