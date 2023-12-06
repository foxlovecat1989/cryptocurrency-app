package com.ed.tradedata.service;

import com.binance.connector.client.WebSocketStreamClient;
import com.ed.tradedata.domain.DiffDepthTradeData;
import com.ed.tradedata.domain.KLineTradeData;
import com.ed.tradedata.producer.DiffDepthTradeDataProducer;
import com.ed.tradedata.producer.KLineTradeDataProducer;
import com.ed.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.ed.tradedata.domain.enums.Symbol.BTCUSDT;

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
        int streamId = webSocketStreamClient.diffDepthStream(BTCUSDT.getSymbol(), ONE_THOUSAND_MILLISECONDS, resp -> {
            JsonUtils.objectFromJson(resp, DiffDepthTradeData.class)
                    .ifPresent(diffDepthTradeDataProducer::publishDiffDepthTradeData);
        });
        // TODO: save streamId to redis
    }

    public void processKLineTradeDataStream() {
        int streamId = webSocketStreamClient.klineStream(BTCUSDT.getSymbol(), ONE_SECOND_INTERVAL, resp -> {
            JsonUtils.objectFromJson(resp, KLineTradeData.class)
                    .ifPresent(kLineTradeDataProducer::publishKLineTradeData);
        });
        // TODO: save streamId to redis
    }

    public void disconnect(int streamId) {
        webSocketStreamClient.closeConnection(streamId);
    }
}
