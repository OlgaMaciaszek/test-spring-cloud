package test.gateway.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Olga Maciaszek-Sharma
 */
public class CustomLoadBalancerConfig {

	@Bean
	public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
		return ServiceInstanceListSupplier.builder()
				.withDiscoveryClient()
				.withHints()
				.withCaching()
				.build(context);
	}
}
