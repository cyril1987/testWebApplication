package com.example.demo.dao.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonHelper {

    private static final Logger logger = LoggerFactory.getLogger(JsonHelper.class);

    public static String getJsonString(Object object) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            logger.debug("Serialized JSON output");
            return jsonString;
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize object to JSON", e);
        }

        return null;
    }

}
