package com.proofit.insurance.calculator.formulas;

import java.math.BigDecimal;

public class CalculationData {
    private BigDecimal riskBasePremium;
    private BigDecimal sumInsuredFactor;
    private BigDecimal riskCountFactor;
    private BigDecimal ageCuntFactor;

    public BigDecimal getRiskBasePremium() {
        return riskBasePremium;
    }

    public void setRiskBasePremium(BigDecimal riskBasePremium) {
        this.riskBasePremium = riskBasePremium;
    }

    public BigDecimal getSumInsuredFactor() {
        return sumInsuredFactor;
    }

    public void setSumInsuredFactor(BigDecimal sumInsuredFactor) {
        this.sumInsuredFactor = sumInsuredFactor;
    }

    public BigDecimal getRiskCountFactor() {
        return riskCountFactor;
    }

    public void setRiskCountFactor(BigDecimal riskCountFactor) {
        this.riskCountFactor = riskCountFactor;
    }

    public BigDecimal getAgeCuntFactor() {
        return ageCuntFactor;
    }

    public void setAgeCuntFactor(BigDecimal ageCuntFactor) {
        this.ageCuntFactor = ageCuntFactor;
    }
}
