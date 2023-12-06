package com.ed.tradedata.producer;

import com.ed.tradedata.domain.KLineTradeData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.ed.kafkastream.constant.topic.KafkaTopic.K_LINE_TRADE_DATA_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class KLineTradeDataProducer {
    private final KafkaTemplate<Long, KLineTradeData> kafkaTemplate;

    public void publishKLineTradeData(KLineTradeData kLineTradeData) {
        kafkaTemplate.send(K_LINE_TRADE_DATA_TOPIC, kLineTradeData.getEventTime(), kLineTradeData);
    }
}
