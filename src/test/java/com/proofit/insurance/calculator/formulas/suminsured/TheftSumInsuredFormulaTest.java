package com.proofit.insurance.calculator.formulas.suminsured;

import com.proofit.insurance.calculator.BaseRiskTypes;
import com.proofit.insurance.calculator.Formula;
import com.proofit.insurance.calculator.formulas.BaseFormulaTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TheftSumInsuredFormulaTest extends BaseFormulaTest {

    protected TheftSumInsuredFormulaTest() throws Exception {
    }

    @ParameterizedTest
    @CsvSource({"0,1000.00",
                "1,225.00",
                "2,100.00"})
    void calculateFormula_whenActualObjectProvided_shouldReturnDamageSumInsured(Integer objectIndex, BigDecimal expectedPremium) {
        // given
        Formula calculationFormula = groovyFormulasSupplier.getSumInsuredFormula(BaseRiskTypes.THEFT).orElseThrow();

        // when
        BigDecimal premium = calculationFormula.calculate(getInsuredObjects().get(objectIndex));

        // then
        assertThat(premium).isEqualTo(expectedPremium);
    }
}
