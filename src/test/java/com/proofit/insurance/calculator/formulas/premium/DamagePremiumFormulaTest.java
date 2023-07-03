package com.proofit.insurance.calculator.formulas.premium;

import com.proofit.insurance.calculator.formulas.BaseFormulaTest;
import com.proofit.insurance.calculator.formulas.CalculationFormula;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class DamagePremiumFormulaTest extends BaseFormulaTest {

    @ParameterizedTest
    @CsvSource({"0,4.50",
                "1,5.82",
                "2,8.40"})
    void calculateFormula_whenActualObjectProvided_shouldReturnDamagePremium(Integer objectIndex, BigDecimal expectedPremium) {
        // given
        CalculationFormula calculationFormula = new DamagePremiumFormula(dataSupplier, dateSupplier);

        // when
        BigDecimal premium = calculationFormula.calculate(insuredObjects.get(objectIndex));

        // then
        assertThat(premium).isEqualTo(expectedPremium);
    }
}
