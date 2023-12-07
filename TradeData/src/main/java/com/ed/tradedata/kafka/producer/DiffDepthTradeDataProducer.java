package com.ed.tradedata.kafka.producer;

import com.ed.tradedata.model.dto.DiffDepthTradeDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.ed.kafkastream.topic.KafkaTopic.DIFF_DEPTH_TRADE_DATA_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiffDepthTradeDataProducer {
    private final KafkaTemplate<Long, DiffDepthTradeDataDto> kafkaTemplate;

    public void publishDiffDepthTradeData(DiffDepthTradeDataDto diffDepthTradeDataDto) {
        kafkaTemplate.send(DIFF_DEPTH_TRADE_DATA_TOPIC, diffDepthTradeDataDto.getEventTime(), diffDepthTradeDataDto);
    }
}
