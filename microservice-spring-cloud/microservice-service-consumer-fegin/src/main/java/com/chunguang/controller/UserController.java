package com.chunguang.controller;

import java.util.List;

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
	@GetMapping("/user")
	public List<User> getUsers(){
		List<User> users = userFeignClient.getUsers();
		return users;
	}
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id){
		
		
		User users = userFeignClient.findUserById(id);
		return users;
	}
	
	@GetMapping("/update/{id}")
	public User updateUser(@PathVariable Long id){
		User user2 = new User();
		user2.setId(3L);
		
		User users = userFeignClient.update(id, user2);
		return users; 
	}
//	http://XXX:000/update/1?id=XXX&name=XXX&age=,,,,,
	@GetMapping("/get/{id}")
	public User updateUser2(@PathVariable Long id,User user){
		
		User users = userFeignClient.testGet(id, user.getName());
		return users;
	}
}
