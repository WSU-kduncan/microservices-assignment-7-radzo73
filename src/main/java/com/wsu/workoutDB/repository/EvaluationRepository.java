package com.wsu.workoutDB.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wsu.workoutDB.model.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, String> {
    @Query(nativeQuery = true, value = 
        "SELECT e.evaluationID AS evaluationID"
            +", e.runnerID as runnerID"
            +", e.workoutID as workoutID"
            +", e.evaluationDate as evaluationDate"
            +", e.city as city"
            +", e.stateCode as stateCode"
            +", e.startTime as startTime"
            +", e.endTime as endTime"
            +", e.distance as distance"
            +", e.timeOfDay as timeOfDay"
            +", e.averageHeartRate as averageHeartRate"
            +", e.feelScoreRating as feelScoreRating"
            +", e.comments as comments"
        +" FROM Evaluation e"
        +" WHERE (:search IS NULL"
            +" OR (e.evaluationID LIKE %:search%"
            +" OR e.city LIKE %:search%"
            +" OR e.stateCode LIKE %:search%"
            +" OR e.distance LIKE %:search%"
        +"))"
    )


    Page<Object[]> findBySearch(String search, Pageable pageable);
}
