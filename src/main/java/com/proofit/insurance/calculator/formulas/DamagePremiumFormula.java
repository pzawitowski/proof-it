package com.proofit.insurance.calculator.formulas;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.calculator.RiskType;
import com.proofit.insurance.supplier.CalculationDataSupplier;
import com.proofit.insurance.supplier.DateSupplier;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DamagePremiumFormula extends AbstractPremiumCalculationFormula {

    public DamagePremiumFormula(CalculationDataSupplier dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    public DamagePremiumFormula(CalculationDataSupplier dataSupplier, DateSupplier dateSupplier) {
        this.dataSupplier = dataSupplier;
        this.dateSupplier = dateSupplier;
    }

    @Override
    public RiskType getRiskType() {
        return RiskType.DAMAGE;
    }

    @Override
    public BigDecimal calculate(InsuredObject actualObject) {
        BigDecimal sumInsuredFactor = sumInsuredFactor(actualObject);
        BigDecimal ageFactor = ageFactor(actualObject);

        return riskBasePremium().multiply(sumInsuredFactor).multiply(ageFactor).setScale(ROUND_SCALE, RoundingMode.HALF_EVEN);
    }
}
