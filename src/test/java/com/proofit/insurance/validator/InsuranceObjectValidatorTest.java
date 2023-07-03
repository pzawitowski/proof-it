package com.proofit.insurance.validator;

import com.proofit.insurance.calculator.RiskType;
import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.supplier.FixedDateSupplier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static java.lang.String.format;

public class InsuranceObjectValidatorTest {

    Validator validator = new InsuredObjectValidator(new FixedDateSupplier("2023-01-01"));


    @Test
    void validate_whenObjectIsValid_NoExceptionShouldBeThrown() {
        // given
        InsuredObject insuredObject = getValidInsuredObject();

        // when
        validator.validate(insuredObject);

        // then
        Assertions.assertThatNoException();
    }

    @Test
    void validate_whenModelIsEmpty_shouldThrowIllegalArgumentException() {
        // given
        InsuredObject insuredObject = getValidInsuredObject();
        insuredObject.setModel(null);

        //when
        Assertions.assertThatThrownBy(() -> validator.validate(insuredObject))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InsuredObjectValidator.BICYCLE_MODEL_IS_MANDATORY);
    }

    @Test
    void validate_whenMakeIsEmpty_shouldThrowIllegalArgumentException() {
        // given
        InsuredObject insuredObject = getValidInsuredObject();
        insuredObject.setMake(null);

        //when
        Assertions.assertThatThrownBy(() -> validator.validate(insuredObject))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InsuredObjectValidator.BICYCLE_MAKE_IS_MANDATORY);
    }


    @Test
    void validate_whenManufactureYearIsEmpty_shouldThrowIllegalArgumentException() {
        // given
        InsuredObject insuredObject = getValidInsuredObject();
        insuredObject.setManufactureYear(null);

        //when
        Assertions.assertThatThrownBy(() -> validator.validate(insuredObject))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InsuredObjectValidator.BICYCLE_MANUFACTURE_YEAR_IS_MANDATORY);
    }

    @Test
    void validate_whenRiskAreEmpty_shouldThrowIllegalArgumentException() {
        // given
        InsuredObject insuredObject = getValidInsuredObject();
        insuredObject.setRisks(null);

        //when
        Assertions.assertThatThrownBy(() -> validator.validate(insuredObject))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InsuredObjectValidator.AT_LEAST_ONE_RISK_SHOULD_BE_PROVIDED);
    }

    @Test
    void validate_whenAgeIsHigherThan10_shouldThrowIllegalArgumentException() {
        // given
        InsuredObject insuredObject = getValidInsuredObject();
        insuredObject.setManufactureYear(2010);

        //when
        Assertions.assertThatThrownBy(() -> validator.validate(insuredObject))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(format(InsuredObjectValidator.BICYCLE_TOO_OLD, InsuredObjectValidator.MAX_AGE));
    }

    @Test
    void validate_whenSumInsuredIsHigherThan10000_shouldThrowIllegalArgumentException() {
        // given
        InsuredObject insuredObject = getValidInsuredObject();
        insuredObject.setSumInsured(BigDecimal.valueOf(10001.0));

        //when
        Assertions.assertThatThrownBy(() -> validator.validate(insuredObject))
                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(format(InsuredObjectValidator.SUM_INSURED_TOO_HIGH, InsuredObjectValidator.MAX_SUM_INSURED));
    }

    private InsuredObject getValidInsuredObject() {
        return new InsuredObject("Canyon", "Model 1", "STANDARD", 2022, BigDecimal.valueOf(10000.0), List.of(RiskType.DAMAGE));

    }
}
