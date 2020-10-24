package com.apihomepage.models;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Component
public class User {


    private String id;
    private String name;
    private int age;
    
    public User(){}

    public User(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
