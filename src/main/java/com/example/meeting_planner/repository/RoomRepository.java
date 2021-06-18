package com.example.meeting_planner.repository;

import com.example.meeting_planner.repository.model.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, String> {
}
