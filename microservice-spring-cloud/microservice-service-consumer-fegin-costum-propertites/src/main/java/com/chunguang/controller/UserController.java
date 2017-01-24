package com.chunguang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chunguang.domain.User;
import com.chunguang.feign.TestFeignClient;
import com.chunguang.feign.UserFeignClient;

@RestController
public class UserController {
	 
	@Autowired
	private UserFeignClient userFeignClient;
	@Autowired
	private TestFeignClient testFeignClient;
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id){
		
		
		User users = userFeignClient.findUserById(id);
		return users;
	}
//	http://localhost:7909/conf2/MICROSERVICE-SERVICE-PROVIDER
	@GetMapping("/conf2/{name}")
	public String getUser2(@PathVariable String name){
		
		
	
		return testFeignClient.findServiceInfoFromEurekaByServiceName(name);
	}
	 
}
