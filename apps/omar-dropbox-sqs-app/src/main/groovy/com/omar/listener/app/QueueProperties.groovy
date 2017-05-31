package com.omar.listener.app

import groovy.transform.ToString
import org.springframework.boot.context.properties.ConfigurationProperties

@ToString(includeNames = true)
@ConfigurationProperties(prefix = "queue")
class QueueProperties {

    String name;

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }
}
