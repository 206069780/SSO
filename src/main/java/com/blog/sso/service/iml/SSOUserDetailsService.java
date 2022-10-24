package com.blog.sso.service.iml;

import com.blog.sso.entity.ResponseEntity;
import com.blog.sso.entity.Role;
import com.blog.sso.entity.User;
import com.blog.sso.entity.UserAndRole;
import com.blog.sso.entity.dto.LoginRequest;
import com.blog.sso.exception.ErrorCode;
import com.blog.sso.exception.GetUUIDException;
import com.blog.sso.exception.ParameterException;
import com.blog.sso.utils.ApplicationContextHelper;
import com.blog.sso.utils.HttpUtil;
import com.mysql.cj.util.StringUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class SSOUserDetailsService {

    @Value("${id.url}")
    private String idUrl;
    @Autowired
    HttpUtil httpUtil;
    private UserService userService;

    @Autowired
    private BaseServiceIml<UserAndRole, String> userAndRoleService;

    @Autowired
    private BaseServiceIml<Role, String> roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private ApplicationContextHelper applicationContextHelper;

    @Autowired
    public void setApplicationContext(ApplicationContextHelper applicationContextHelper) {
        this.applicationContextHelper = applicationContextHelper;
    }

    @Autowired
    public void setBaseService(UserService userService) {
        this.userService = userService;
    }


    public UserDetails loadUserByUsername(String iAccount) throws UsernameNotFoundException {

        User user = this.userService.queryByAccount(iAccount);
        if (user != null) {
            user = userService.queryByUserUUID(user.getUUID());
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoleList().forEach(r -> {
                authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getAccount(), user.getPassword(), authorities);

        } else {
            throw new UsernameNotFoundException("User " + iAccount + "not found  ");
        }
    }

    @Transactional
    public boolean register(LoginRequest loginUser) throws GetUUIDException {
        if (StringUtils.isNullOrEmpty(loginUser.getAccount()) || StringUtils.isNullOrEmpty(loginUser.getPassword())) {
            log.warn("register fail: account or password don`t null ");
            throw new ParameterException(ErrorCode.PARAMETER_ERROR, Map.of(loginUser.getAccount(), loginUser.getPassword()));
        }
        String userUUID = "";
        String userAndRoleUUID = "";
        try {
            userUUID = String.valueOf(httpUtil.doGet(idUrl.trim()));
            userAndRoleUUID = httpUtil.doGet(idUrl.trim()).toString();
        } catch (IOException e) {
            throw new GetUUIDException(ErrorCode.GET_UUID_ERROR, Map.of());
        }

        PasswordEncoder passwordEncoder = applicationContextHelper.getBean("passwordEncoder");
        User user = new User()
                .setUUID(userUUID)
                .setPassword(passwordEncoder.encode(loginUser.getPassword()))
                .setAccount(loginUser.getAccount());

        Role role = roleService.queryByName("ROLE_USER", "roleDao");
        UserAndRole userAndRole = new UserAndRole()
                .setUUID(userAndRoleUUID)
                .setRoleId(role.getUUID()).setUserId(user.getUUID());

        boolean userInsert = userService.insert(user, "userDao");
        boolean userRoleInsert = userAndRoleService.insert(userAndRole, "userAndRoleDao");
        return (userInsert && userRoleInsert);
    }
}
