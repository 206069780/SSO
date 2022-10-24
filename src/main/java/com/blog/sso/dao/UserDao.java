package com.blog.sso.dao;

import com.blog.sso.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseProperties<User, String> {

    public int hasAccount(@Param("account") String account);

    Optional<User> queryByAccount(@Param("account") String account);

    Optional<User> queryByUserUUID(@Param("UUID") String UUID);
}
