package com.example.meeting_planner.service.dto;

import com.example.meeting_planner.enums.MeetingTypeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class MeetingParams {
    private Integer peopleNumber;
    private MeetingTypeEnum meetingType;
    private LocalDate date;
    private Integer hour;
}
