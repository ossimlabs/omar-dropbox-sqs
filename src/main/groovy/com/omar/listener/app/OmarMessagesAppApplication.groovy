package com.omar.listener.app

import com.amazonaws.services.sqs.AmazonSQS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.context.annotation.Bean
import org.springframework.integration.annotation.InboundChannelAdapter

@SpringBootApplication
@EnableBinding(Source.class)

class OmarSQSReceiverApp {

    @Autowired
    AmazonSQS amazonSqs

    @Bean
    OmarSqsQueueReceiver omarSqsQueueReceiver() {
        return new OmarSqsQueueReceiver(amazonSqs);
    }

    static void main(String[] args) {
        SpringApplication.run OmarSQSReceiverApp, args
    }

    @InboundChannelAdapter(Source.OUTPUT)
    public String sayHello() {
        return "hello again" + System.currentTimeMillis();
    }
}
