package com.wsu.workoutDB.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wsu.workoutDB.dto.EvaluationDTO;
import com.wsu.workoutDB.dto.ServiceResponseDTO;
import com.wsu.workoutDB.service.EvalutionService;
import com.wsu.workoutDB.utilities.Constants;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/evaluations")
public class EvaluationController {

    private final EvalutionService evaluationService;

    @GetMapping
    public ResponseEntity<ServiceResponseDTO> getAllEvaluations(
        @RequestParam(required = false)
        String search,
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer rpp,
        @RequestParam(required = false, defaultValue = "evaluationID") String sortField,
        @RequestParam(required = false, defaultValue = Constants.DESC) String sortOrder)
    {
        Page<EvaluationDTO> evaluationDTOPage = evaluationService.get(search, sortField, sortOrder, page, rpp);
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(
            Map.of(
                Constants.MESSAGE
                ,"Successfully retrieved evaluations."
                ,Constants.PAGE_COUNT
                ,evaluationDTOPage.getTotalPages()
                ,Constants.RESULT_COUNT
                ,evaluationDTOPage.getTotalElements()
            )).data(
                evaluationDTOPage.getContent()).build()
                ,HttpStatus.OK
            );
        
    }

    @PostMapping
    public ResponseEntity<ServiceResponseDTO> save(
        @RequestBody
        @Valid
        EvaluationDTO EvaluationDTO)
    {
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(Map.of(
            Constants.MESSAGE
            ,"Successfully added evaluation."
        ))
            .data(
                evaluationService.save(EvaluationDTO)).build()
                ,HttpStatus.CREATED
        );
    }

    @PutMapping("/{evaluationID}")
    public ResponseEntity<ServiceResponseDTO> update(
        @PathVariable String evaluationID
        ,@RequestBody @Valid EvaluationDTO evaluationDTO
    ){
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(
            Map.of(
                Constants.MESSAGE
                ,"Evaluation updated successfully"
            )).data(
                evaluationService.update(
                    Integer.parseInt(evaluationID)
                    ,evaluationDTO
                )).build()
                , HttpStatus.OK
            );
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<ServiceResponseDTO> deleteTechnician(
        @PathVariable int evaluationID
    ) {
        evaluationService.delete(evaluationID);
        return new ResponseEntity<>(ServiceResponseDTO.builder().meta(
            Map.of(
                Constants.MESSAGE
                ,"Technician deleted successfully"
            )).build()
            ,HttpStatus.OK
        );
    }
}
