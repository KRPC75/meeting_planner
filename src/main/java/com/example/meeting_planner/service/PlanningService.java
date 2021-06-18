package com.example.meeting_planner.service;

import com.example.meeting_planner.repository.model.PlanningEntity;

import java.time.LocalDate;
import java.util.List;

public interface PlanningService {
    List<PlanningEntity> getPlanningOfTheDate(final LocalDate day);

    void addPlanning(final PlanningEntity planningEntity);
}
