package com.proofit.insurance.calculator;

import com.proofit.insurance.calculator.formulas.CalculationFormula;
import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.InsuredObjectCalculationResult;
import com.proofit.insurance.model.RiskCalculationResult;
import com.proofit.insurance.model.RiskType;

import java.math.BigDecimal;
import java.util.*;

public class PremiumInsuranceCalculator implements InsuranceCalculator {

    private Map<RiskType, CalculationFormula> premiumFormulas = new EnumMap<>(RiskType.class);

    public PremiumInsuranceCalculator(List<CalculationFormula> premiumFormulas) {
        premiumFormulas.forEach(formula -> this.premiumFormulas.put(formula.getRiskType(), formula));
    }

    @Override
    public List<InsuredObjectCalculationResult> calculate(List<InsuredObject> insuredObjects) {
        List<InsuredObjectCalculationResult> results = new ArrayList<>();
        for (InsuredObject insuredObject : insuredObjects) {
            results.add(calculateForObject(insuredObject));
        }

        return results;
    }

    private InsuredObjectCalculationResult calculateForObject(InsuredObject insuredObject) {
        InsuredObjectCalculationResult objectCalculationResult = new InsuredObjectCalculationResult();
        for (RiskType riskType : insuredObject.getRisks()) {
            BigDecimal result = premiumFormulas.get(riskType).calculate(insuredObject);

            RiskCalculationResult riskCalculationResult = new RiskCalculationResult(riskType);
            riskCalculationResult.setPremium(result);

            objectCalculationResult.getRiskCalculationResults().add(riskCalculationResult);
        }

        objectCalculationResult.setInsuredObject(insuredObject);
        return objectCalculationResult;
    }
}
