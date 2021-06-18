package com.example.meeting_planner.service.dto;

import com.example.meeting_planner.enums.MeetingTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MeetingParams {
    private Integer peopleNumber;
    private MeetingTypeEnum meetingType;
    private LocalDate date;
    private Integer hour;
}
