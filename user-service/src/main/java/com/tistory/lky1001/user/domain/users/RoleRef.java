package com.tistory.lky1001.user.domain.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_authority")
public class RoleRef {

    private Integer roleId;

    public RoleRef(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }
}
