package com.blog.sso.com.blog.sso;

import com.blog.sso.dao.RoleDao;
import com.blog.sso.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import javax.annotation.Resource;

@SpringBootTest
public class RoleTest {

    @Resource
    RoleDao roleDao;

    @Test
    public void insert() {
        System.out.println(roleDao.insert((Role) new Role().setRoleName("test").setUUID("1").setDescriptions("测试数据").setCreateUser("admin")));
    }

    @Test
    void queryById() {
        System.out.println(roleDao.queryById("1"));
    }

    @Test
    void queryByName() {
        System.out.println(roleDao.queryByName("test"));
    }

    @Test
    void update() {
        System.out.println(roleDao.edit(new Role().setUUID("1").setDescriptions("test001")));
    }

    @Test
    void delete() {
        System.out.println(roleDao.delete(new Role().setUUID("1").setDescriptions("test001")));
    }

    @Test
    void deleteById() {
        System.out.println(roleDao.deleteById("1"));
    }

    @Test
    void queryByUserUUID() {
//        roleDao.queryByUserUUID("779105383817170944").forEach(System.out::println);
    }

}
