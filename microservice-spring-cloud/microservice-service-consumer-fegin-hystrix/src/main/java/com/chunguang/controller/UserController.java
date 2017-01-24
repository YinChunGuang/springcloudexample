package com.chunguang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.chunguang.domain.User;
import com.chunguang.feign.UserFeignClient;

@RestController
public class UserController {
	 
	@Autowired
	private UserFeignClient userFeignClient;
 
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id){
		User users = userFeignClient.findUserById(id);
		return users;
	}
	
 
}
