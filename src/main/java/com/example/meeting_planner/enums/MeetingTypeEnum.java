package com.example.meeting_planner.enums;

import java.util.Collections;
import java.util.List;

import static com.example.meeting_planner.enums.EquipmentEnum.ECRAN;
import static com.example.meeting_planner.enums.EquipmentEnum.PIEUVRE;
import static com.example.meeting_planner.enums.EquipmentEnum.TABLEAU;
import static com.example.meeting_planner.enums.EquipmentEnum.WEBCAM;

public enum MeetingTypeEnum {

    VC(List.of(PIEUVRE, WEBCAM, ECRAN)),
    SPEC(List.of(TABLEAU)),
    RS(Collections.emptyList()),
    RC(List.of(TABLEAU, ECRAN, PIEUVRE));

    private final List<EquipmentEnum> equipmentNeeded;

    MeetingTypeEnum(List<EquipmentEnum> equipmentNeeded) {
        this.equipmentNeeded = equipmentNeeded;
    }

    public List<EquipmentEnum> getEquipmentNeeded() {
        return equipmentNeeded;
    }
}
