package com.example.meeting_planner.service;

import com.example.meeting_planner.exception.BadHourException;
import com.example.meeting_planner.exception.NoRoomAvailableException;
import com.example.meeting_planner.service.dto.MeetingParams;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class MeetingPlannerServiceTest {

    @Autowired
    private MeetingPlannerServiceImpl meetingPlannerService;

    @Test
    public void check_params_test() {
        try {
            meetingPlannerService.getRightRoom(MeetingParams.builder()
                    .hour(2).build());
            fail();
        } catch (BadHourException | NoRoomAvailableException exception) {
            assertThat(exception.getMessage()).isEqualTo("L'heure choisi [2] ne corresponds pas aux heures d'ouverture des salles (8h à 20h)");
        }
    }
}
