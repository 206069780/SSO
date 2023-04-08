package com.senrui.sso.config;


import com.senrui.core.aop.annotationntool.SRControllerLog;
import com.senrui.core.aop.annotationntool.SRServiceLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanConfig {

    @Bean
    public SRServiceLog srServiceLog() {
        return new SRServiceLog();
    }

    @Bean
    public SRControllerLog srControllerLog() {
        return new SRControllerLog();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        final List<GlobalAuthenticationConfigurerAdapter> configurers = new ArrayList<>();
        configurers.add(new GlobalAuthenticationConfigurerAdapter() {
            @Override
            public void configure(AuthenticationManagerBuilder auth) throws Exception {
                // auth.doSomething()
            }
        });
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
