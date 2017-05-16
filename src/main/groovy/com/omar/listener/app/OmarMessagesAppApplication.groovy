package com.omar.listener.app

import com.amazonaws.services.sqs.AmazonSQS
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
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
}
