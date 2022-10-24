package com.blog.sso.dao;

import com.blog.sso.entity.Permission;
import com.blog.sso.entity.Role;
import com.blog.sso.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao extends BaseProperties<Role, String> {

}
