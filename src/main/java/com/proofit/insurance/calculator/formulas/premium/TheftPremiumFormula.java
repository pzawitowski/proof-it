package com.proofit.insurance.calculator.formulas.premium;

import com.proofit.insurance.calculator.RiskType;
import com.proofit.insurance.calculator.formulas.AbstractPremiumCalculationFormula;
import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.supplier.CalculationDataSupplier;
import com.proofit.insurance.supplier.DateSupplier;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_EVEN;

public class TheftPremiumFormula extends AbstractPremiumCalculationFormula {
    public TheftPremiumFormula(CalculationDataSupplier dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    public TheftPremiumFormula(CalculationDataSupplier dataSupplier, DateSupplier dateSupplier) {
        this.dataSupplier = dataSupplier;
        this.dateSupplier = dateSupplier;
    }

    @Override
    public RiskType getRiskType() {
        return RiskType.THEFT;
    }

    @Override
    public BigDecimal calculate(InsuredObject actualObject) {
        BigDecimal sumInsuredFactor = sumInsuredFactor(actualObject);
        BigDecimal riskBasePremium = riskBasePremium();

        return riskBasePremium.multiply(sumInsuredFactor).setScale(ROUND_SCALE, HALF_EVEN);
    }
}
