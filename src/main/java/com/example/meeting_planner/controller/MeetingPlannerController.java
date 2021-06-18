package com.example.meeting_planner.controller;

import com.example.meeting_planner.exception.BadHourException;
import com.example.meeting_planner.exception.NoRoomAvailableException;
import com.example.meeting_planner.service.MeetingPlannerService;
import com.example.meeting_planner.service.dto.MeetingParams;
import com.example.meeting_planner.service.dto.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingPlannerController implements MeetingPlannerApi {

    @Autowired
    private MeetingPlannerService meetingPlannerService;

    @Override
    public Reservation reserveRoom(MeetingParams meetingParams) throws NoRoomAvailableException, BadHourException {
        return meetingPlannerService.getRightRoom(meetingParams);
    }
}
