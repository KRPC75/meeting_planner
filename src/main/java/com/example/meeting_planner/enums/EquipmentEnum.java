package com.example.meeting_planner.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public enum EquipmentEnum {
    PIEUVRE, ECRAN, WEBCAM, TABLEAU;

    public static List<EquipmentEnum> getListFromString(final String list) {
        return list == null ? Collections.emptyList() :
                Arrays.stream(list.split(","))
                        .map(EquipmentEnum::valueOf)
                        .collect(Collectors.toList());
    }

    public static String getStringFromList(final List<EquipmentEnum> list) {
        return list.stream()
                .map(Enum::toString)
                .collect(Collectors.joining(","));
    }
}
