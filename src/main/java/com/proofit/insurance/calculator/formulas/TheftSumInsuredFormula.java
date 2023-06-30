package com.proofit.insurance.calculator.formulas;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskType;

import java.math.BigDecimal;

public class TheftSumInsuredFormula implements CalculationFormula {
    @Override
    public RiskType getRiskType() {
        return RiskType.THEFT;
    }

    @Override
    public BigDecimal calculate(InsuredObject insuredObject) {
        return insuredObject.getSumInsured();
    }
}
