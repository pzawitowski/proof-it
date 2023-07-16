package com.proofit.insurance.calculator.formulas.suminsured;

import com.proofit.insurance.calculator.BaseRiskTypes;
import com.proofit.insurance.calculator.Formula;
import com.proofit.insurance.calculator.formulas.BaseFormulaTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ThirdPartyDamageSumInsuredFormulaTest extends BaseFormulaTest {
    protected ThirdPartyDamageSumInsuredFormulaTest() throws Exception {
    }

    @ParameterizedTest
    @CsvSource({"0,100.00",
                "1,100.00",
                "2,100.00"})
    void calculateFormula_whenActualObjectProvided_shouldReturnThirdPartySumInsured(Integer objectIndex, BigDecimal expectedPremium) {
        // given
        Formula calculationFormula = groovyFormulasSupplier.getSumInsuredFormula(BaseRiskTypes.THIRD_PARTY_DAMAGE).orElseThrow();

        // when
        BigDecimal premium = calculationFormula.calculate(getInsuredObjects().get(objectIndex));

        // then
        assertThat(premium).isEqualTo(expectedPremium);
    }
}
