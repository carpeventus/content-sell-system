package com.netease.sell.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 指定外部静态资源的映射和路径位置
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Value("${custom.static.url-pattern}")
    private String urlPattern;
    @Value("${custom.static.image-location}")
    private String imageLocation;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(urlPattern).addResourceLocations("file:" + imageLocation);
    }
}
