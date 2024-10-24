package com.wsu.workoutDB.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wsu.workoutDB.model.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, String> {
    @Query(nativeQuery = true, value = 
        "SELECT e.evaluation_id AS evaluationID"
            +", e.runner_id as runnerID"
            +", e.workout_id as workoutID"
            +", e.evaluation_date as evaluationDate"
            +", e.city as city"
            +", e.state_code as stateCode"
            +", e.start_time as startTime"
            +", e.end_time as endTime"
            +", e.distance as distance"
            +", e.time_of_day as timeOfDay"
            +", e.average_heart_rate as averageHeartRate"
            +", e.feel_score_rating as feelScoreRating"
            +", e.comments as comments"
        +" FROM evaluation e"
        +" WHERE (:search IS NULL"
            +" OR (e.evaluation_id LIKE %:search%"
            +" OR e.city LIKE %:search%"
            +" OR e.state_code LIKE %:search%"
            +" OR e.distance LIKE %:search%"
        +"))"
    )

    Page<Object[]> findBySearch(String search, Pageable pageable);
}
