package test.gateway;

import test.gateway.config.CustomLoadBalancerConfig;
import test.gateway.handler.RequestBodyRoutePredicateFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author ace
 * @create 2018/3/12.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@LoadBalancerClient(name = "test-consumer", configuration = CustomLoadBalancerConfig.class)
public class TestGatewayServerBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(TestGatewayServerBootstrap.class, args);
    }

    @Bean
    public RequestBodyRoutePredicateFactory requestBodyRoutePredicateFactory() {
        return new RequestBodyRoutePredicateFactory();
    }

}
