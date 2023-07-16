import com.proofit.insurance.calculator.Formula
import com.proofit.insurance.model.InsuredObject

class TestPremiumFormula implements Formula {
    @Override
    String getRiskType() {
        return 'TEST'
    }

    @Override
    FormulaType getFormulaType() {
        return FormulaType.PREMIUM
    }

    @Override
    BigDecimal calculate(InsuredObject insuredObject) {
        return 100
    }
}
