package com.ed.tradedata.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class KLineTradeData {

    @JsonProperty("e") private String eventType;
    @JsonProperty("E") private Long eventTime;
    @JsonProperty("s") private String symbol;
    @JsonProperty("k") private KLine kline;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    @ToString
    public static class KLine {
        @JsonProperty("t") private Long kLineStartTime;
        @JsonProperty("T") private Long kLineCloseTime;
        @JsonProperty("s") private String symbol;
        @JsonProperty("i") private String Interval;
        @JsonProperty("f") private Long firstTradId;
        @JsonProperty("L") private Long lastTradId;

        @JsonProperty("o") private String openPrice;
        @JsonProperty("c") private String closePrice;
        @JsonProperty("h") private String highPrice;
        @JsonProperty("l") private String lowPrice;
        @JsonProperty("v") private String bestAssetVolume;
        @JsonProperty("n") private Long numberOfTrades;
        @JsonProperty("x") private Boolean isThisKLineClosed;
        @JsonProperty("q") private String quoteAssetVolume;
        @JsonProperty("V") private String takerBuyBaseAssetVolume;
        @JsonProperty("Q") private String takerBuyQuoteAssetVolume;
    }
}