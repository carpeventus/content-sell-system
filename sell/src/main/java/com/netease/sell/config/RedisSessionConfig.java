package com.netease.sell.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
/**
 * @author Haiyu
 * @date 2019/2/28 18:51
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {
}
