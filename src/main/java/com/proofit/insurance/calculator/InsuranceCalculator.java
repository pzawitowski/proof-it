package com.proofit.insurance.calculator;

import com.proofit.insurance.calculator.formulas.CalculationFormula;
import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskCalculationResult;
import com.proofit.insurance.validator.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class InsuranceCalculator implements Calculator {

    private final Map<RiskType, CalculationFormula> premiumFormulas = new EnumMap<>(RiskType.class);
    private final Map<RiskType, CalculationFormula> sumInsuredFormulas = new EnumMap<>(RiskType.class);
    private final Validator validator;

    public InsuranceCalculator(List<CalculationFormula> premiumFormulas,
                               List<CalculationFormula> sumInsuredFormulas,
                               Validator validator) {
        premiumFormulas.forEach(formula -> this.premiumFormulas.put(formula.getRiskType(), formula));
        sumInsuredFormulas.forEach(formula -> this.sumInsuredFormulas.put(formula.getRiskType(), formula));
        this.validator = validator;
    }

    @Override
    public List<RiskCalculationResult> calculate(InsuredObject insuredObject) {
        List<RiskCalculationResult> riskCalculationResults = new ArrayList<>();

        validator.validate(insuredObject);

        for (RiskType riskType : insuredObject.getRisks()) {
            BigDecimal premium = premiumFormulas.get(riskType).calculate(insuredObject);
            BigDecimal sumInsured = sumInsuredFormulas.get(riskType).calculate(insuredObject);

            riskCalculationResults.add(new RiskCalculationResult(riskType, sumInsured, premium));
        }

        return riskCalculationResults;
    }

}
