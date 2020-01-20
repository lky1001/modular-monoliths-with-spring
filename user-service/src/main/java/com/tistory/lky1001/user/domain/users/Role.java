package com.tistory.lky1001.user.domain.users;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Role {

    @Id
    private int id;

    private String name;

    private String desc;

    private Date created;

    public Role(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
