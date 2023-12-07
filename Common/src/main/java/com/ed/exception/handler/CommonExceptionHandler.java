package com.ed.exception.handler;

import com.ed.exception.SystemError;
import com.ed.model.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(SystemError.class)
    public ResponseEntity<HttpResponse> handle(SystemError e) {
        Optional<Throwable> causeOpt = Optional.ofNullable(e.getCause());
        String cause = causeOpt.isPresent() ? causeOpt.get().toString() : "";
        showErrorMsg(e, cause);

        return createHttpResponse(e.getHttpStatus(), e.getMessage(), e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> handle(Exception e) {
        Optional<Throwable> causeOpt = Optional.ofNullable(e.getCause());
        String cause = causeOpt.isPresent() ? causeOpt.get().toString() : "";
        showErrorMsg(e, cause);

        return createHttpResponse(INTERNAL_SERVER_ERROR, e.getMessage(), "9999");
    }

    private void showErrorMsg(Exception e, String cause) {
        log.error("CommonExceptionHandler Caught {} - ErrorMsg: {}, Cause By: {}",
                e.getClass().getSimpleName(), e.getMessage(), cause);
    }

    protected ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message, String errorCode) {
        return new ResponseEntity<>(
                new HttpResponse(
                        httpStatus.value(),
                        httpStatus,
                        httpStatus.getReasonPhrase().toUpperCase(),
                        message,
                        errorCode),
                httpStatus
        );
    }
}
