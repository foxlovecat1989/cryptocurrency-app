package com.ed.tradedata.kafka.producer;

import com.ed.tradedata.model.dto.KLineTradeDataDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.ed.kafkastream.topic.KafkaTopic.K_LINE_TRADE_DATA_TOPIC;


@Service
@RequiredArgsConstructor
@Slf4j
public class KLineTradeDataProducer {
    private final KafkaTemplate<Long, KLineTradeDataDto> kafkaTemplate;

    public void publishKLineTradeData(KLineTradeDataDto kLineTradeDataDto) {
        kafkaTemplate.send(K_LINE_TRADE_DATA_TOPIC, kLineTradeDataDto.getEventTime(), kLineTradeDataDto);
    }
}
