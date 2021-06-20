package com.example.meeting_planner.service;

import com.example.meeting_planner.enums.MeetingTypeEnum;
import com.example.meeting_planner.exception.NoRoomAvailableException;
import com.example.meeting_planner.repository.model.PlanningEntity;
import com.example.meeting_planner.repository.model.RoomEntity;
import com.example.meeting_planner.service.dto.MeetingParams;
import com.example.meeting_planner.service.dto.Reservation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MeetingPlannerRulesTest {

    @Autowired
    private MeetingPlannerRules meetingPlannerRules;

    @Test
    public void get_adapted_rooms_test() {

        final List<RoomEntity> rooms = buildRoomList();

        final MeetingParams meetingParamsVC = MeetingParams.builder()
                .meetingType(MeetingTypeEnum.VC)
                .peopleNumber(5)
                .build();

        final MeetingParams meetingParamsSPEC = MeetingParams.builder()
                .meetingType(MeetingTypeEnum.SPEC)
                .peopleNumber(10)
                .build();

        final MeetingParams meetingParamsRC = MeetingParams.builder()
                .meetingType(MeetingTypeEnum.RC)
                .peopleNumber(12)
                .build();

        final MeetingParams meetingParamsRS = MeetingParams.builder()
                .meetingType(MeetingTypeEnum.RS)
                .peopleNumber(2)
                .build();

        assertThat(meetingPlannerRules.getAdaptedRooms(meetingParamsRC, rooms).size())
                .isEqualTo(1);
        assertThat(meetingPlannerRules.getAdaptedRooms(meetingParamsSPEC, rooms).size())
                .isEqualTo(1);
        assertThat(meetingPlannerRules.getAdaptedRooms(meetingParamsVC, rooms).size())
                .isEqualTo(2);
        assertThat(meetingPlannerRules.getAdaptedRooms(meetingParamsRS, rooms).size())
                .isEqualTo(5);

    }

    @Test
    public void get_most_efficient_room_test() throws NoRoomAvailableException {
        List<RoomEntity> rooms = buildRoomListForHour();
        List<PlanningEntity> planning = buildPlanningList();
        Reservation reservation = meetingPlannerRules.getMostEfficientRoom(planning, rooms, MeetingParams.builder().hour(11).date(LocalDate.now()).build());
        assertThat(reservation)
                .hasFieldOrPropertyWithValue("roomName", "First")
                .hasFieldOrPropertyWithValue("hour", 10)
                .hasFieldOrPropertyWithValue("date", LocalDate.now())
                .hasFieldOrPropertyWithValue("capacity", 3);
    }

    @Test
    public void get_most_efficient_room_with_no_room_test() {
        List<RoomEntity> rooms = buildRoomListForHour();
        List<PlanningEntity> planning = buildPlanningList();
        try {
            meetingPlannerRules.getMostEfficientRoom(planning, new ArrayList<>(), MeetingParams.builder().hour(11).date(LocalDate.now()).build());
            Assertions.fail();
        } catch (NoRoomAvailableException exception) {
            assertThat(exception.getMessage()).isEqualTo("Aucune salle disponible à ce jour correspondant aux critères d'entrée");
        }
    }

    @Test
    public void get_room_for_hour_test() {
        List<RoomEntity> rooms = buildRoomListForHour();
        List<Reservation> roomsAtTwelve = meetingPlannerRules.getRoomsForHour(buildPlanningList(), rooms, 12, LocalDate.now());
        List<Reservation> roomsAtEleven = meetingPlannerRules.getRoomsForHour(buildPlanningList(), rooms, 11, LocalDate.now());
        List<Reservation> roomsAtTen = meetingPlannerRules.getRoomsForHour(buildPlanningList(), rooms, 10, LocalDate.now());
        assertThat(roomsAtTwelve.get(0))
                .hasFieldOrPropertyWithValue("roomName", "Second")
                .hasFieldOrPropertyWithValue("hour", 12)
                .hasFieldOrPropertyWithValue("capacity", 5);
        assertThat(roomsAtEleven).isEmpty();
        assertThat(roomsAtTen.get(0))
                .hasFieldOrPropertyWithValue("roomName", "First")
                .hasFieldOrPropertyWithValue("hour", 10)
                .hasFieldOrPropertyWithValue("capacity", 3);

    }

    private List<RoomEntity> buildRoomList() {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        roomEntityList.add(RoomEntity.builder()
                .name("First")
                .capacity(3)
                .build());
        roomEntityList.add(RoomEntity.builder()
                .name("Second")
                .capacity(10)
                .equipments("TABLEAU")
                .build());
        roomEntityList.add(RoomEntity.builder()
                .name("Third")
                .capacity(20)
                .equipments("PIEUVRE,ECRAN,TABLEAU,WEBCAM")
                .build());
        roomEntityList.add(RoomEntity.builder()
                .name("Fourth")
                .capacity(8)
                .equipments("PIEUVRE,ECRAN")
                .build());
        roomEntityList.add(RoomEntity.builder()
                .name("Fifth")
                .capacity(10)
                .equipments("PIEUVRE,ECRAN,WEBCAM")
                .build());

        return roomEntityList;
    }

    private List<RoomEntity> buildRoomListForHour() {
        List<RoomEntity> roomEntityList = new ArrayList<>();
        roomEntityList.add(RoomEntity.builder()
                .name("First")
                .capacity(3)
                .build());
        roomEntityList.add(RoomEntity.builder()
                .name("Second")
                .capacity(5)
                .build());
        return roomEntityList;
    }

    private List<PlanningEntity> buildPlanningList() {
        List<PlanningEntity> planningEntities = new ArrayList<>();
        planningEntities.add(PlanningEntity.builder()
                .date(LocalDate.now())
                .hour(11)
                .roomName("First")
                .maintenance(false)
                .build());
        planningEntities.add(PlanningEntity.builder()
                .date(LocalDate.now())
                .hour(12)
                .roomName("First")
                .maintenance(true)
                .build());
        planningEntities.add(PlanningEntity.builder()
                .date(LocalDate.now())
                .hour(10)
                .roomName("Second")
                .maintenance(false)
                .build());
        planningEntities.add(PlanningEntity.builder()
                .date(LocalDate.now())
                .hour(11)
                .roomName("Second")
                .maintenance(true)
                .build());
        return planningEntities;
    }
}
