package com.proofit.insurance.calculator;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskCalculationResult;
import com.proofit.insurance.supplier.FormulasSupplier;
import com.proofit.insurance.validator.Validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class InsuranceCalculator implements Calculator {
    private final FormulasSupplier formulasSupplier;
    private final Validator validator;


    public InsuranceCalculator(FormulasSupplier formulasSupplier,
                               Validator validator) {
        this.formulasSupplier = formulasSupplier;
        this.validator = validator;
    }

    @Override
    public List<RiskCalculationResult> calculate(InsuredObject insuredObject) {
        List<RiskCalculationResult> riskCalculationResults = new ArrayList<>();

        validator.validate(insuredObject);

        for (String riskType : insuredObject.getRisks()) {
            BigDecimal premium = formulasSupplier.getPremiumFormula(riskType)
                    .orElseThrow(() -> new IllegalArgumentException(format("Invalid risk type %s", riskType)))
                    .calculate(insuredObject);
            BigDecimal sumInsured = formulasSupplier.getSumInsuredFormula(riskType)
                    .orElseThrow(() -> new IllegalArgumentException(format("Invalid risk type %s", riskType)))
                    .calculate(insuredObject);

            riskCalculationResults.add(new RiskCalculationResult(riskType, sumInsured, premium));
        }

        return riskCalculationResults;
    }

}
