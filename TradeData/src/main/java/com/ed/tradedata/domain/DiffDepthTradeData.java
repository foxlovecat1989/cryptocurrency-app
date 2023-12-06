package com.ed.tradedata.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class DiffDepthTradeData {
    @JsonProperty("e") private String eventType;
    @JsonProperty("E") private Long eventTime;
    @JsonProperty("s") private String symbol;

    @JsonProperty("U") private String firstUpdateIdInEvent;
    @JsonProperty("u") private String lastUpdateIdInEvent;

    @JsonProperty("b") private List<List<Double>> bids;
    @JsonProperty("a") private List<List<Double>> asks;
}