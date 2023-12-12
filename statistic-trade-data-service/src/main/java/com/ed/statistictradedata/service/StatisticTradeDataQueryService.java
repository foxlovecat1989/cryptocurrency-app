package com.ed.statistictradedata.service;

import com.ed.statistictradedata.model.StatisticTradeData;
import com.ed.statistictradedata.repository.StatisticTradeDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StatisticTradeDataQueryService {
    private final StatisticTradeDataRepository statisticTradeDataRepository;

    public Flux<StatisticTradeData> findAll() {
        return statisticTradeDataRepository.findAll();
    }

    public Mono<StatisticTradeData> create(StatisticTradeData statisticTradeData) {
        return statisticTradeDataRepository.save(statisticTradeData);
    }
}
