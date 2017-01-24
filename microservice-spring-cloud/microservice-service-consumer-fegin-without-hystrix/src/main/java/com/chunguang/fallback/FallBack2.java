package com.chunguang.fallback;

import org.springframework.stereotype.Component;

import com.chunguang.feign.TestFeignClient;
@Component
public class FallBack2 implements TestFeignClient {

	@Override
	public String findServiceInfoFromEurekaByServiceName(String serviceName) {
		 
		return "0000000000000000000";
	}

}
