package com.chunguang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.chunguang.domain.User;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class UserController {
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * eureka 自带的 client， 两者使用都可以的 
	 */
	@Autowired
	private EurekaClient discoveryClient;
	/**
	 * You can also use the org.springframework.cloud.client.discovery.DiscoveryClient which provides a simple 
	 *    这里是spring 支持的服务发现client
	 */
	@Autowired
	private DiscoveryClient sDiscoveryClient;
	/**
	 * 到目前为止，我们的 项目还是直接进行url调用，没有通过eureka来获取服务信息进行调用，
	 * 现在仅仅完成了服务注册的功能。
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id){
		return this.restTemplate.getForObject("http://localhost:7900/user/"+id, User.class);
	}
	/**
	 * 可以在其他的微服务中调用这个服务名称来获取服务的url：ip+url
	 * @return
	 */
	@GetMapping("/url")
	public String serviceUrl() {
	    InstanceInfo instance = discoveryClient.getNextServerFromEureka("MICROSERVICE-SERVICE-PROVIDER", false);
	    return instance.getHomePageUrl();
	}
 
	@GetMapping("/url2")
	public ServiceInstance discoverClient() {
		sDiscoveryClient.getServices();
		ServiceInstance localServiceInstance = sDiscoveryClient.getLocalServiceInstance();
	    return localServiceInstance;
	}
	@GetMapping("/url3")
	public List<String> discoverClient1() {
		List<String> services = sDiscoveryClient.getServices();
	    return services;
	}
	@GetMapping("/url4")
	public List<ServiceInstance> discoverClient11() {
		List<ServiceInstance> services = sDiscoveryClient.getInstances("MICROSERVICE-SERVICE-PROVIDER");
	    return services;
	}
}
