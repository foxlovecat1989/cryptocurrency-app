package com.ed.statistictradedata.controller;

import com.ed.statistictradedata.model.StatisticTradeData;
import com.ed.statistictradedata.repository.StatisticTradeDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class StatisticTradeDataControllerTest {
    @Autowired
    private StatisticTradeDataRepository statisticTradeDataRepository;
    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        List<StatisticTradeData> statisticTradeData = List.of(
                StatisticTradeData.builder()
                        .symbol("BTCUSDT")
                        .eventTime(new Date().getTime())
                        .build(),
                StatisticTradeData.builder()
                        .symbol("BTCUSDT2")
                        .eventTime(new Date().getTime())
                        .build(),
                StatisticTradeData.builder()
                        .symbol("BTCUSDT3")
                        .eventTime(new Date().getTime())
                        .build());

        statisticTradeDataRepository
                .deleteAll()
                .thenMany(statisticTradeDataRepository.saveAll(statisticTradeData))
                .blockLast();
    }

    @Test
    void findAllDiffDepthData() {
        webTestClient
                .get()
                .uri("/api/v1/statistic-trade-data")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(StatisticTradeData.class)
                .hasSize(3);
    }
}