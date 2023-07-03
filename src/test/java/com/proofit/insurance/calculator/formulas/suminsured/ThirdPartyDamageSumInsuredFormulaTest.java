package com.proofit.insurance.calculator.formulas.suminsured;

import com.proofit.insurance.calculator.formulas.BaseFormulaTest;
import com.proofit.insurance.calculator.formulas.CalculationFormula;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ThirdPartyDamageSumInsuredFormulaTest extends BaseFormulaTest {
    @ParameterizedTest
    @CsvSource({"0,100.00",
                "1,100.00",
                "2,100.00"})
    void calculateFormula_whenActualObjectProvided_shouldReturnThirdPartySumInsured(Integer objectIndex, BigDecimal expectedPremium) {
        // given
        CalculationFormula calculationFormula = new ThirdPartyDamageSumInsuredFormula();

        // when
        BigDecimal premium = calculationFormula.calculate(getInsuredObjects().get(objectIndex));

        // then
        assertThat(premium).isEqualTo(expectedPremium);
    }
}
