package com.wsu.workoutDB.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wsu.workoutDB.dto.EvaluationDTO;
import com.wsu.workoutDB.exception.DatabaseErrorException;
import com.wsu.workoutDB.exception.InvalidRequestException;
import com.wsu.workoutDB.model.Evaluation;
import com.wsu.workoutDB.repository.EvaluationRepository;
import static com.wsu.workoutDB.utilities.CommonUtils.sort;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Time;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor

public class EvalutionService {
    private final EvaluationRepository evaluationRepository;

    public Page<EvaluationDTO> get(String search, String sortField, String sortOrder, Integer page, Integer rpp){
        try {
            
            Page<Object[]> evaluations = evaluationRepository.findBySearch(
                search
                ,PageRequest.of(
                    page-1
                    ,rpp
                    ,sort(sortField, sortOrder)
                )
            );
            return evaluations.map(evaluation -> EvaluationDTO.builder()
                .evaluationID((Integer)evaluation[0])
                .runnerID((Integer)evaluation[1])
                .workoutID((Integer)evaluation[2])
                .evaluationDate((Date)evaluation[3])
                .city((String)evaluation[4])
                .stateCode((String)evaluation[5])
                .startTime((Time)evaluation[6])
                .endTime((Time)evaluation[7])
                .distance((BigDecimal)evaluation[8])
                .timeOfDay((String)evaluation[9])
                .averageHeartRate((Byte)evaluation[10])
                .feelScoreRating((Byte)evaluation[11])
                .comments((String)evaluation[12])
            .build());

        } catch (Exception e) {
            log.error(
                "Failed to retrieve evaluations. search:{}, sortField:{}, sortOrder:{}, page:{}, rpp:{}, Exception:{}"
                ,search
                ,sortField
                ,sortOrder
                ,page
                ,rpp
                ,e
            );
            throw new DatabaseErrorException("Failed to retrieve evaluations", e);
        }
    }

    public EvaluationDTO save(EvaluationDTO evaluationDTO) {
        if (evaluationRepository.existsById(String.valueOf(evaluationDTO.getEvaluationID()))) {
            throw new InvalidRequestException("Evaluation already exists with this ID.");
        }
        try {
            Evaluation evaluation = mapToEntity(evaluationDTO);
            evaluation.setEvaluationID(evaluationDTO.getEvaluationID());
            return mapToDto(evaluationRepository.save(evaluation));
        } catch (Exception e) {
            log.error(
                "Failed to add evaluation. evaluation ID:{}, Exception:{}"
                ,evaluationDTO.getEvaluationID()
                ,e
            );
            throw new DatabaseErrorException("Failed to add evaluation.", e);
        }
    }

    public Evaluation update(int evaluationID, EvaluationDTO EvaluationDTO) {
        if (!evaluationRepository.existsById(String.valueOf(evaluationID))) {
            throw new InvalidRequestException("Invalid evaluation ID.");
        }
        try {
            Evaluation evaluation = mapToEntity(EvaluationDTO);
            evaluation.setEvaluationID(evaluationID);
            return evaluationRepository.save(evaluation);
        } catch (Exception e) {
            log.error(
                "Failed to update evaluation, evaluationID:{}. Exception:"
                ,evaluationID
                ,e
            );
            throw new DatabaseErrorException(
                "Failed to update evaluation"
                ,e
            );
        }
    }

    public void delete(int evaluationID) {
        if (!evaluationRepository.existsById(Integer.toString(evaluationID))) {
            throw new InvalidRequestException("Invalid evaluation ID.");
        }
        try {
            evaluationRepository.deleteById(Integer.toString(evaluationID));
        } catch (Exception e) {
            log.error(
                "Failed to delete evaluation, evalutationID:{}. Exception:{}"
                ,evaluationID
                ,e
            );
            throw new DatabaseErrorException(
                "Failed to delete evaluation."
                ,e
            );
        }
    }

    private Evaluation mapToEntity(EvaluationDTO evaluationDTO) {
        Evaluation evaluation = Evaluation.builder()
            .evaluationID(evaluationDTO.getEvaluationID())
            .runnerID(evaluationDTO.getRunnerID())
            .workoutID(evaluationDTO.getWorkoutID())
            .city(evaluationDTO.getCity())
            .stateCode(evaluationDTO.getStateCode())
            .startTime(evaluationDTO.getStartTime())
            .endTime(evaluationDTO.getEndTime())
            .distance(evaluationDTO.getDistance())
            .timeOfDay(evaluationDTO.getTimeOfDay())
            .averageHeartRate(evaluationDTO.getAverageHeartRate())
            .feelScoreRating(evaluationDTO.getFeelScoreRating())
            .comments(evaluationDTO.getComments())
        .build();
        
        return evaluation;
    }

    private EvaluationDTO mapToDto(Evaluation evaluation) {
        return  evaluation != null ? EvaluationDTO.builder()
            .evaluationID(evaluation.getEvaluationID())
            .runnerID(evaluation.getRunnerID())
            .workoutID(evaluation.getWorkoutID())
            .city(evaluation.getCity())
            .stateCode(evaluation.getStateCode())
            .startTime(evaluation.getStartTime())
            .endTime(evaluation.getEndTime())
            .distance(evaluation.getDistance())
            .timeOfDay(evaluation.getTimeOfDay())
            .averageHeartRate(evaluation.getAverageHeartRate())
            .feelScoreRating(evaluation.getFeelScoreRating())
            .comments(evaluation.getComments())
        .build() : null;
    }

}
