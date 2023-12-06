package com.ed.tradedata;

import com.ed.tradedata.service.WebSocketStreamService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TradeDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(TradeDataApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(WebSocketStreamService webSocketStreamService) {
        return args -> {
            webSocketStreamService.processDiffDepthTradeDataStream();
            webSocketStreamService.processKLineTradeDataStream();
        };
    }
}
