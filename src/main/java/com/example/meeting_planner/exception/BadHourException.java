package com.example.meeting_planner.exception;

public class BadHourException extends Exception {
    public BadHourException(Integer hour) {
        super(String.format("L'heure choisi [%d] ne corresponds pas aux heures d'ouverture des salles (8h à 20h)", hour));
    }
}
