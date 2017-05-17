package com.omar.listener.app

import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.support.MessageBuilder
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.cloud.aws.messaging.listener.Acknowledgment;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import java.sql.Timestamp;
import java.util.Date;
import groovy.json.JsonSlurper;

class OmarSqsQueueReceiver {

    private final QueueMessagingTemplate queueMessagingTemplate;

    @Autowired
    OmarSqsQueueReceiver(AmazonSQS amazonSqs) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqs);
    }

    @MessageMapping("praveen-queue")
    @SqsListener(value = "praveen-queue", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void receive(String message, Acknowledgment acknowledgment) {
        //Date object
        Date date= new Date();
        //getTime() returns current time in milliseconds
        long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class
        Timestamp ts = new Timestamp(time);
        System.out.println("Current Time Stamp: "+ts);
        System.out.println("Inside receive : " + message);

        def slurper = new groovy.json.JsonSlurper();
        def result = slurper.parseText(message);

        println result.Records.s3.bucket.name
        println result.Records.s3.object.key

    }
}