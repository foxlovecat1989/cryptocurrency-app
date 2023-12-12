package com.ed.statistictradedata.controller;

import com.ed.statistictradedata.model.StatisticTradeData;
import com.ed.statistictradedata.service.StatisticTradeDataQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/statistic-trade-data")
public class StatisticTradeDataController {
    private final StatisticTradeDataQueryService statisticTradeDataQueryService;

    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<StatisticTradeData> findAllStatisticTradeData() {
        return statisticTradeDataQueryService.findAll().log();
    }
}

