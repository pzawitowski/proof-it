package com.proofit.insurance.model;

import com.proofit.insurance.calculator.RiskType;

import java.math.BigDecimal;
import java.util.List;

public class InsuredObject {
    private BigDecimal sumInsured;
    private Integer manufactureYear;
    private String model;
    private String make;
    private String coverage;

    private List<RiskType> risks;

    public InsuredObject() {
    }

    public InsuredObject(String make, String model, String coverage, Integer manufactureYear, BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
        this.manufactureYear = manufactureYear;
        this.model = model;
        this.make = make;
        this.coverage = coverage;
    }

    public InsuredObject(String make, String model, String coverage, Integer manufactureYear, BigDecimal sumInsured, List<RiskType> risks) {
        this.sumInsured = sumInsured;
        this.manufactureYear = manufactureYear;
        this.model = model;
        this.make = make;
        this.coverage = coverage;
        this.risks = risks;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
    }

    public Integer getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(Integer manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getCoverage() {
        return coverage;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public List<RiskType> getRisks() {
        return risks;
    }

    public void setRisks(List<RiskType> risks) {
        this.risks = risks;
    }
}
