package com.chunguang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.netflix.loadbalancer.RandomRule;

@SpringBootApplication  // 扫描的包是自己的子包
@EnableEurekaClient
public class MicroserviceServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceServiceProviderApplication.class, args);
	}
	
	
}
