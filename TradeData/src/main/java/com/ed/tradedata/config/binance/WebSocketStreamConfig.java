package com.ed.tradedata.config.binance;

import com.binance.connector.client.WebSocketStreamClient;
import com.binance.connector.client.impl.WebSocketStreamClientImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketStreamConfig {

    @Bean
    WebSocketStreamClient webSocketStreamClient() {
        return new WebSocketStreamClientImpl();
    }
}
