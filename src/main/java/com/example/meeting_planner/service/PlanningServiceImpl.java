package com.example.meeting_planner.service;

import com.example.meeting_planner.repository.PlanningRepository;
import com.example.meeting_planner.repository.model.PlanningEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlanningServiceImpl implements PlanningService {

    @Autowired
    private PlanningRepository planningRepository;

    @Override
    public List<PlanningEntity> getPlanningOfTheDate(LocalDate day) {
        return planningRepository.findByDateOrderByHour(day == null ? LocalDate.now() : day);
    }

    @Override
    public void addPlanning(PlanningEntity planningEntity) {
        planningRepository.save(planningEntity);
        createMaintenancePlanning(planningEntity);
        planningRepository.flush();
    }

    private void createMaintenancePlanning(PlanningEntity planningEntity) {
        final Integer hour = planningEntity.getHour();
        if (hour - 1 >= 8 && planningRepository.findByDateAndHour(planningEntity.getDate(), hour - 1) == null)
            planningRepository.save(PlanningEntity.builder()
                    .roomName(planningEntity.getRoomName())
                    .hour(hour - 1)
                    .maintenance(true)
                    .date(planningEntity.getDate())
                    .build());
        if (hour + 1 <= 20 && planningRepository.findByDateAndHour(planningEntity.getDate(), hour + 1) == null)
            planningRepository.save(PlanningEntity.builder()
                    .roomName(planningEntity.getRoomName())
                    .hour(hour + 1)
                    .maintenance(true)
                    .date(planningEntity.getDate())
                    .build());
    }

}
