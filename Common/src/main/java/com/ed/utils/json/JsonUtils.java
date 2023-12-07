package com.ed.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.CheckForNull;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
public class JsonUtils {
    private final static ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @CheckForNull
    public static <T> byte[] objectToBytes(T object) {
        if (Optional.ofNullable(object).isEmpty()) {
            return null;
        }

        try {
            return MAPPER.writeValueAsString(object).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException objectToBytes, ErrorMessage: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Exception objectToBytes, ErrorMessage: {}", e.getMessage(), e);
            throw e;
        }
    }

    @CheckForNull
    public static <T> T bytesToObject(byte[] bytes, Class<T> destinationClass) {
        if (Optional.ofNullable(bytes).isEmpty()) {
            return null;
        }

        try {
            return MAPPER.readValue(new String(bytes, StandardCharsets.UTF_8), destinationClass);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException when bytesToObject to {}, ErrorMessage: {}",
                    destinationClass, e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Exception when bytesToObject to {}, ErrorMessage: {}",
                    destinationClass, e.getMessage(), e);
            throw e;
        }
    }

    @CheckForNull
    public static <T> T jsonToObject(String json, Class<T> destinationClass) {
        if (Optional.ofNullable(json).isEmpty() || json.isBlank()) {
            return null;
        }

        try {
            return MAPPER.readValue(json, destinationClass);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException when jsonToObject to {}, ErrorMessage: {}",
                   destinationClass, e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Exception when jsonToObject to {}, ErrorMessage: {}",
                    destinationClass, e.getMessage(), e);
            throw e;
        }
    }

    @CheckForNull
    public static <T> String objectToJson(T obejct) {
        if (Optional.ofNullable(obejct).isEmpty()) {
            return null;
        }

        try {
            return MAPPER.writeValueAsString(obejct);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException when objectToJson from {} to JSON, ErrorMessage: {}",
                    obejct, e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Exception when objectToJson from {} to JSON, ErrorMessage: {}",
                    obejct, e.getMessage(), e);
            throw e;
        }
    }
}