package com.proofit.insurance.calculator.formulas.premium;

import com.proofit.insurance.calculator.BaseRiskTypes;
import com.proofit.insurance.calculator.Formula;
import com.proofit.insurance.calculator.formulas.BaseFormulaTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ThirdPartyDamagePremiumFormulaTest extends BaseFormulaTest {

    protected ThirdPartyDamagePremiumFormulaTest() throws Exception {
    }

    @ParameterizedTest
    @CsvSource({"0,12.00",
                "1,14.73",
                "2,14.40"})
    void calculateFormula_whenActualObjectProvided_shouldReturnThirdPartyDamagePremium(Integer objectIndex, BigDecimal expectedPremium) {
        // given insuredObjects and
        Formula calculationFormula = groovyFormulasSupplier.getPremiumFormula(BaseRiskTypes.THIRD_PARTY_DAMAGE).orElseThrow();

        // when
        BigDecimal premium = calculationFormula.calculate(insuredObjects.get(objectIndex));

        // then
        assertThat(premium).describedAs("Premium should be equal to %s", expectedPremium).isEqualTo(expectedPremium);
    }
}
