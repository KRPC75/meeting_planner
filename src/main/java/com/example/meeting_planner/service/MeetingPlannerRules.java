package com.example.meeting_planner.service;

import com.example.meeting_planner.enums.EquipmentEnum;
import com.example.meeting_planner.enums.MeetingTypeEnum;
import com.example.meeting_planner.exception.NoRoomAvailableException;
import com.example.meeting_planner.repository.model.PlanningEntity;
import com.example.meeting_planner.repository.model.RoomEntity;
import com.example.meeting_planner.service.dto.MeetingParams;
import com.example.meeting_planner.service.dto.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MeetingPlannerRules {

    public List<RoomEntity> getAdaptedRooms(final MeetingParams meetingParams, final List<RoomEntity> rooms) {
        return rooms.stream()
                .filter(roomEntity ->
                        haveCapacity(roomEntity.getCapacity(), meetingParams.getPeopleNumber()) &&
                                haveEquipment(EquipmentEnum.getListFromString(roomEntity.getEquipments()), meetingParams.getMeetingType()))
                .collect(Collectors.toList());
    }

    public Reservation getMostEfficientRoom(final List<PlanningEntity> planningOfTheDay, List<RoomEntity> rooms, MeetingParams meetingParams) throws NoRoomAvailableException {
        final Integer hour = meetingParams.getHour();

        List<Reservation> reservationsAvailable = new ArrayList<>();
        int difference = 0;
        while (reservationsAvailable.isEmpty() && (hour + difference <= 20 && hour - difference >= 8)) {
            reservationsAvailable = getRoomsWithDifferencesHours(planningOfTheDay, rooms, meetingParams, difference++);
        }
        return getMostEfficientReservation(reservationsAvailable);
    }

    private Reservation getMostEfficientReservation(final List<Reservation> reservationList) throws NoRoomAvailableException {
        if (reservationList.isEmpty())
            throw new NoRoomAvailableException();
        else if (reservationList.size() == 1)
            return reservationList.get(0);
        else {
            return reservationList
                    .stream()
                    .min(Comparator.comparing(Reservation::getCapacity))
                    .orElseThrow(NoRoomAvailableException::new);
        }
    }

    private List<Reservation> getRoomsWithDifferencesHours(List<PlanningEntity> planningOfTheDay, List<RoomEntity> rooms, MeetingParams meetingParams, Integer difference) {
        final Integer initalHour = meetingParams.getHour();
        if (difference == 0)
            return getRoomsForHour(planningOfTheDay, rooms, initalHour, meetingParams.getDate());
        else {
            List<Reservation> reservationList = new ArrayList<>();
            if (initalHour - difference >= 8)
                reservationList.addAll(getRoomsForHour(planningOfTheDay, rooms, initalHour - difference, meetingParams.getDate()));
            if (initalHour + difference <= 20)
                reservationList.addAll(getRoomsForHour(planningOfTheDay, rooms, initalHour + difference, meetingParams.getDate()));
            return reservationList;
        }
    }

    List<Reservation> getRoomsForHour(final List<PlanningEntity> planning, final List<RoomEntity> rooms, final Integer hour, final LocalDate date) {
        List<PlanningEntity> planningForTheHour = getPlanningByHour(planning, hour);
        return rooms
                .stream()
                .filter(room -> isRoomFreeForThisHour(room.getName(), planningForTheHour))
                .map(roomEntity -> new Reservation(roomEntity.getName(), hour, date, roomEntity.getCapacity()))
                .collect(Collectors.toList());

    }

    private List<PlanningEntity> getPlanningByHour(final List<PlanningEntity> planning, final Integer hour) {
        return planning
                .stream()
                .filter(planningEntity -> hour.equals(planningEntity.getHour()))
                .collect(Collectors.toList());
    }

    private Boolean isRoomFreeForThisHour(final String roomName, final List<PlanningEntity> planningDay) {
        return planningDay.stream()
                .noneMatch(planningHour -> roomName.equals(planningHour.getRoomName()));
    }

    private Boolean haveCapacity(final Integer capacity, final Integer peopleNumber) {
        return capacity * 0.7 >= peopleNumber;
    }

    private Boolean haveEquipment(final List<EquipmentEnum> equipments, final MeetingTypeEnum meetingType) {
        return equipments.containsAll(meetingType.getEquipmentNeeded());
    }
}
