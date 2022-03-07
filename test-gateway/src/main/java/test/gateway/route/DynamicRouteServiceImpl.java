package test.gateway.route;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.Executor;

@Component
@RefreshScope
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {
    private static final Logger log = LoggerFactory.getLogger(DynamicRouteServiceImpl.class);
    private ApplicationEventPublisher publisher;
    @Autowired
    private RouteDefinitionRepository routeDefinitionRepository;
    @Value("${spring.cloud.gateway.third-data-id:gateway-third}")
    private String dataId;
    @Value("${spring.cloud.nacos.config.group:gateway-third}")
    private String group;
    @Autowired
    private NacosConfigManager nacosConfigManager;

    /**
     * 监听Nacos Server下发的动态路由配置
     */
    public void dynamicRouteByNacosListener(DynamicRouteServiceImpl dynamicRouteService) {
        try {
            ConfigService configService = nacosConfigManager.getConfigService();
            configService.addListener(dataId, group, new Listener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("收到动态路由信息" + configInfo);
                    dynamicRouteService.update(configInfo);
                }

                @Override
                public Executor getExecutor() {
                    return null;
                }
            });
            String config = configService.getConfig(dataId, group, 5000);
            dynamicRouteService.update(config);
        } catch (NacosException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(String configInfo) {
        if (StringUtils.isEmpty(configInfo)) {
            return;
        }
        List<RouteDefinition> nacosRouteDefinitions = JSON.parseArray(configInfo, RouteDefinition.class);
        Flux<RouteDefinition> inMemoryRouteDefinitions = routeDefinitionRepository.getRouteDefinitions();
        inMemoryRouteDefinitions.toIterable().forEach(routeDefinition -> routeDefinitionRepository.delete(Mono.just(routeDefinition.getId())));
        nacosRouteDefinitions.forEach(routeDefinition -> routeDefinitionRepository.save(Mono.just(routeDefinition)).subscribe());
        publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
        dynamicRouteByNacosListener(this);
    }

}
