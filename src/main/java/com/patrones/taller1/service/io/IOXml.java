package com.patrones.taller1.service.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.patrones.taller1.entity.Appointment;

public class IOXml implements IOManager {

    private final XmlMapper xmlMapper = new XmlMapper();

    @Override
    public byte[] getBytes(Appointment appointment) {
        return new byte[0];
    }

    @Override
    public Appointment readFile(String data) {
        try {
            return xmlMapper.readValue(data, Appointment.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
