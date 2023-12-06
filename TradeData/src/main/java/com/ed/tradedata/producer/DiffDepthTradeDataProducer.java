package com.ed.tradedata.producer;

import com.ed.tradedata.domain.DiffDepthTradeData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.ed.kafkastream.constant.topic.KafkaTopic.DIFF_DEPTH_TRADE_DATA_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiffDepthTradeDataProducer {
    private final KafkaTemplate<Long, DiffDepthTradeData> kafkaTemplate;

    public void publishDiffDepthTradeData(DiffDepthTradeData diffDepthTradeData) {
        kafkaTemplate.send(DIFF_DEPTH_TRADE_DATA_TOPIC, diffDepthTradeData.getEventTime(), diffDepthTradeData);
    }
}
