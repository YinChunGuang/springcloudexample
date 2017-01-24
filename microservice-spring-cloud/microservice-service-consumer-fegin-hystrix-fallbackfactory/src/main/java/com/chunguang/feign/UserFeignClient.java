package com.chunguang.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chunguang.domain.User;

import com.chunguang.fallback.HystrixClientFallbackFactory;
/*
 *
 */
/**
 * 在最初服务未启动不可用的时候，会直接进入断路工厂。
 *  从服务可用到不可用的时候，在请求是失败固定次数（可以配置）之后，才开始进入这里的断路机制。
 *  两个不能同时存在，
 *  fallback = HystrixClientFallback.class, fallbackFactory = HystrixClientFactory.class
 *  fallbackFactory 是fallback的增强。使用了factory之后，就不能使用fallback了
 * @author Administrator
 *
 */
@FeignClient(name="microservice-service-provider" ,fallbackFactory = HystrixClientFallbackFactory.class)
public interface UserFeignClient {
  
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    User findUserById(@PathVariable("id") Long id);
   
}