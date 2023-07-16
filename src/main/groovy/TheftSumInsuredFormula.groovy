

import com.proofit.insurance.model.InsuredObject
import com.proofit.insurance.calculator.Formula

class TheftSumInsuredFormula implements Formula {
    @Override
    String getRiskType() {
        return 'THEFT'
    }

    @Override
    FormulaType getFormulaType() {
        return FormulaType.SUM_INSURED
    }

    @Override
    BigDecimal calculate(InsuredObject insuredObject) {
        return insuredObject.getSumInsured().setScale(SCALE)
    }
}
