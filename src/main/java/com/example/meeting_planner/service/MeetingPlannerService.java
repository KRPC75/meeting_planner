package com.example.meeting_planner.service;

import com.example.meeting_planner.exception.BadHourException;
import com.example.meeting_planner.exception.NoRoomAvailableException;
import com.example.meeting_planner.service.dto.MeetingParams;
import com.example.meeting_planner.service.dto.Reservation;

public interface MeetingPlannerService {
    Reservation getRightRoom(MeetingParams meetingParams) throws BadHourException, NoRoomAvailableException;
}
