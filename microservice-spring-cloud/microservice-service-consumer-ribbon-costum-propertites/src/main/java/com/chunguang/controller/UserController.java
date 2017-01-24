package com.chunguang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

		return this.restTemplate.getForObject("http://MICROSERVICE-SERVICE-PROVIDER/user/" + id, User.class);
	}
	/**
	 * 这里检测配置文件配置是否生效，
	 * 
111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7906
111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7905
111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7906
111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7905
111:microservice-service-provider:192.168.1.106:7904
222:microservice-service-provider2:192.168.1.106:7906
111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7905
111:microservice-service-provider:192.168.1.106:7904
222:microservice-service-provider2:192.168.1.106:7906

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
