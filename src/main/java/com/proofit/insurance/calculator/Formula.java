package com.proofit.insurance.calculator;

import com.proofit.insurance.model.InsuredObject;

import java.math.BigDecimal;

public interface Formula {

    int SCALE = 2;

    enum FormulaType {
        PREMIUM,
        SUM_INSURED;
    }

    String getRiskType();

    FormulaType getFormulaType();

    BigDecimal calculate(InsuredObject insuredObject);
}