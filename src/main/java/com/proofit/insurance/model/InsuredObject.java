package com.proofit.insurance.model;

import java.math.BigDecimal;
import java.util.List;

public class InsuredObject {
    private BigDecimal sumInsured;
    private Integer manufactureYear;
    private String model;
    private String make;
    private String coverage;

    private List<String> risks;

    private Integer age;

    public InsuredObject() {
    }

    public InsuredObject(String make, String model, String coverage, Integer manufactureYear, BigDecimal sumInsured, List<String> risks, Integer age) {
        this.sumInsured = sumInsured;
        this.manufactureYear = manufactureYear;
        this.model = model;
        this.make = make;
        this.coverage = coverage;
        this.risks = risks;
        this.age = age;
    }

    public InsuredObject(String make, String model, String coverage, Integer manufactureYear, BigDecimal sumInsured) {
        this.sumInsured = sumInsured;
        this.manufactureYear = manufactureYear;
        this.model = model;
        this.make = make;
        this.coverage = coverage;
    }

    public InsuredObject(String make, String model, String coverage, Integer manufactureYear, BigDecimal sumInsured, List<String> risks) {
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

    public List<String> getRisks() {
        return risks;
    }

    public void setRisks(List<String> risks) {
        this.risks = risks;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
