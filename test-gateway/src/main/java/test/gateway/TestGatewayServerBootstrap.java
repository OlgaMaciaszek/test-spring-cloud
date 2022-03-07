package test.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import test.gateway.handler.RequestBodyRoutePredicateFactory;

/**
 * @author ace
 * @create 2018/3/12.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TestGatewayServerBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(TestGatewayServerBootstrap.class, args);
    }

    @Bean
    public RequestBodyRoutePredicateFactory requestBodyRoutePredicateFactory() {
        return new RequestBodyRoutePredicateFactory();
    }

    /**
     * comment this method
     * @param context
     * @return
     */
//    @Bean
//    public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
//        return ServiceInstanceListSupplier.builder()
//                .withDiscoveryClient()
//                .withHints()
//                .withCaching()
//                .build(context);
//    }
}
