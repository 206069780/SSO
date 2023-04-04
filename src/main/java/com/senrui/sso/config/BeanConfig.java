package com.senrui.sso.config;


import com.senrui.core.aop.annotationntool.SRControllerLog;
import com.senrui.core.aop.annotationntool.SRServiceLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
