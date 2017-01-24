package com.chunguang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chunguang.domain.User;

@RestController
public class UserController {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadBalancerClient;

	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id) {

		ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-service-provider");
		System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
				+ serviceInstance.getPort());

		return this.restTemplate.getForObject("http://microservice-service-provider/user/" + id, User.class);
	}
	/**
	 * 这里检测配置文件配置是否生效， 单独访问了一个节点，这说明了我们的 ribbon生效了的，这个并不是实战的目的。仅仅是检验单独使用ribbon
	 * 
===:microservice-service-provider:localhost:7900
===:microservice-service-provider:localhost:7900
===:microservice-service-provider:localhost:7900
===:microservice-service-provider:localhost:7900
===:microservice-service-provider:localhost:7900
===:microservice-service-provider:localhost:7900
===:microservice-service-provider:localhost:7900
===:microservice-service-provider:localhost:7900
===:microservice-service-provider:localhost:7900
===:microservice-service-provider:localhost:7900

	 * @return
	 */
	  @GetMapping("/test")
	  public String test() {
		  
	    ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-service-provider");
	    System.out.println("111" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":" + serviceInstance.getPort());

	    ServiceInstance serviceInstance2 = this.loadBalancerClient.choose("microservice-service-provider2");
	    System.out.println("222" + ":" + serviceInstance2.getServiceId() + ":" + serviceInstance2.getHost() + ":" + serviceInstance2.getPort());

	    return "1";
	  }
	

}
