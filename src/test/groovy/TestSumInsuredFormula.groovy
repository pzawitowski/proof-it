import com.proofit.insurance.calculator.Formula
import com.proofit.insurance.model.InsuredObject

class TestSumInsuredFormula implements Formula{
    @Override
    String getRiskType() {
        return 'TEST'
    }

    @Override
    FormulaType getFormulaType() {
        return FormulaType.SUM_INSURED
    }

    @Override
    BigDecimal calculate(InsuredObject insuredObject) {
        return 50
    }
}
