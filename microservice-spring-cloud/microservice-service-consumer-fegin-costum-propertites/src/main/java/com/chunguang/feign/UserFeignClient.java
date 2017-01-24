package com.chunguang.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunguang.domain.User;

@FeignClient("microservice-service-provider")
public interface UserFeignClient {
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
//    feign client :  的注解需要@PathVariable("id")  不想springmvc @PathVariable ,还有getmapping不支持
    User findUserById(@PathVariable("id") Long id);
  
}