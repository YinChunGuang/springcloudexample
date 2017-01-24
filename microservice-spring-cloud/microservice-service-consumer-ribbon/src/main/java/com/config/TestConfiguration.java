package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.chunguang.annu.ExcludeFromComponentScan;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
@ExcludeFromComponentScan
public class TestConfiguration {
//	@Autowired
//	private IClientConfig config;

	@Bean
	public IRule ribbonRule() {
//		在我们的子包内的时候，启动的时候就会被夹在，放在包外面的时候，调用的时候才会加载
		return new RandomRule();
	}
}
