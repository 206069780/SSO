package com.blog.sso.dao;

import com.blog.sso.entity.UserAndRole;

import java.util.List;

public interface UserAndRoleDao extends BaseProperties<UserAndRole, String> {
    public String queryUserAndRoles(String UserId);
}
