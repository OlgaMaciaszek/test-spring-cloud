package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigTestController {
    private static final Logger log = LoggerFactory.getLogger(ConfigTestController.class);

    @Value("${test.name:microservice}")
    private String name;

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String count() {
        log.info("receive a req");
        return name;
    }
}
