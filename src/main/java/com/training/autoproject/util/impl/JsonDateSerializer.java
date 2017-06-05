package com.training.autoproject.util.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Base implementation
 * {@link com.fasterxml.jackson.databind.JsonSerializer}
 *
 * @author Oleh Surkov
 * @version 1.0
 */

@Component
public class JsonDateSerializer extends JsonSerializer<Date> {
    /**
     * Logger for logging class
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    /**
     * implementation method from
     * <p>
     * {@link com.fasterxml.jackson.databind.JsonSerializer}
     *
     * @param date
     * @param jsonGenerator
     * @param serializerProvider
     * @throws IOException
     * @throws JsonProcessingException
     */
    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        String formattedDate = dateFormat.format(date);
        jsonGenerator.writeString(formattedDate);
    }
}
