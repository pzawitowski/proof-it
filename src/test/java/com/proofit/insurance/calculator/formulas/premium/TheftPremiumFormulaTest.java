package com.proofit.insurance.calculator.formulas.premium;

import com.proofit.insurance.calculator.BaseRiskTypes;
import com.proofit.insurance.calculator.Formula;
import com.proofit.insurance.calculator.formulas.BaseFormulaTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class TheftPremiumFormulaTest extends BaseFormulaTest {
    protected TheftPremiumFormulaTest() throws Exception {
    }

    @ParameterizedTest
    @CsvSource({"0,15.00",
                "1,17.00",
                "2,18.00"})
    void calculateFormula_whenActualObjectProvided_shouldReturnTheftPremium(Integer objectIndex, BigDecimal expectedPremium) {
        // given
        Formula calculationFormula = groovyFormulasSupplier.getPremiumFormula(BaseRiskTypes.THEFT).orElseThrow();

        // when
        BigDecimal premium = calculationFormula.calculate(insuredObjects.get(objectIndex));

        // then
        assertThat(premium).isEqualTo(expectedPremium);
    }
}
