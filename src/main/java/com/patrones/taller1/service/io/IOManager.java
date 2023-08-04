package com.patrones.taller1.service.io;

import com.patrones.taller1.entity.Appointment;

public interface IOManager {

    byte[] getBytes(Appointment appointment);

    Appointment readFile(String data);
}
