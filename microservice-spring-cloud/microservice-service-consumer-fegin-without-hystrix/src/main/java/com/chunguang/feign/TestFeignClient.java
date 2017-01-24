package com.chunguang.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chunguang.domain.User;
import com.chunguang.fallback.FallBack2;
import com.config.FeignConfig2;

@FeignClient(name="eureka-test", url = "http://localhost:8761/",configuration=FeignConfig2.class,fallback = FallBack2.class)
public interface TestFeignClient {
 
	@RequestMapping(value = "/eureka/apps/{serviceName}")
	public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName); 
}