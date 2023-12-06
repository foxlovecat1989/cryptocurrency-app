package com.ed.linebot.service;

import com.ed.linebot.domain.LineMessage;
import com.ed.linebot.producer.LineMessageProducer;
import com.ed.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LineMessageService {
    private final LineMessageProducer lineMessageProducer;

    public void processLineMessage(String message) {
        JsonUtils.objectFromJson(message, LineMessage.class).ifPresent(lineMessageProducer::publishLineMessage);
    }
}
