package com.proofit.insurance.controller;

import com.proofit.insurance.calculator.Calculator;
import com.proofit.insurance.mapper.BicycleMapper;
import com.proofit.insurance.mapper.ObjectCalculationResultMapper;
import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskCalculationResult;
import com.proofit.insurance.view.Bicycle;
import com.proofit.insurance.view.CalculationRequest;
import com.proofit.insurance.view.CalculationResponse;
import com.proofit.insurance.view.ObjectCalculationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BicyclesInsuranceController {

    @Autowired
    Calculator insuranceCalculator;

    @Autowired
    BicycleMapper bicyclemapper;

    @Autowired
    ObjectCalculationResultMapper objectCalculationResultMapper;

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {
        List<InsuredObject> insuredObjects = request.getBicycles().stream()
                .map(this::convertToInsuredObject)
                .toList();


        List<ObjectCalculationResult> objects = new ArrayList<>();
        for (InsuredObject insuredObject : insuredObjects) {
            List<RiskCalculationResult> calculationResults = insuranceCalculator.calculate(insuredObject);
            objects.add(convertToInsuredCalculationResult(insuredObject, calculationResults));
        }

        return ResponseEntity.ok(new CalculationResponse(objects));
    }

    private InsuredObject convertToInsuredObject(Bicycle bicycle) {
        return bicyclemapper.convertToInsuredObject(bicycle);
    }

    private ObjectCalculationResult convertToInsuredCalculationResult(InsuredObject insuredObject , List<RiskCalculationResult> riskCalculationResults) {
        return objectCalculationResultMapper.convertToCalculationResult(insuredObject, riskCalculationResults);
    }
}
