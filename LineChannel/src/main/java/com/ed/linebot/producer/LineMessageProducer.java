package com.ed.linebot.producer;

import com.ed.linebot.domain.LineMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.ed.kafkastream.constant.topic.KafkaTopic.LINE_CHANNEL_DATA_TOPIC;

@Service
@RequiredArgsConstructor
public class LineMessageProducer {
    private final KafkaTemplate<String, LineMessage> kafkaTemplate;

    public void publishLineMessage(LineMessage lineMessage) {
        kafkaTemplate.send(LINE_CHANNEL_DATA_TOPIC, lineMessage.getReplyToken(), lineMessage);
    }
}
