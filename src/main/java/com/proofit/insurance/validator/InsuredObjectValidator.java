package com.proofit.insurance.validator;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.supplier.DateSupplier;
import com.proofit.insurance.supplier.DefaultDateSupplier;

import java.math.BigDecimal;

import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

public class InsuredObjectValidator implements Validator {

    public static final Double MAX_SUM_INSURED = 10000.0;
    public static final Integer MAX_AGE = 10;

    public static final String BICYCLE_MAKE_IS_MANDATORY = "Bicycle 'make' is mandatory";
    public static final String BICYCLE_MODEL_IS_MANDATORY = "Bicycle 'model' is mandatory";
    public static final String BICYCLE_MANUFACTURE_YEAR_IS_MANDATORY = "Bicycle 'manufactureYear' is mandatory";
    public static final String BICYCLE_SUM_INSURED_IS_MANDATORY = "Bicycle 'sumInsured' is mandatory";
    public static final String SUM_INSURED_TOO_HIGH = "Sum insured cannot be higher than %.2f";
    public static final String AT_LEAST_ONE_RISK_SHOULD_BE_PROVIDED = "At least one risk should be provided";
    public static final String BICYCLE_TOO_OLD = "Bicycle age cannot be higher than %d";


    private DateSupplier dateSupplier = new DefaultDateSupplier();

    public InsuredObjectValidator() {
    }

    public InsuredObjectValidator(DateSupplier dateSupplier) {
        this.dateSupplier = dateSupplier;
    }

    @Override
    public void validate(InsuredObject insuredObject) {
        if (isEmpty(insuredObject.getMake())) {
            throw new IllegalArgumentException(BICYCLE_MAKE_IS_MANDATORY);
        }

        if (isEmpty(insuredObject.getModel())) {
            throw new IllegalArgumentException(BICYCLE_MODEL_IS_MANDATORY);
        }

        if (isEmpty(insuredObject.getManufactureYear())) {
            throw new IllegalArgumentException(BICYCLE_MANUFACTURE_YEAR_IS_MANDATORY);
        }

        if (isEmpty(insuredObject.getSumInsured())) {
            throw new IllegalArgumentException(BICYCLE_SUM_INSURED_IS_MANDATORY);
        }

        if (insuredObject.getSumInsured().compareTo(BigDecimal.valueOf(MAX_SUM_INSURED)) > 0) {
            throw new IllegalArgumentException(format(SUM_INSURED_TOO_HIGH, MAX_SUM_INSURED));
        }

        if (isEmpty(insuredObject.getRisks())) {
            throw new IllegalArgumentException(AT_LEAST_ONE_RISK_SHOULD_BE_PROVIDED);
        }

        if (dateSupplier.getCurrentYear() - insuredObject.getManufactureYear() > MAX_AGE) {
            throw new IllegalArgumentException(format(BICYCLE_TOO_OLD, MAX_AGE));
        }
    }
}
