import com.proofit.insurance.calculator.Formula
import com.proofit.insurance.model.InsuredObject

class ThirdPartySumInsuredFormula implements Formula {

    @Override
    String getRiskType() {
        return 'THIRD_PARTY_DAMAGE'
    }

    @Override
    FormulaType getFormulaType() {
        return FormulaType.SUM_INSURED
    }

    @Override
    BigDecimal calculate(InsuredObject insuredObject) {
        return 100.00
    }
}
