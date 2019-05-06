package com.rpc.entity;

import lombok.Data;

@Data
public class InfoUser {
    private String id;
    private String name;
    private Integer age;

    public InfoUser(String id,String name,Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
