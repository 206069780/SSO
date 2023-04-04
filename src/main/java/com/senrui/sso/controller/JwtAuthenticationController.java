package com.senrui.sso.controller;


import com.senrui.sso.entity.dto.LoginRequest;
import com.senrui.sso.entity.result.Result;
import com.senrui.sso.service.iml.AuthService;
import com.senrui.sso.service.iml.SSOUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class JwtAuthenticationController {
    @Autowired
    private AuthService authService;

    @Autowired
    private SSOUserDetailsService userDetailsService;


    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest user) {
        if (user.getAccount().equals("") || user.getPassword().equals("")) {
            return Result.error("账号或密码不能为空");
        }
        String token = authService.createToken(user);
        return Result.ok(Map.of("token", token));
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