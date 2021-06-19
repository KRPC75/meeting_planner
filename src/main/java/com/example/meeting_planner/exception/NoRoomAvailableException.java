package com.example.meeting_planner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class NoRoomAvailableException extends Exception {

    private static final String MESSAGE = "Aucune salle disponible à ce jour correspondant aux critères d'entrée";

    public NoRoomAvailableException() {
        super(MESSAGE);
    }
}
