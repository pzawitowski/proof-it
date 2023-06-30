package com.proofit.insurance.model;

import java.math.BigDecimal;

public class RiskCalculationResult {
    public RiskCalculationResult(RiskType riskType) {
        this.riskType = riskType;
    }

    private RiskType riskType;
    private BigDecimal sumInsured;
    private BigDecimal premium;


    public RiskType getRiskType() {
        return riskType;
    }

    public RiskCalculationResult(RiskType riskType, BigDecimal sumInsured, BigDecimal premium) {
        this.riskType = riskType;
        this.sumInsured = sumInsured;
        this.premium = premium;
    }

    public void setRiskType(RiskType riskType) {
        this.riskType = riskType;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public void setPremium(BigDecimal premium) {
        this.premium = premium;
    }
}
