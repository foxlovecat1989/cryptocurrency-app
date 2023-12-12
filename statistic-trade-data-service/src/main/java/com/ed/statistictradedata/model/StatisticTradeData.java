package com.ed.statistictradedata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class StatisticTradeData {
    @Id
    private String id;
    private Long eventTime;
    private String symbol;
    private List<List<Double>> bids;
    private List<List<Double>> asks;
}
