package com.ed.linebot.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.linecorp.bot.model.response.BotApiResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineMessageDto {
    @JsonProperty(value = "botApiResponse")
    private BotApiResponse botApiResponse;
    @JsonProperty(value = "replyToken")
    private String replyToken;
    @JsonProperty(value = "replyMessage")
    private String replyMessage;
}