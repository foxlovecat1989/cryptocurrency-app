package com.ed.kafkastream.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.ed.kafkastream.constant.topic.KafkaTopic.DIFF_DEPTH_TRADE_DATA_TOPIC;
import static com.ed.kafkastream.constant.topic.KafkaTopic.K_LINE_TRADE_DATA_TOPIC;


@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic diffDepthTradeDataTopic() {
        return TopicBuilder.name(DIFF_DEPTH_TRADE_DATA_TOPIC)
                .build();
    }

    @Bean
    public NewTopic kLineTradeDataTopic() {
        return TopicBuilder.name(K_LINE_TRADE_DATA_TOPIC)
                .build();
    }
}
