package com.example.meeting_planner.repository;

import com.example.meeting_planner.repository.model.PlanningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlanningRepository extends JpaRepository<PlanningEntity, Long> {
    List<PlanningEntity> findByDate(final LocalDate date);

    PlanningEntity findByDateAndHour(final LocalDate date, final Integer hour);
}
