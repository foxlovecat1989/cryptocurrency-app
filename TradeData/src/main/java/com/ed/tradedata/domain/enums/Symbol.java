package com.ed.tradedata.domain.enums;

public enum Symbol {
    BTCUSDT("btcusdt");

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
