package com.zcl.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 项目名称：cloud-demo
 * 描述：基于java代码的feign自定义配置设置
 *
 * @author zhong
 * @date 2022-05-29 11:07
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.BASIC;
    }
}
