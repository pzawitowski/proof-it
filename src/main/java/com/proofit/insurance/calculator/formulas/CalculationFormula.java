package com.proofit.insurance.calculator.formulas;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.calculator.RiskType;

import java.math.BigDecimal;

public interface CalculationFormula {
    int ROUND_SCALE = 2;

    RiskType getRiskType();

    BigDecimal calculate(InsuredObject insuredObject);
}
