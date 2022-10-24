package com.blog.sso.com.blog.sso;


import com.blog.sso.dao.UserAndRoleDao;
import com.blog.sso.entity.Permission;
import com.blog.sso.entity.UserAndRole;
import com.blog.sso.service.iml.BaseServiceIml;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@SpringBootTest
public class UserAndRoleTest {
    @Autowired
    BaseServiceIml<UserAndRole, String> userAndRole;

    @Test
    public void insert() {
        boolean res = userAndRole.insert(new UserAndRole().setUUID("1").setRoleId("1").setUserId("1"), "userAndRoleDao");
        System.out.println(res);
    }

    @Test
    public void queryById() {
//        System.out.println(userAndRole.queryById("1"));
    }

    @Test
    public void update() {

//        userAndRole.edit((UserAndRole) new UserAndRole().setUUID("1").setUpdateUser("admin").setCreateUser("admin").setUpdateTime(new Date()));
    }

    @Test
    public void queryByName() {
//        System.out.println(userAndRole.queryByName("test1"));
    }

    @Test
    public void deleteById() {
//        System.out.println(userAndRole.deleteById("1"));
    }

    @Test
    public void deleteBy() {
//        System.out.println(userAndRole.delete(new Permission().setUUID("1")));
    }
}
