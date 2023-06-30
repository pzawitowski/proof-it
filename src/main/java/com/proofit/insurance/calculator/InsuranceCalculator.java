package com.proofit.insurance.calculator;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.InsuredObjectCalculationResult;

import java.util.List;

public interface InsuranceCalculator {
    List<InsuredObjectCalculationResult> calculate(List<InsuredObject> insuredObjects);
}
