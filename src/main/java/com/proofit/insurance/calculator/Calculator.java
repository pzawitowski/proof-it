package com.proofit.insurance.calculator;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskCalculationResult;

import java.util.List;

public interface Calculator {
    List<RiskCalculationResult> calculate(InsuredObject insuredObject);
}
