package com.example.meeting_planner.exception;

public class NoRoomAvailableException extends Exception {
    public NoRoomAvailableException() {
        super("Aucune salle disponible à ce jour");
    }
}
