package com.ed.exception.handler;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception e) {
        Optional<Throwable> causeOpt = Optional.ofNullable(e.getCause());
        String cause = causeOpt.isPresent() ? causeOpt.get().toString() : "";
        log.error("ErrorMsg: {}, Cause: {}", e.getMessage(), cause);

        return createHttpResponse(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    protected ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(
                new HttpResponse(
                        httpStatus.value(),
                        httpStatus,
                        httpStatus.getReasonPhrase().toUpperCase(),
                        message),
                httpStatus
        );
    }
}
