package com.proofit.insurance.calculator.formulas;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class TheftPremiumFormulaTest extends BasePremiumFormulaTest {
    @ParameterizedTest
    @CsvSource({"0,15.00",
                "2,18.00"})
    void calculateFormula_whenActualObjectProvided_shouldReturnTheftPremium(Integer objectIndex, BigDecimal expectedPremium) {
        // given
        CalculationFormula calculationFormula = new TheftPremiumFormula(dataSupplier, dateSupplier);

        // when
        BigDecimal premium = calculationFormula.calculate(insuredObjects.get(objectIndex));

        // then
        assertThat(premium).isEqualTo(expectedPremium);
    }
}
