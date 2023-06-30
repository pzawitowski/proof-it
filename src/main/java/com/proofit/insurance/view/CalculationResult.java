package com.proofit.insurance.view;

import java.math.BigDecimal;
import java.util.List;

public class CalculationResult {
    private Attributes attributes;
    private List<Risk> risks;
    private BigDecimal sumInsured;
    private String coverageType;

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public List<Risk> getRisks() {
        return risks;
    }

    public void setRisks(List<Risk> risks) {
        this.risks = risks;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public BigDecimal getPremium() {
        return risks.stream().
                map(Risk::getPremium).
                reduce(new BigDecimal("0.00"), BigDecimal::add);
    }


    public String getCoverageType() {
        return coverageType;
    }

    public void setCoverageType(String coverageType) {
        this.coverageType = coverageType;
    }
}
