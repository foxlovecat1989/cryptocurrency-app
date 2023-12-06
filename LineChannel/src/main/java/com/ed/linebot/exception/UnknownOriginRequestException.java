package com.ed.linebot.exception;

public class UnknownOriginRequestException extends IllegalAccessException {
    public static final String ERROR_MSG = "UNKNOWN ORIGIN REQUEST - CONNECTION REFUSED";

    public UnknownOriginRequestException() {
        super(ERROR_MSG);
    }
}
