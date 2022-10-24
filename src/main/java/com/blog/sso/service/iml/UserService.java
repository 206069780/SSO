package com.blog.sso.service.iml;

import com.blog.sso.dao.UserDao;
import com.blog.sso.entity.ResponseEntity;
import com.blog.sso.entity.User;
import com.blog.sso.exception.UserNameAlreadyExistException;
import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService extends BaseServiceIml<User, String> {

    @Resource
    UserDao userDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean insert(User user, String daoName) {
        if (hasAccount(user.getAccount())) {
            log.warn("{} is exists", user.getAccount());
            throw new UserNameAlreadyExistException(Map.of("account", user.getAccount()));
        }
        return userDao.insert(user) > 0;

    }

    /**
     * 账号不能重复
     *
     * @param account 账号
     * @return 账号查询结果
     */
    public boolean hasAccount(String account) {
        if (StringUtils.isNullOrEmpty(account)) {
            log.warn("{} is exists", account);
            return false;
        }
        return userDao.hasAccount(account) > 0;
    }

    public User queryByAccount(String iAccount) {
        if (isNullOrEmpty(iAccount)) {
            log.error("{} is not exists", iAccount);
            return null;
        }
        return userDao.queryByAccount(iAccount).get();
    }

    public User queryByUserUUID(String UUID) {
        if (com.baomidou.mybatisplus.core.toolkit.StringUtils.isBlank(UUID)) {
            return null;
        }
        return userDao.queryByUserUUID(UUID).get();
    }

    boolean isNullOrEmpty(String str) {
        return StringUtils.isNullOrEmpty(str);
    }


    public boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword, password);
    }
}
