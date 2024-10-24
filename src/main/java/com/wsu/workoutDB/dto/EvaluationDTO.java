package com.wsu.workoutDB.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class EvaluationDTO {
    @NotNull(message = "Evaluation ID must not be null or blank")
    private Integer evaluationID;

    @NotNull(message = "Runner ID must not be null or blank")
    private Integer runnerID;

    @NotNull(message = "Workout ID must not be null or blank")
    private Integer workoutID;

    @NotNull(message = "Evaluation Date must not be null or blank")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a")
    private Date evaluationDate;

    @NotBlank(message = "City must not be null or blank")
    @Size(max = 25)
    private String city;

    @NotBlank(message = "State code must not be null or blank")
    @Size(max = 2)
    private String stateCode;

    @NotNull(message = "Start Time must not be null or blank")
    @JsonFormat(pattern = "hh:mm:ss")
    private Time startTime;

    @NotNull(message = "End Time must not be null or blank")
    @JsonFormat(pattern = "hh:mm:ss")
    private Time endTime;

    
    @NotNull(message = "Distance must not be null or blank")
    private BigDecimal distance;

    @NotBlank(message = "Time Of Day must not be null or blank")
    @Size(max = 1)
    private String timeOfDay;

    // @Size(max = 3)
    private Byte averageHeartRate;

    // @Size(max = 2)
    private Byte feelScoreRating;

    @Size(max = 2000)
    private String comments;
}
