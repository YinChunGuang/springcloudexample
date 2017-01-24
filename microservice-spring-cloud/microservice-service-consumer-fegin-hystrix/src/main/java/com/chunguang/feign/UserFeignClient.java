package com.chunguang.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunguang.domain.User;
import com.chunguang.fallback.HystrixClientFallback;

@FeignClient(name="microservice-service-provider" ,fallback = HystrixClientFallback.class)
public interface UserFeignClient {
  
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    User findUserById(@PathVariable("id") Long id);
   
}