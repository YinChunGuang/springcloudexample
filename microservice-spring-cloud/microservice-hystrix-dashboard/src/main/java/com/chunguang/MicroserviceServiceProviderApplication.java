package com.chunguang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class MicroserviceServiceProviderApplication {
/**
 * 1,
 * 先启动ribbon with hystrix，然后访问其中一个api，
 * 在来dashboard 页面来观察
 * 2,先启动feign with hystrix，  这个启动失败，api 404 是缺少依赖，没有eventstream 依赖，在feign添加一个 hystrix 添加依赖，然后@EnableCircuitBreaker
 * 在来dashboard 页面来观察
 * @param args
 */
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceServiceProviderApplication.class, args);
	}
}
