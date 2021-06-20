package com.example.meeting_planner.controller;

import com.example.meeting_planner.repository.model.PlanningEntity;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@RequestMapping(value = "/planning")
public interface PlanningApi {
    @ResponseBody
    @GetMapping(produces = "application/json")
    List<PlanningEntity> getPlanningOfTheDay(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);
}

