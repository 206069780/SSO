package com.blog.sso.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


public class JwtUser implements UserDetails {

    private String id;
    private String username;
    private String password;
    private Boolean enabled = true;
    private Collection<SimpleGrantedAuthority> authorities;

    public JwtUser() {
    }

    /**
     * 通过 user 对象创建jwtUser
     */
    public JwtUser(User user) {
        id = user.getUUID();
        username = user.getUsername();
        password = user.getPassword();
        setAuthorities(user.getRoleList());
    }

    private void setAuthorities(List<Role> roleList) {
        authorities = new ArrayList<>();
        roleList.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}