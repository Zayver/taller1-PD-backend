package com.patrones.taller1.controller;

import com.patrones.taller1.entity.Appointment;
import com.patrones.taller1.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/search")
    public Iterable<Appointment> search(@RequestParam String date){
        return appointmentService.search(date);
    }


    @PostMapping("/create")
    public void add(@RequestBody Appointment appointment){
        this.appointmentService.add(appointment);
    }

}
