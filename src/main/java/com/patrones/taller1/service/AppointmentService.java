package com.patrones.taller1.service;

import com.patrones.taller1.entity.Appointment;
import com.patrones.taller1.service.filesystem.FileSystemService;
import com.patrones.taller1.service.io.IOType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.patrones.taller1.service.io.IOType.JSON;

@Service
public class AppointmentService {

    @Autowired
    private FileSystemService fileSystemService;

    private static final IOType SAVE_TYPE = JSON;

    public Iterable<Appointment> search(String date){
        return fileSystemService.search(date, SAVE_TYPE);
    }

    public void add(Appointment appointment){
        this.fileSystemService.save(appointment, SAVE_TYPE);
    }
}
