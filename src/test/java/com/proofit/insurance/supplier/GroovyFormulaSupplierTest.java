package com.proofit.insurance.supplier;

import com.proofit.insurance.calculator.Formula;
import org.junit.Test;

import static com.proofit.insurance.calculator.BaseRiskTypes.DAMAGE;
import static com.proofit.insurance.calculator.BaseRiskTypes.THEFT;
import static org.assertj.core.api.Assertions.assertThat;

public class GroovyFormulaSupplierTest {
    private String directory = "src/main/groovy";
    private GroovyFormulasSupplier formulasSupplier;
    private GroovyFormulasSupplier testDirectoryFormulaSupplier;

    public GroovyFormulaSupplierTest() throws Exception {
         formulasSupplier = new GroovyFormulasSupplier(directory);
         testDirectoryFormulaSupplier = new GroovyFormulasSupplier("src/test/groovy");

        formulasSupplier.loadFormulas();
        testDirectoryFormulaSupplier.loadFormulas();
    }

    @Test
    public void getPremiumFormulaByRiskType_WhenRiskTypeProvided_shouldReturnFormula() throws Exception {
        // given formulaSupplier

        // when
        Formula formula = formulasSupplier.getPremiumFormula(DAMAGE).orElseThrow();

        // then
        assertThat(formula.getFormulaType()).isEqualTo(Formula.FormulaType.PREMIUM);
    }

    @Test
    public void getSumInsuredFormulaByRiskType_WhenRiskTypeProvided_shouldReturnFormula() throws Exception {
        // given formulaSupplier

        // when
        Formula formula = formulasSupplier.getSumInsuredFormula(THEFT).orElseThrow();

        // then
        assertThat(formula.getFormulaType()).isEqualTo(Formula.FormulaType.SUM_INSURED);
    }

    @Test
    public void getSumInsuredFormulaByRiskType_WhenTestRiskTypeProvided_shouldReturnFormula() throws Exception {
        // given formulaSupplier

        // when
        Formula formula = testDirectoryFormulaSupplier.getSumInsuredFormula("TEST").orElseThrow();

        // then
        assertThat(formula.getFormulaType()).isEqualTo(Formula.FormulaType.SUM_INSURED);
    }

    @Test
    public void getPremiumFormulaByRiskType_WhenTestRiskTypeProvided_shouldReturnFormula() throws Exception {
        // given

        // when
        Formula formula = testDirectoryFormulaSupplier.getPremiumFormula("TEST").orElseThrow();

        // then
        assertThat(formula.getFormulaType()).isEqualTo(Formula.FormulaType.PREMIUM);
    }
}
