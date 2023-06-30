package com.proofit.insurance.calculator.formulas;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.supplier.CalculationDataSupplier;
import com.proofit.insurance.supplier.DateSupplier;
import com.proofit.insurance.supplier.DefaultDateSupplier;
import com.proofit.insurance.supplier.FactorData;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static java.math.RoundingMode.HALF_EVEN;

public abstract class AbstractPremiumCalculationFormula implements CalculationFormula {

    protected CalculationDataSupplier dataSupplier;
    protected DateSupplier dateSupplier = new DefaultDateSupplier();

    protected BigDecimal riskBasePremium() {
        return dataSupplier.getRiskDataPremiumByRiskType(getRiskType());
    }

    protected BigDecimal riskCountFactor(InsuredObject actualObject) {
        return dataSupplier.getRiskCountFactorData(actualObject.getRisks().size()).getFactorMin();
    }


    protected BigDecimal sumInsuredFactor(InsuredObject object) {
        BigDecimal ageActual = getObjectAge(object);
        FactorData ageFactorData = dataSupplier.getAgeFactorDataByObject(object);
        BigDecimal ageMin = ageFactorData.getValueFrom();
        BigDecimal ageMax = ageFactorData.getValueTo();

        FactorData factorData = dataSupplier.getSumInsuredFactorDataBySum(object.getSumInsured());

        return factorData.getFactorMax().subtract(factorData.getFactorMax().subtract(factorData.getFactorMin()).multiply(ageMax.subtract(ageActual)).divide(ageMax.subtract(ageMin), ROUND_SCALE, HALF_EVEN));
    }

    protected BigDecimal ageFactor(InsuredObject object) {
        BigDecimal ageActual = getObjectAge(object);
        FactorData factorData = dataSupplier.getAgeFactorDataByObject(object);
        BigDecimal ageMin = factorData.getValueFrom();
        BigDecimal ageMax = factorData.getValueTo();


        return factorData.getFactorMax().subtract(factorData.getFactorMax().subtract(factorData.getFactorMin()).multiply(ageMax.subtract(ageActual).divide(ageMax.subtract(ageMin), ROUND_SCALE, HALF_EVEN)));
    }

    private BigDecimal getObjectAge(InsuredObject object) {
        return valueOf(dateSupplier.getCurrentYear() - object.getManufactureYear());
    }

}
