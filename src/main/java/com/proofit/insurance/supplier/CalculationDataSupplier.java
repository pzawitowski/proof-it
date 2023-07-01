package com.proofit.insurance.supplier;

import com.github.javaparser.quality.NotNull;
import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.calculator.RiskType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CalculationDataSupplier {
    String MAKE = "MAKE";
    String MODEL = "MODEL";
    String VALUE_FROM = "VALUE_FROM";
    String VALUE_TO = "VALUE_TO";
    String FACTOR_MIN = "FACTOR_MIN";
    String FACTOR_MAX = "FACTOR_MAX";
    String RISK_TYPE = "RISK_TYPE";
    String PREMIUM = "PREMIUM";


    List<Map<String, Serializable>> getAgeFactorData();

    List<Map<String, Serializable>> getRiskBasePremiumData();

    List<Map<String, Serializable>> getSumInsuredFactorData();

    List<Map<String, Serializable>> getRiskCountFactorData();

    FactorData getAgeFactorDataByObject(@NotNull InsuredObject object);

    BigDecimal getRiskDataPremiumByRiskType(@NotNull RiskType riskType);

    FactorData getRiskCountFactorData(@NotNull Integer riskCount);

    FactorData getSumInsuredFactorDataBySum(@NotNull BigDecimal riskSumInsured);

}
