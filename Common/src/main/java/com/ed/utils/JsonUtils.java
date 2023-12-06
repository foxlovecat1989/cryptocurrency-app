package com.ed.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Optional<byte[]> bytesFromObject(Object object) {
        try {
            if (Objects.isNull(object)) {
                return Optional.empty();
            }

            return Optional.of(MAPPER.writeValueAsBytes(object));
        } catch (JsonProcessingException e) {
            log.error("Unable to serialize to json: {}, Error Message: {}", object, e.getMessage());

            return Optional.empty();
        }
    }

    public static <T> Optional<T> objectFromJson(String json, Class<T> klass) {
        Optional<T> objectOpt;
        try {
            MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            objectOpt = Optional.of(MAPPER.readValue(json, klass));
        } catch (RuntimeException e) {
            log.error("Runtime exception during deserializing {} from {}",
                    klass.getSimpleName(),
                    StringUtils.abbreviate(json, 80));

            throw e;
        } catch (Exception e) {
            log.error("Exception during deserializing {} from {}",
                    klass.getSimpleName(),
                    StringUtils.abbreviate(json, 80));

            return Optional.empty();
        }


        return objectOpt;
    }

    public static <T> Optional<String> stringJsonFromObject(T obejct) {
        Optional<String> stringJSONOpt;
        try {
            stringJSONOpt = Optional.ofNullable(MAPPER.writeValueAsString(obejct));
        } catch (JsonProcessingException e) {
            log.error("Exception during deserializing JSON from {}", obejct.toString());

            return Optional.empty();
        }

        return stringJSONOpt;
    }
}