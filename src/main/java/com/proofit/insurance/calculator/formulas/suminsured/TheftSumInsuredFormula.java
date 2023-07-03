package com.proofit.insurance.calculator.formulas.suminsured;

import com.proofit.insurance.calculator.RiskType;
import com.proofit.insurance.calculator.formulas.CalculationFormula;
import com.proofit.insurance.model.InsuredObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TheftSumInsuredFormula implements CalculationFormula {
    @Override
    public RiskType getRiskType() {
        return RiskType.THEFT;
    }

    @Override
    public BigDecimal calculate(InsuredObject insuredObject) {
        return insuredObject.getSumInsured().setScale(ROUND_SCALE, RoundingMode.HALF_EVEN);
    }
}
