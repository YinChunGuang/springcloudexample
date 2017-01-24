package com.chunguang.fallback;

import org.springframework.stereotype.Component;

import com.chunguang.domain.User;
import com.chunguang.feign.UserFeignClient;

@Component
public class HystrixClientFallback implements UserFeignClient{

	@Override
	public User findUserById(Long id) {
		 User user = new User();
		 user.setName("test fall back");
		return user;
	}

}
