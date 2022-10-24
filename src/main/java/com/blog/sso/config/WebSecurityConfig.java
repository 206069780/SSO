package com.blog.sso.config;

import com.blog.sso.exception.ErrorCode;
import com.blog.sso.exception.JwtAccessDeniedHandler;
import com.blog.sso.exception.JwtAuthenticationEntryPoint;
import com.blog.sso.filter.JwtAuthenticationTokenFilter;
import com.blog.sso.filter.JwtAuthorizationFilter;
import com.blog.sso.hanld.Http401AuthenticationEntryPoint;
import com.blog.sso.service.iml.SSOUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static java.util.Collections.singletonList;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SSOUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(withDefaults())
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/register", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), stringRedisTemplate))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())
              ;
        http.headers().frameOptions().disable();
    }

    /**
     * Cors配置优化
     **/
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(singletonList("*"));
        configuration.setAllowedHeaders(singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setExposedHeaders(singletonList("Authorization"));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}