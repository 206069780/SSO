package com.senrui.sso.filter;

import com.senrui.core.filter.JwtAuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends JwtAuthorizationFilter {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public JwtFilter(AuthenticationManager authenticationManager, StringRedisTemplate stringRedisTemplate) {
        super(authenticationManager, stringRedisTemplate);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);
    }
}
