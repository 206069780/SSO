package com.blog.sso.controller;


import com.blog.sso.entity.ResponseEntity;
import com.blog.sso.entity.dto.LoginRequest;
import com.blog.sso.entity.result.Result;
import com.blog.sso.service.iml.AuthService;
import com.blog.sso.service.iml.SSOUserDetailsService;
import com.blog.sso.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController

public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;

    @Autowired
    private SSOUserDetailsService userDetailsService;


    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest user) throws Exception {
        String token = authService.createToken(user);
        return Result.ok(Map.of("token", token));
//        return new ResponseEntity().setData(token).setDescription("登录成功");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestBody LoginRequest user) {
        if (userDetailsService.register(user)) {
            return Result.ok("注册成功");
        }
        return Result.error("注册失败");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Test";
    }
}