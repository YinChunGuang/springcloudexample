package com.chunguang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chunguang.dao.UserRepository;
import com.chunguang.domain.User;

@RestController
public class UserController {
	@Autowired
	private UserRepository repository;

	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id){
		return repository.findOne(id);
	}
	
	@GetMapping("/user")
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	@PostMapping("/update/{id}")
	public User testPost(@PathVariable Long id,@RequestBody User user){
		 
		return user;
	}
	
	@GetMapping("/get/{id}")
	public User testGet(@PathVariable Long id,@RequestParam("name") String name){
		 System.err.println(id+"===================================="+name);
		 User user = new User();
		 user.setName("test");
		return user;
	}
}
