package com.chunguang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chunguang.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class UserController {
	@Autowired
	private RestTemplate restTemplate;
 
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	@HystrixCommand(fallbackMethod="getUserFallback")
	public User getUser(@PathVariable Long id){
		return this.restTemplate.getForObject("http://microservice-service-provider/user/"+id, User.class);
	}
	public User getUserFallback( Long id){
		
		User user = new User();
		user.setName("fallback");
		return user;
		
	}
 
}
