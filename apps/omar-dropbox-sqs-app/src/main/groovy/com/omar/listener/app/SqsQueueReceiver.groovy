package com.omar.listener.app

import com.amazonaws.services.sqs.AmazonSQS
import groovy.json.JsonBuilder;
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
import groovy.json.JsonBuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.InboundChannelAdapter;

class OmarSqsQueueReceiver {

    private final QueueMessagingTemplate queueMessagingTemplate;
    AwsData senddata = new AwsData();
    QueueConfiguration prop = new QueueConfiguration();

    @Autowired
    OmarSqsQueueReceiver(AmazonSQS amazonSqs) {
        this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqs);
    }

    @MessageMapping("dg-gcs-gegd-queue")
    @SqsListener(value = "dg-gcs-gegd-queue", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
//    @MessageMapping("praveen-queue")
//    @SqsListener(value = "praveen-queue", deletionPolicy = SqsMessageDeletionPolicy.NEVER)
    public void receive(String message, Acknowledgment acknowledgment) {
        //Date object
        Date date = new Date();
        //getTime() returns current time in milliseconds
        long time = date.getTime();
        //Passed the milliseconds to constructor of Timestamp class
        Timestamp ts = new Timestamp(time);
//        System.out.println("Current Time Stamp: " + ts);
//        System.out.println("Inside receive : " + message);

        def slurper = new groovy.json.JsonSlurper();
        def result = slurper.parseText(message);

        def json = new JsonBuilder();

        def root = json bucket: result.Records.s3.bucket.name, filename: result.Records.s3.object.key

        senddata.setFilename(root.filename);
        senddata.setBucket(root.bucket);

        System.out.println(senddata.getFilename())
//        System.out.println("bucket" + senddata.getBucket())
    }

    @InboundChannelAdapter(Source.OUTPUT)
    public AwsData sayHello() {
        return senddata;
    }

}