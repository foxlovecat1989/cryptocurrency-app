package com.ed.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class SystemError extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatus;

    public SystemError(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorCode = errorType.getErrorCode();
        this.httpStatus = errorType.getHttpStatus();
    }

    public SystemError(ErrorType errorType, HttpStatus httpStatus) {
        super(errorType.getMessage());
        this.errorCode = errorType.getErrorCode();
        this.httpStatus = httpStatus;
    }

    public SystemError(ErrorType errorType, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorType.getErrorCode();
        this.httpStatus = errorType.getHttpStatus();
    }

    public SystemError(ErrorType errorType, String errorMsg, HttpStatus httpStatus) {
        super(errorMsg);
        this.errorCode = errorType.getErrorCode();
        this.httpStatus = httpStatus;
    }

    public SystemError(ErrorType errorType, Throwable cause) {
        super(errorType.getMessage(), cause);
        this.errorCode = errorType.getErrorCode();
        this.httpStatus = errorType.getHttpStatus();
    }

    public SystemError(ErrorType errorType, Throwable cause, HttpStatus httpStatus) {
        super(errorType.getMessage(), cause);
        this.errorCode = errorType.getErrorCode();
        this.httpStatus = httpStatus;
    }

    public SystemError(ErrorType errorType, String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = errorType.getErrorCode();
        this.httpStatus = errorType.getHttpStatus();
    }

    public SystemError(ErrorType errorType, String errorMsg, Throwable cause, HttpStatus httpStatus) {
        super(errorMsg, cause);
        this.errorCode = errorType.getErrorCode();
        this.httpStatus = httpStatus;
    }
}
