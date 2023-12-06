package com.ed.linebot.exception.handler;

import com.ed.exception.handler.CommonExceptionHandler;
import com.ed.linebot.exception.UnknownOriginRequestException;
import com.ed.model.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class LineChannelWebhookExceptionHandler extends CommonExceptionHandler {

    @ExceptionHandler(UnknownOriginRequestException.class)
    public ResponseEntity<HttpResponse> unknownOriginRequestException(UnknownOriginRequestException e) {
        return createHttpResponse(BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<HttpResponse> noSuchAlgorithmException(NoSuchAlgorithmException e) {
        return createHttpResponse(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(InvalidKeyException.class)
    public ResponseEntity<HttpResponse> noSuchAlgorithmException(InvalidKeyException e) {
        return createHttpResponse(INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
