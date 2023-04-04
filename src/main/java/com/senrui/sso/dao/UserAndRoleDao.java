package com.senrui.sso.dao;

import com.senrui.sso.entity.UserAndRole;

public interface UserAndRoleDao extends BaseProperties<UserAndRole, String> {
    public String queryUserAndRoles(String UserId);
}
