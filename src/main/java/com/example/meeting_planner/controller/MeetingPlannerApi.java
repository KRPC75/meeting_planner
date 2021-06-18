package com.example.meeting_planner.controller;

import com.example.meeting_planner.exception.BadHourException;
import com.example.meeting_planner.exception.NoRoomAvailableException;
import com.example.meeting_planner.service.dto.MeetingParams;
import com.example.meeting_planner.service.dto.Reservation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/meeting_planner")
public interface MeetingPlannerApi {

    @ResponseBody
    @PostMapping(produces = "application/json", path = "/plan")
    Reservation reserveRoom(@RequestBody MeetingParams meetingParams) throws NoRoomAvailableException, BadHourException;

}
