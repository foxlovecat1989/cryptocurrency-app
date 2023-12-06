package com.ed.linebot.controller;

import com.ed.linebot.exception.UnknownOriginRequestException;
import com.ed.linebot.exception.handler.LineChannelWebhookExceptionHandler;
import com.ed.linebot.service.LineMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/line-channel")
public class LineChannelWebHookController extends LineChannelWebhookExceptionHandler {
    @Value("${line.bot.channel-secret}")
    private String lineChannelSecret;
    @Value("${line.bot.algorithm}")
    private String lineAlgorithm;

    private final LineMessageService lineMessageService;


    @PostMapping(value = "receive-message")
    public ResponseEntity<?> receiveMessage(
            @RequestHeader("X-Line-Signature") String xLineSignature,
            @RequestBody String requestBody) throws UnknownOriginRequestException, NoSuchAlgorithmException, InvalidKeyException {
        log.info("receive request from webhook event - xLineSignature: {}, requestBody: {}",
                xLineSignature, requestBody);
        checkRequestFromLine(requestBody, xLineSignature);
        lineMessageService.processLineMessage(requestBody);

        return ResponseEntity.status(201).build();
    }

    private void checkRequestFromLine(String requestBody, String xLineSignature)
            throws UnknownOriginRequestException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec key = new SecretKeySpec(lineChannelSecret.getBytes(), lineAlgorithm);
        Mac mac;
        try {
            mac = Mac.getInstance(lineAlgorithm);
            mac.init(key);
            byte[] bytes = requestBody.getBytes(StandardCharsets.UTF_8);
            String signature = Base64.encodeBase64String(mac.doFinal(bytes));
            boolean isXLineSignaturePresent = Optional.ofNullable(xLineSignature).isPresent();
            boolean isOriginOfLine = isXLineSignaturePresent && signature.equals(xLineSignature);
            if(!isOriginOfLine)
                throw new UnknownOriginRequestException();
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException: CauseBy: {}, ErrorMessage: {}", e.getCause(), e.getMessage());
            throw e;
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException: CauseBy: {}, ErrorMessage: {}", e.getCause(), e.getMessage());
            throw e;
        }
    }
}
