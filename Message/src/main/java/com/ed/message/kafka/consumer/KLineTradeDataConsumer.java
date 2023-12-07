package com.ed.message.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.ed.kafkastream.topic.KafkaTopic.K_LINE_TRADE_DATA_TOPIC;


@Service
@Slf4j
public class KLineTradeDataConsumer {
    @KafkaListener(topics = {K_LINE_TRADE_DATA_TOPIC}, groupId = "groupIdA")
    void listen(String data) {
        log.info("Received: {}", data);
    }
}