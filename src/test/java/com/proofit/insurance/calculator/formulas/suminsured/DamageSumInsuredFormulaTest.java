package com.proofit.insurance.calculator.formulas.suminsured;

import com.proofit.insurance.calculator.formulas.BaseFormulaTest;
import com.proofit.insurance.calculator.formulas.CalculationFormula;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DamageSumInsuredFormulaTest extends BaseFormulaTest {

    @ParameterizedTest
    @CsvSource({"0,500.00",
            "1,112.50",
            "2, 50.00"})
    void calculateFormula_whenActualObjectProvided_shouldReturnDamageSumInsured(Integer objectIndex, BigDecimal expectedSumInsured) {
        // given
        CalculationFormula calculationFormula = new DamageSumInsuredFormula();

        // when
        BigDecimal premium = calculationFormula.calculate(getInsuredObjects().get(objectIndex));

        // then
        assertThat(premium).isEqualTo(expectedSumInsured);
    }
}
