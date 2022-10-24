package com.blog.sso.com.blog.sso;


import com.blog.sso.dao.UserDao;
import com.blog.sso.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserTest {
    @Resource
    UserDao userDao;

    @Test
    public void insert() {
        System.out.println(userDao.insert(new User().setAccount("fudaopin@fsafsdfsd").setUsername("fudaopin").setUUID("03812903")));
    }

    @Test
    public void queryById() {
        System.out.println(userDao.queryById("03812903"));
    }

    @Test
    public void update() {

        userDao.edit(new User().setUUID("03812903").setQq("fdsafhsdjfhsud"));
    }

    @Test
    public void queryByName() {
        System.out.println();
    }

    @Test
    public void deleteById() {
        System.out.println();
    }

    @Test
    public void deleteBy() {
        System.out.println();
    }
}
