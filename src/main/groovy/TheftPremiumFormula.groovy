

import com.proofit.insurance.model.InsuredObject
import com.proofit.insurance.calculator.Formula

import java.math.RoundingMode


class TheftPremiumFormula extends Script implements Formula {
    public String RISK_TYPE = 'THEFT'


    @Override
    String getRiskType() {
        return RISK_TYPE
    }

    @Override
    FormulaType getFormulaType() {
        return FormulaType.PREMIUM
    }

    @Override
    BigDecimal calculate(InsuredObject insuredObject) {
        BaseScript baseScript = new BaseScript()
        CalculationHelper calculationHelper = new CalculationHelper(baseScript)

        def riskBasePremium = baseScript.getRiskBasePremiumData()
                .find({ it.RISK_TYPE == RISK_TYPE }).PREMIUM
        def sumInsuredFactor = calculationHelper.calculateSumInsuredFactor(insuredObject);

        return (riskBasePremium * sumInsuredFactor).setScale(SCALE, RoundingMode.HALF_EVEN)

    }

    @Override
    Object run() {
        return null
    }
}
