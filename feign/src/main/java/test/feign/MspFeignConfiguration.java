package test.feign;

import feign.RequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MspFeignConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "cloudx.msp", name = "header-transfer", havingValue = "true", matchIfMissing = true)
    public RequestInterceptor headerTransferRequestInterceptor() {
        return new FeignHeaderTransferRequestInterceptor();
    }
}
