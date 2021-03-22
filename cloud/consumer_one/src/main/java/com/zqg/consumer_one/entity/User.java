package com.zqg.consumer_one.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: zqg
 * @create: 2020/10/28
 **/
@Data
public class User implements Serializable {

    private int id;

    private String name;

    private String address;

}