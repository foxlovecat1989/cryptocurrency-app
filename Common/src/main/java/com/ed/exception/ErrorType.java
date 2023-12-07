package com.ed.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorType {
    UnknownException("9999", HttpStatus.INTERNAL_SERVER_ERROR, "UNKNOWN ORIGIN REQUEST - CONNECTION REFUSED"),
    UnknownOriginRequestException("9000", HttpStatus.BAD_REQUEST, "UNKNOWN ORIGIN REQUEST - CONNECTION REFUSED"),
    NoSuchAlgorithmException("9001", HttpStatus.INTERNAL_SERVER_ERROR, "NO SUCH ALGORITHM"),
    InvalidKeyException("9002", HttpStatus.INTERNAL_SERVER_ERROR, "INVALID KEY");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
