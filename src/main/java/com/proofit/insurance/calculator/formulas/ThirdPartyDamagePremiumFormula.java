package com.proofit.insurance.calculator.formulas;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskType;
import com.proofit.insurance.supplier.CalculationDataSupplier;
import com.proofit.insurance.supplier.DateSupplier;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ThirdPartyDamagePremiumFormula extends AbstractPremiumCalculationFormula {

    public ThirdPartyDamagePremiumFormula(CalculationDataSupplier dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    public ThirdPartyDamagePremiumFormula(CalculationDataSupplier dataSupplier, DateSupplier dateSupplier) {
        this.dataSupplier = dataSupplier;
        this.dateSupplier = dateSupplier;
    }

    @Override
    public RiskType getRiskType() {
        return RiskType.THIRD_PARTY_DAMAGE;
    }

    @Override
    public BigDecimal calculate(InsuredObject actualObject) {
        BigDecimal sumInsuredFactor = sumInsuredFactor(actualObject);
        BigDecimal riskCountFactor = riskCountFactor(actualObject);

        return riskBasePremium().multiply(sumInsuredFactor).multiply(riskCountFactor).setScale(ROUND_SCALE, RoundingMode.HALF_EVEN);
    }
}
