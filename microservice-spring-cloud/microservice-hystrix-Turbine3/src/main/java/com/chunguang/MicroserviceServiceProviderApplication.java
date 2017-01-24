package com.chunguang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class MicroserviceServiceProviderApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceServiceProviderApplication.class, args);
	}
}
