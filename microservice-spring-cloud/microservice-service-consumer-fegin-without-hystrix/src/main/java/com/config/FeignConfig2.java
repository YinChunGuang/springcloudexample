package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;

@Configuration
public class FeignConfig2 {


    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("user", "111111");
    }
	/**
	 *  默认是feignHystrix.Builder. 现在用feign替换原有的 ，就禁用了单个client的hystrix
	 * @return
	 */
    @Bean
 	@Scope("prototype")
 	public Feign.Builder feignBuilder() {
 		return Feign.builder();
 	}
}
