package com.chunguang.fallback;

import org.springframework.stereotype.Component;

import com.chunguang.domain.User;
import com.chunguang.feign.UserFeignClient;
@Component
public class FallBack1 implements UserFeignClient{

	@Override
	public User findUserById(Long id) {
		 User user = new User();
		 user.setName("test hystrix");
		return user;
	}

}
