package com.example.meeting_planner.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Reservation {
    private String roomName;
    private Integer hour;
    private LocalDate date;
    private Integer capacity;

    public Reservation(String roomName, Integer hour, LocalDate date, Integer capacity) {
        this.roomName = roomName;
        this.hour = hour;
        this.date = date;
        this.capacity = capacity;
    }
}
