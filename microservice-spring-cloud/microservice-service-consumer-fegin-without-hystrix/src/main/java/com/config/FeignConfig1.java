package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Contract;
import feign.Logger;

@Configuration
public class FeignConfig1 {

	/**
	 * 这里是使用feign自己的策略。没有使用springmvc的注解配置。
	 * @return
	 */
	@Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
      return Logger.Level.FULL;
    }
}
