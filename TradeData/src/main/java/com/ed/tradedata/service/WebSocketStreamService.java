package com.ed.tradedata.service;

import com.binance.connector.client.WebSocketStreamClient;
import com.ed.tradedata.model.dto.DiffDepthTradeDataDto;
import com.ed.tradedata.model.dto.KLineTradeDataDto;
import com.ed.tradedata.kafka.producer.DiffDepthTradeDataProducer;
import com.ed.tradedata.kafka.producer.KLineTradeDataProducer;
import com.ed.utils.json.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ed.tradedata.model.enums.Symbol.BTCUSDT;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketStreamService {
    public static final int ONE_THOUSAND_MILLISECONDS = 1000;
    public static final String ONE_SECOND_INTERVAL = "1s";

    private final WebSocketStreamClient webSocketStreamClient;
    private final DiffDepthTradeDataProducer diffDepthTradeDataProducer;
    private final KLineTradeDataProducer kLineTradeDataProducer;

    public void processDiffDepthTradeDataStream() {
        int streamId = webSocketStreamClient.diffDepthStream(
                BTCUSDT.getSymbol(),
                ONE_THOUSAND_MILLISECONDS,
                resp -> Optional.ofNullable(JsonUtils.jsonToObject(resp, DiffDepthTradeDataDto.class))
                        .ifPresent(diffDepthTradeDataProducer::publishDiffDepthTradeData));
        // TODO: save streamId to redis
    }

    public void processKLineTradeDataStream() {
        int streamId = webSocketStreamClient.klineStream(
                BTCUSDT.getSymbol(),
                ONE_SECOND_INTERVAL,
                resp -> Optional.ofNullable( JsonUtils.jsonToObject(resp, KLineTradeDataDto.class))
                         .ifPresent(kLineTradeDataProducer::publishKLineTradeData));
        // TODO: save streamId to redis
    }

    public void disconnect(int streamId) {
        webSocketStreamClient.closeConnection(streamId);
    }
}
