package com.example.meeting_planner.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plannings")
public class PlanningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hour", nullable = false)
    private Integer hour;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "maintenance", nullable = false)
    private boolean maintenance;

    @Column(name = "room_name", nullable = false)
    private String roomName;

}
