package com.proofit.insurance.supplier;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskType;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static java.lang.String.*;

public class GroovyCalculationDataSupplier implements CalculationDataSupplier {
    private GroovyObject groovyScript;
    private DateSupplier dateSupplier = new DefaultDateSupplier();

    public GroovyCalculationDataSupplier(File groovyFile) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class calcClass;
        try (GroovyClassLoader loader = new GroovyClassLoader(this.getClass().getClassLoader())) {
            calcClass = loader.parseClass(groovyFile);
            groovyScript = (GroovyObject) calcClass.getConstructor().newInstance();
        }

    }

    public GroovyCalculationDataSupplier(File groovyFile, DateSupplier dateSupplier) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this(groovyFile);
        this.dateSupplier = dateSupplier;
    }

    @Override
    public List<Map<String, Serializable>> getAgeFactorData() {
        return (List<Map<String, Serializable>>) groovyScript.invokeMethod("getAgeFactorData", new Object[] {});
    }

    @Override
    public List<Map<String, Serializable>> getRiskBasePremiumData() {
        return (List<Map<String, Serializable>>) groovyScript.invokeMethod("getRiskBasePremiumData", new Object[] {});
    }

    @Override
    public List<Map<String, Serializable>> getSumInsuredFactorData() {
        return (List<Map<String, Serializable>>) groovyScript.invokeMethod("getSumInsuredFactorData", new Object[] {});
    }

    @Override
    public List<Map<String, Serializable>> getRiskCountFactorData() {
        return (List<Map<String, Serializable>>) groovyScript.invokeMethod("getRiskCountFactorData", new Object[] {});
    }

    @Override
    public FactorData getAgeFactorDataByObject(InsuredObject object) {
        Integer objectAge = dateSupplier.getCurrentYear() - object.getManufactureYear();

        Optional<Map<String, Serializable>> factorData = getAgeFactorData().stream()
                .filter(m -> object.getMake().equals(m.get(MAKE)))
                .filter(m -> object.getModel().equals(m.get(MODEL)))
                .filter(isValueInRange(objectAge))
                .findFirst();


        if (factorData.isPresent()) {
            return factorData.map(this::mapToFactorData).get();
        }

        factorData = getAgeFactorData().stream()
                .filter(m -> object.getMake().equals(m.get(MAKE)))
                .filter(m -> m.get(MODEL) == null)
                .filter(isValueInRange(objectAge))
                .findFirst();

        if (factorData.isPresent()) {
            return factorData.map(this::mapToFactorData).get();
        }

        factorData = getAgeFactorData().stream()
                .filter(m -> m.get(MAKE) == null && m.get(MODEL) == null)
                .filter(isValueInRange(objectAge))
                .findFirst();

        return factorData.map(this::mapToFactorData).orElseThrow(() -> new IllegalArgumentException("No matching age factor found"));
    }

    private FactorData mapToFactorData(Map<String, Serializable> m) {
        return new FactorData(getBigDecimal(m, FACTOR_MIN), getBigDecimal(m, FACTOR_MAX), getBigDecimal(m, VALUE_FROM), getBigDecimal(m, VALUE_TO));
    }

    private Integer getInteger(Map<String, Serializable> m, String key) {
        return Integer.valueOf(valueOf(m.get(key)));
    }

    private BigDecimal getBigDecimal(Map<String, Serializable> m, String key) {
        return new BigDecimal(valueOf(m.get(key)));
    }

    @Override
    public BigDecimal getRiskDataPremiumByRiskType(RiskType riskType) {
        Serializable riskPremiumValue = getRiskBasePremiumData().stream()
                .filter(m -> m.get(RISK_TYPE).equals(riskType.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Risk premium not found by provided risk type"))
                .get(PREMIUM);

        return new BigDecimal(valueOf(riskPremiumValue));
    }

    @Override
    public FactorData getRiskCountFactorData(Integer riskCount) {
        return getRiskCountFactorData().stream()
                .filter(isValueInRange(riskCount))
                .findFirst()
                .map(this::mapToFactorData)
                .orElseThrow(() -> new IllegalArgumentException("Risk count factor not found by provided risk count"));
    }

    @Override
    public FactorData getSumInsuredFactorDataBySum(BigDecimal riskSumInsured) {
        return getSumInsuredFactorData().stream()
                .filter(isValueInRange(riskSumInsured))
                .findFirst()
                .map(this::mapToFactorData)
                .orElseThrow(() -> new IllegalArgumentException("Sum insured factor not found by risk sum insured"));
    }


    private Predicate<Map<String, Serializable>> isValueInRange(Integer value) {
        return m -> getInteger(m, VALUE_FROM) <= value && getInteger(m, VALUE_TO)  >= value;
    }

    private Predicate<Map<String, Serializable>> isValueInRange(BigDecimal value) {
        return m -> getBigDecimal(m, VALUE_FROM).compareTo(value) <= 0  && getBigDecimal(m, VALUE_TO).compareTo(value) >= 0;
    }
}
