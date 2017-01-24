package com.chunguang.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.chunguang.domain.User;
import com.chunguang.feign.HystrixClientWithFallBackFactory;
import com.chunguang.feign.UserFeignClient;

import feign.hystrix.FallbackFactory;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<UserFeignClient>{

	Logger loger = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);
	@Override
	public UserFeignClient create(Throwable cause) {
		loger.error("cause===============:   {}",cause.getMessage());
		
		return new HystrixClientWithFallBackFactory(){

			@Override
			public User findUserById(Long id) {
				 
				User user = new User();
				 user.setName("test fall back factory");
				return user;
			}
			
		};
	}

}
