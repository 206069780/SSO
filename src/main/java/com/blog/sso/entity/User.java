package com.blog.sso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private String UUID;
    private String account;
    private String username;
    private String password;
    private String qq;
    private String wx;
    private String tell;
    private int isAccountNonExpired;
    private int isAccountNonLocked;
    private int isCredentialsNonExpired;
    private int isEnabled;
    private List<Role> roleList;
}
