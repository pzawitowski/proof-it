package com.proofit.insurance.model;

import java.util.ArrayList;
import java.util.List;

public class InsuredObjectCalculationResult {
    private InsuredObject insuredObject;

    private List<RiskCalculationResult> riskCalculationResults = new ArrayList<>();

    public InsuredObject getInsuredObject() {
        return insuredObject;
    }

    public void setInsuredObject(InsuredObject insuredObject) {
        this.insuredObject = insuredObject;
    }

    public List<RiskCalculationResult> getRiskCalculationResults() {
        return riskCalculationResults;
    }

    public void setRiskCalculationResults(List<RiskCalculationResult> riskCalculationResults) {
        this.riskCalculationResults = riskCalculationResults;
    }
}
