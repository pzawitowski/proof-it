import com.proofit.insurance.calculator.Formula
import com.proofit.insurance.model.InsuredObject

import java.math.RoundingMode

class DamageSumInsuredFormula implements Formula {

    @Override
    String getRiskType() {
        return 'DAMAGE'
    }

    @Override
    FormulaType getFormulaType() {
        return FormulaType.SUM_INSURED
    }

    @Override
    BigDecimal calculate(InsuredObject insuredObject) {
        return (insuredObject.getSumInsured() / SCALE ).setScale(SCALE, RoundingMode.HALF_EVEN)
    }
}


