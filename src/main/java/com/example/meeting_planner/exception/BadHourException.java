package com.example.meeting_planner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadHourException extends Exception {

    private static final String MESSAGE = "L'heure choisi [%d] ne corresponds pas aux heures d'ouverture des salles (8h à 20h)";

    public BadHourException(Integer hour) {
        super(String.format(MESSAGE, hour));
    }
}
