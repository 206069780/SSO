package com.senrui.sso.service.iml;

import com.senrui.core.util.JwtTokenUtils;
import com.senrui.sso.entity.JwtUser;
import com.senrui.sso.entity.User;
import com.senrui.sso.entity.dto.LoginRequest;
import com.senrui.sso.exception.ErrorCode;
import com.senrui.sso.exception.RedisConnectException;
import com.senrui.sso.utils.CurrentUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private CurrentUserUtils currentUserUtils;

    private static final String TOKEN_PREFIX = "Bearer ";

    public String createToken(LoginRequest loginRequest) {
        User user = userService.queryByAccount(loginRequest.getAccount());
        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("The user name or pas`sword is not correct.");
        }
        JwtUser jwtUser = new JwtUser(userService.queryByUserUUID(user.getUUID()));
        if (!jwtUser.isEnabled()) {
            throw new BadCredentialsException("User is forbidden to login");
        }
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtTokenUtils.createToken(user.getAccount(), user.getUUID(), authorities, true);
        try {
            stringRedisTemplate.opsForValue().set(user.getUUID(), TOKEN_PREFIX + token);
        } catch (Exception e) {
            throw new RedisConnectException(ErrorCode.REDIS_CONNECTION_ERROR, Map.of("redis server ", 000));
        }
        SecurityContextHolder.getContext().setAuthentication(JwtTokenUtils.getAuthentication(token));
        return token;
    }

    public void removeToken() {
        stringRedisTemplate.delete(currentUserUtils.getCurrentUser().getUUID());
    }
}