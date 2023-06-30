package com.proofit.insurance.controller;

import com.proofit.insurance.calculator.InsuranceCalculator;
import com.proofit.insurance.mapper.BicycleMapper;
import com.proofit.insurance.mapper.ObjectCalculationResultMapper;
import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.InsuredObjectCalculationResult;
import com.proofit.insurance.view.Bicycle;
import com.proofit.insurance.view.CalculationRequest;
import com.proofit.insurance.view.CalculationResponse;
import com.proofit.insurance.view.CalculationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BicyclesInsuranceController {

    @Autowired
    InsuranceCalculator insuranceCalculator;

    @Autowired
    BicycleMapper bicyclemapper;

    @Autowired
    ObjectCalculationResultMapper objectCalculationResultMapper;

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculationResponse> calculate(@RequestBody CalculationRequest request) {

        List<InsuredObject> insuredObjects = request.getBicycles().stream().
                map(this::convertToBicycle)
                .toList();

        List<InsuredObjectCalculationResult> objectCalculationResults = insuranceCalculator.calculate(insuredObjects);

        List<CalculationResult> calculationResults = objectCalculationResults.stream()
                .map(this::convertToInsuredCalculationResult)
                .toList();

        return ResponseEntity.ok(new CalculationResponse(calculationResults));
    }

    private InsuredObject convertToBicycle(Bicycle bicycle) {
        return bicyclemapper.convertToInsuredObject(bicycle);
    }

    private CalculationResult convertToInsuredCalculationResult(InsuredObjectCalculationResult o) {
        return objectCalculationResultMapper.convertToCalculationResult(o);
    }
}
