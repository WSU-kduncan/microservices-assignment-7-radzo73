package com.wsu.workoutDB.model;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "Evaluation")
public class Evaluation {
    @Id
    @Column(name = "evaluationID", nullable = false)
    private Integer evaluationID;

    @Column(name = "runnerID", nullable = false)
    private Integer runnerID;

    @Column(name = "workoutID", nullable = false)
    private Integer workoutID;

    @Column(name = "evaluationDate", nullable = false)
    private Date evaluationDate;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "stateCode", nullable = false)
    private String stateCode;

    @Column(name = "startTime", nullable = false)
    private Time startTime;

    @Column(name = "endTime", nullable = false)
    private Time endTime;

    @Column(name = "distance", nullable = false)
    private BigDecimal distance;

    @Column(name = "timeOfDay", nullable = false)
    private String timeOfDay;

    @Column(name = "averageHeartRate")
    private byte averageHeartRate;

    @Column(name = "feelScoreRating")
    private byte feelScoreRating;

    @Column(name = "comments")
    private String comments;
}