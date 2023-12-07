package com.ed.linebot.kafka.producer;

import com.ed.linebot.model.dto.LineMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.ed.kafkastream.topic.KafkaTopic.LINE_CHANNEL_DATA_TOPIC;


@Service
@RequiredArgsConstructor
public class LineMessageProducer {
    private final KafkaTemplate<String, LineMessageDto> kafkaTemplate;

    public void publishLineMessage(LineMessageDto lineMessageDto) {
        kafkaTemplate.send(LINE_CHANNEL_DATA_TOPIC, lineMessageDto.getReplyToken(), lineMessageDto);
    }
}
