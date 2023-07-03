package com.proofit.insurance.calculator.formulas.suminsured;

import com.proofit.insurance.calculator.RiskType;
import com.proofit.insurance.calculator.formulas.CalculationFormula;
import com.proofit.insurance.model.InsuredObject;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

public class DamageSumInsuredFormula implements CalculationFormula {
    @Override
    public RiskType getRiskType() {
        return RiskType.DAMAGE;
    }

    @Override
    public BigDecimal calculate(InsuredObject insuredObject) {
        return insuredObject.getSumInsured().divide(BigDecimal.valueOf(2)).setScale(ROUND_SCALE, HALF_EVEN);
    }
}
