package com.ed.statistictradedata.repository;

import com.ed.statistictradedata.model.StatisticTradeData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StatisticTradeDataRepository extends ReactiveMongoRepository<StatisticTradeData, String> {
}
