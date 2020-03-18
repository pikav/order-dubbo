package com.order.server.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {

    private static  final Logger log = LoggerFactory.getLogger(KafkaTestController.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 生成消息
     */
    @GetMapping("/kafkaTest")
    public void produce(String msg) {
        kafkaTemplate.send("test_topic", msg);
        log.info("生产者消息发送成功:" + msg);
    }

    /**
     * 消费消息
     * @param record
     */
    @KafkaListener(topics = "test_topic")
    public void listen(ConsumerRecord<?, ?> record) {
        log.info("topic = {}, offset = {}, value = {} \n", record.topic(), record.offset(), record.value());
    }

}
