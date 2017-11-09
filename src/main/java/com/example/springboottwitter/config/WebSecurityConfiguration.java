package com.example.springboottwitter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author zhin
 * @date 2017/11/8
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/profile")
        .and()
        .logout().logoutSuccessUrl("/login")
        .and()
        .authorizeRequests()
        .antMatchers("/webjars/**", "/login", "/signin/**", "/signup").permitAll()
        .anyRequest().authenticated();
  }
}
