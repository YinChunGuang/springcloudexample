package com.chunguang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class ConsumeRibbonApplication {
	@Bean
	@LoadBalanced      //   整合ribbon只需这一步就行了
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	/**
	 * ribbon 客户端的负载均衡策略 , Eureka 已经集成了 ribbon
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConsumeRibbonApplication.class, args);
	}
}
