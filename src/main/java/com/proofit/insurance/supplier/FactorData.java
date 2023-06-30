package com.proofit.insurance.supplier;

import java.math.BigDecimal;

public class FactorData {
    private BigDecimal factorMin;
    private BigDecimal factorMax;

    private BigDecimal valueFrom;
    private BigDecimal valueTo;

    public FactorData() {
    }

    public FactorData(BigDecimal factorMin, BigDecimal factorMax, BigDecimal valueFrom, BigDecimal valueTo) {
        this.factorMin = factorMin;
        this.factorMax = factorMax;
        this.valueFrom = valueFrom;
        this.valueTo = valueTo;
    }

    public BigDecimal getFactorMin() {
        return factorMin;
    }

    public void setFactorMin(BigDecimal factorMin) {
        this.factorMin = factorMin;
    }

    public BigDecimal getFactorMax() {
        return factorMax;
    }

    public void setFactorMax(BigDecimal factorMax) {
        this.factorMax = factorMax;
    }

    public BigDecimal getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(BigDecimal valueFrom) {
        this.valueFrom = valueFrom;
    }

    public BigDecimal getValueTo() {
        return valueTo;
    }

    public void setValueTo(BigDecimal valueTo) {
        this.valueTo = valueTo;
    }
}
