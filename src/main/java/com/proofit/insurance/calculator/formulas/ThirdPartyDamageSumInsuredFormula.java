package com.proofit.insurance.calculator.formulas;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskType;

import java.math.BigDecimal;

public class ThirdPartyDamageSumInsuredFormula implements CalculationFormula {
    private static final BigDecimal SUM_INSURED = new BigDecimal("100.00");

    @Override
    public RiskType getRiskType() {
        return RiskType.THIRD_PARTY_DAMAGE;
    }

    @Override
    public BigDecimal calculate(InsuredObject insuredObject) {
        return SUM_INSURED;
    }
}
