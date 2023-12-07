package com.ed.linebot.service;

import com.ed.linebot.model.dto.LineMessageDto;
import com.ed.linebot.kafka.producer.LineMessageProducer;
import com.ed.utils.json.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LineMessageService {
    private final LineMessageProducer lineMessageProducer;

    public void processLineMessage(String message) {
        LineMessageDto lineMessageDto = JsonUtils.jsonToObject(message, LineMessageDto.class);
        Optional.ofNullable(lineMessageDto).ifPresent(lineMessageProducer::publishLineMessage);
    }
}
