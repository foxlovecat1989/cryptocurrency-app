package com.ed.statistictradedata.service;

import com.ed.statistictradedata.controller.StatisticTradeDataController;
import com.ed.statistictradedata.model.StatisticTradeData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = StatisticTradeDataController.class)
@AutoConfigureWebTestClient
class StatisticTradeDataQueryServiceTest {
    @MockBean
    private StatisticTradeDataQueryService statisticTradeDataQueryService;

    @Autowired
    private WebTestClient webTestClient;
    static List<StatisticTradeData> statisticTradeData = List.of(
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

    @Test
    void getAllMovieInfos() {
        when(statisticTradeDataQueryService.findAll()).thenReturn(Flux.fromIterable(statisticTradeData));

        webTestClient
                .get()
                .uri("/api/v1/statistic-trade-data")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBodyList(StatisticTradeData.class)
                .hasSize(3);
    }

    @Test
    void findAllDiffDepthData() {
        // GIVEN
        // WHEN
        // THEN
    }
}