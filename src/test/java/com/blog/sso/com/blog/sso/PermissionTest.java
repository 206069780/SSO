package com.blog.sso.com.blog.sso;


import com.blog.sso.dao.BaseProperties;
import com.blog.sso.dao.PermissionDao;
import com.blog.sso.entity.Permission;
import com.blog.sso.service.BaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@SpringBootTest
public class PermissionTest {

    @Autowired
    BaseService<Permission,String> map;

    @Autowired
    BaseService<Permission, String> baseService;

    @Resource
    PermissionDao permissionDao;

    @Test
    public void insert() {
        System.out.println(permissionDao.insert(new Permission().setPermissionName("test").setActions("view").setUUID("1").setDescriptions("测试")));
    }

    @Test
    public void queryById() {
        System.out.println(map.queryById("1","userDao"));
    }

    @Test
    public void update() {

        permissionDao.edit((Permission) new Permission().setUUID("1").setPermissionName("test1").setDescriptions("hello word").setActions("delete").setUpdateUser("admin").setCreateUser("admin").setUpdateTime(new Date()));
    }

    @Test
    public void queryByName() {
        System.out.println(permissionDao.queryByName("test1"));
    }

    @Test
    public void deleteById() {
        System.out.println(permissionDao.deleteById("1"));
    }

    @Test
    public void deleteBy() {
        System.out.println(permissionDao.delete(new Permission().setUUID("1")));
    }
}
