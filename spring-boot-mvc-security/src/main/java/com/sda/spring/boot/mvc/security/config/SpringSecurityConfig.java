package com.sda.spring.boot.mvc.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // role admin allow access to /admin/**
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // provide authorization
        // disable cross site request forgery
        http.csrf().disable()
                .authorizeRequests()
                //no auth
                .antMatchers("/", "/home", "/about").permitAll()
                // admin auth
                .antMatchers("/admin/**")
                .hasAnyRole("ADMIN")
                // user auth
                .antMatchers("/user/**")
                .hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }

    // in memory authentication

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // create an admin user with an unencrypted password
        auth.inMemoryAuthentication()
                //admin
                .withUser("admin")
                .password("{noop}pass")
                .roles("ADMIN")
                .and()
                //regular user
                .withUser("user")
                .password("{noop}pass")
                .roles("USER");
    }

    //db authentication
}
