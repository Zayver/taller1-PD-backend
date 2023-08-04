package com.patrones.taller1.entity;


import lombok.Data;

@Data
public class Appointment {
    private Long cc;
    private String name;
    private String surname;
    private Short age;
    private String date;
    private String time;
}
