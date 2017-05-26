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

    QueueProperties getQueue() {
        return queue
    }

    void setQueue(QueueProperties queue) {
        this.queue = queue
    }
    @Autowired
        private QueueProperties queue;

        @PostConstruct
        void init() {
            System.setProperty("queue.name", queue.name);
            System.out.println("quenename" + queue.name);
            queue.setName(queue.name)

        }


    }
