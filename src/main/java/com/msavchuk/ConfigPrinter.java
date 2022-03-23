package com.msavchuk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RefreshScope
@Component
public class ConfigPrinter {

    @Value("${test.conf}")
    private volatile String testConf;

    @Value("${amazon.redis.endpoint}")
    private volatile String prop;

    @PostConstruct
    public void postConstructor() {
        System.out.println(testConf + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(prop + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }


}
