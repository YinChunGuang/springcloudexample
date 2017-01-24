package com.chunguang.feign;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chunguang.domain.User;

@FeignClient("microservice-service-provider")
public interface UserFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    List<User> getUsers();
    
    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
//    feign client :  的注解需要@PathVariable("id")  不想springmvc @PathVariable ,还有getmapping不支持
    User findUserById(@PathVariable("id") Long id);
    
    @RequestMapping(method = RequestMethod.POST, value = "/update/{id}")
    User update(@PathVariable("id") Long id, User user);

    /**
     * 如果是get请求这里是不能够传递对象的，只能够通过多个参数的形式传递，
     * 如果是传递对象的话，最好用Post方式
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
//    User testGet( User user);
    User testGet(@PathVariable("id") Long id,@RequestParam("name") String name);
}