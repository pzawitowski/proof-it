package com.proofit.insurance.calculator.formulas;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.calculator.RiskType;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

public class DamageSumInsuredFormula implements CalculationFormula{
    @Override
    public RiskType getRiskType() {
        return RiskType.DAMAGE;
    }

    @Override
    public BigDecimal calculate(InsuredObject insuredObject) {
        return insuredObject.getSumInsured().divide(BigDecimal.valueOf(2)).setScale(ROUND_SCALE, HALF_EVEN);
    }
}
