package com.senrui.sso.filter;

import com.senrui.core.filter.JwtAuthorizationFilter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter extends JwtAuthorizationFilter {
    public JwtFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate) {
        super(authenticationManager, stringRedisTemplate);
    }
}
