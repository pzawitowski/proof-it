package com.proofit.insurance.calculator.formulas;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ThirdPartyDamagePremiumFormulaTest extends  BasePremiumFormulaTest {

    @ParameterizedTest
    @CsvSource({"0,12.00",
                "2,14.40"})
    void calculateFormula_whenActualObjectProvided_shouldReturnThirdPartyDamagePremium(Integer objectIndex, BigDecimal expectedPremium) {
        // given insuredObjects and
        CalculationFormula calculationFormula = new ThirdPartyDamagePremiumFormula(dataSupplier, dateSupplier);

        // when
        BigDecimal premium = calculationFormula.calculate(insuredObjects.get(objectIndex));

        // then
        assertThat(premium).describedAs("Premium should be equal to %s", expectedPremium).isEqualTo(expectedPremium);
    }
}
