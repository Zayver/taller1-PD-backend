package com.patrones.taller1.service.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrones.taller1.entity.Appointment;
import org.springframework.stereotype.Component;

@Component
public class IOJson implements IOManager {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] getBytes(Appointment appointment) {
        try {
            return objectMapper.writeValueAsBytes(appointment);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Appointment readFile(String data) {
        try {
            return objectMapper.readValue(data, Appointment.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
