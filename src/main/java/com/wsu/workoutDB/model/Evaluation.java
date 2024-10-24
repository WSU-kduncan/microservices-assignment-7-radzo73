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
@Builder
@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @Column(name = "evaluation_id", nullable = false)
    private Integer evaluationID;

    @Column(name = "runner_id", nullable = false)
    private Integer runnerID;

    @Column(name = "workout_id", nullable = false)
    private Integer workoutID;

    @Column(name = "evaluation_date", nullable = false)
    private Date evaluationDate;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state_code", nullable = false)
    private String stateCode;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    @Column(name = "distance", nullable = false)
    private BigDecimal distance;

    @Column(name = "time_of_day", nullable = false)
    private Character timeOfDay;

    @Column(name = "average_heart_rate")
    private byte averageHeartRate;

    @Column(name = "feel_score_rating")
    private byte feelScoreRating;

    @Column(name = "comments")
    private String comments;
}