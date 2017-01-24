package com.chunguang.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chunguang.domain.User;
import com.config.FeignConfig2;

@FeignClient(name="eureka-test", url = "http://localhost:8761/",configuration=FeignConfig2.class)
public interface TestFeignClient {
    /**
     *MVC的注解，和feign自带注解 两个都可以成功调用，则说明我们的单独配置策略是生效了额
     * @param id
     * @return
     */
    
//	@RequestMapping("/user/{id}")
//    User findUserById(@PathVariable("id") Long id);
	
	@RequestMapping(value = "/eureka/apps/{serviceName}")
	public String findServiceInfoFromEurekaByServiceName(@PathVariable("serviceName") String serviceName); 
}