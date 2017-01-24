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
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class UserController {
	@Autowired
	private RestTemplate restTemplate;
	
	
	 @Autowired
	  private LoadBalancerClient loadBalancerClient;
	 /**
	  * 分别在两个service中启动多个实例，可以对比不同的配置的轮训规则的ribbon
	  * 这里用 provider进行修改名字和端口，总共启动四个就行了（相同的application name 对应分别两个服务）
	  * 这里的日志是在 TestConfig类在我们子包中的时候，此时使用的策略都是random   
	111:microservice-service-provider:192.168.1.106:7900
222:microservice-service-provider2:192.168.1.106:7903
	111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7904
	111:microservice-service-provider:192.168.1.106:7900
222:microservice-service-provider2:192.168.1.106:7904
	111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7904
	111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7904
	111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7904
	111:microservice-service-provider:192.168.1.106:7902
222:microservice-service-provider2:192.168.1.106:7903
	111:microservice-service-provider:192.168.1.106:7900
222:microservice-service-provider2:192.168.1.106:7903
															接下来的日志是config类在我们的包外面,很显然一个轮训一个是random， 
															
	222:microservice-service-provider2:192.168.1.106:7903
111:microservice-service-provider:192.168.1.106:7902
	222:microservice-service-provider2:192.168.1.106:7904
111:microservice-service-provider:192.168.1.106:7900
	222:microservice-service-provider2:192.168.1.106:7903
111:microservice-service-provider:192.168.1.106:7900
	222:microservice-service-provider2:192.168.1.106:7904
111:microservice-service-provider:192.168.1.106:7902
	222:microservice-service-provider2:192.168.1.106:7903
111:microservice-service-provider:192.168.1.106:7900
	222:microservice-service-provider2:192.168.1.106:7904
111:microservice-service-provider:192.168.1.106:7900
	222:microservice-service-provider2:192.168.1.106:7903
111:microservice-service-provider:192.168.1.106:7900
	222:microservice-service-provider2:192.168.1.106:7904
	 
	 										如果需要放在包内的时候，也希望不同的使用不同的策略的时候。自定义注解来解决这个问题, 此时配置Class可以放在任意的包位置
	 	@ComponentScan(excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = ExcludeFromComponentScan.class) })
	 	
	 										
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
	/** 
	 * 2.2.使用RestTemplate时，想要获得一个List时，应该用数组，而不应该直接用List 
	 *  User[] users = this.restTemplate.getForObject("http://MICROSERVICE-SERVICE-PROVIDER/user/"+id, User[].class);
	 *  Arrays.asList(users)
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable Long id){
		return this.restTemplate.getForObject("http://MICROSERVICE-SERVICE-PROVIDER/user/"+id, User.class);
	}
 
}
