package com.proofit.insurance.model;

import java.math.BigDecimal;

public class RiskCalculationResult {
    private String riskType;
    private BigDecimal sumInsured;
    private BigDecimal premium;

    public RiskCalculationResult(String riskType, BigDecimal sumInsured, BigDecimal premium) {
        this.riskType = riskType;
        this.sumInsured = sumInsured;
        this.premium = premium;
    }

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
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
