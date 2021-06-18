package com.example.meeting_planner.service;

import com.example.meeting_planner.exception.BadHourException;
import com.example.meeting_planner.exception.NoRoomAvailableException;
import com.example.meeting_planner.repository.RoomRepository;
import com.example.meeting_planner.repository.model.PlanningEntity;
import com.example.meeting_planner.repository.model.RoomEntity;
import com.example.meeting_planner.service.dto.MeetingParams;
import com.example.meeting_planner.service.dto.Reservation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MeetingPlannerServiceImpl implements MeetingPlannerService {

    @Autowired
    private MeetingPlannerRules meetingPlannerRules;

    @Autowired
    private PlanningService planningService;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Reservation getRightRoom(final MeetingParams meetingParams) throws BadHourException, NoRoomAvailableException {
        checkParams(meetingParams);
        List<RoomEntity> rooms = meetingPlannerRules.getAdaptedRooms(meetingParams, roomRepository.findAll());
        List<PlanningEntity> planningOfTheDay = planningService.getPlanningOfTheDate(meetingParams.getDate());
        var reservation = meetingPlannerRules.getMostEfficientRoom(planningOfTheDay, rooms, meetingParams);
        planningService.addPlanning(PlanningEntity.builder()
                .hour(reservation.getHour())
                .roomName(reservation.getRoomName())
                .date(reservation.getDate())
                .maintenance(false)
                .build());
        return reservation;
    }

    private void checkParams(final MeetingParams meetingParams) throws BadHourException {
        if (meetingParams.getHour() > 20 || meetingParams.getHour() < 8)
            throw new BadHourException(meetingParams.getHour());
        if (meetingParams.getDate() == null)
            meetingParams.setDate(LocalDate.now());
    }


}
