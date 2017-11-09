package com.example.springboottwitter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author zhin
 * @date 2017/11/9
 */
@Configuration
@Profile("redis")
@EnableRedisHttpSession
public class RedisConfig {

}
