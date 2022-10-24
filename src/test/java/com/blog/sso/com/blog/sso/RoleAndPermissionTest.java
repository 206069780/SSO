package com.blog.sso.com.blog.sso;


import com.blog.sso.dao.RoleAndPermissionDao;

import com.blog.sso.entity.RoleAndPermission;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class RoleAndPermissionTest {
    @Resource
    RoleAndPermissionDao roleAndPermission;

    @Test
    public void insert() {
        System.out.println(roleAndPermission.insert(new RoleAndPermission().setUUID("1").setRoleId("1").setRoleId("1")));
    }

    @Test
    public void queryById() {
        System.out.println(roleAndPermission.queryById("1"));
    }

    @Test
    public void update() {

//        roleAndPermission.edit((roleAndPermission) new roleAndPermission().setUUID("1").setUpdateUser("admin").setCreateUser("admin").setUpdateTime(new Date()));
    }

    @Test
    public void queryByName() {
        System.out.println(roleAndPermission.queryByName("test1"));
    }

    @Test
    public void deleteById() {
        System.out.println(roleAndPermission.deleteById("1"));
    }

    @Test
    public void deleteBy() {
//        System.out.println(roleAndPermission.delete(new Permission().setUUID("1")));
    }
}
