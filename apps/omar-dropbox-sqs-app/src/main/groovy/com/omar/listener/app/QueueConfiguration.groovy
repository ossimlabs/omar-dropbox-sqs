package com.omar.listener.app


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

/**
 * Created by pelangovan on 5/24/2017.
 */
@Configuration
@EnableConfigurationProperties(QueueProperties)
class QueueConfiguration {

    @Autowired
    private QueueProperties queueProperties;

    @PostConstruct
    void init() {
        System.setProperty("queueProperties.name", queueProperties.name);
        queueProperties.setName(queueProperties.name)

    }


}
