package com.example.meeting_planner.controller;

import com.example.meeting_planner.repository.model.PlanningEntity;
import com.example.meeting_planner.service.PlanningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class PlanningController implements PlanningApi {

    @Autowired
    private PlanningService planningService;

    @Override
    public List<PlanningEntity> getPlanningOfTheDay(LocalDate date) {
        return planningService.getPlanningOfTheDate(date);
    }
}
