

import com.proofit.insurance.calculator.RiskType
import com.proofit.insurance.model.InsuredObject
import com.proofit.insurance.calculator.Formula

import java.math.RoundingMode

class DamagePremiumFormula extends Script implements Formula {
    public static final String RISK_TYPE = "DAMAGE";



    @Override
    String getRiskType() {
        return RISK_TYPE
    }

    @Override
    FormulaType getFormulaType() {
        return FormulaType.PREMIUM;
    }

    @Override
    BigDecimal calculate(InsuredObject insuredObject) {
        BaseScript baseScript = new BaseScript()
        CalculationHelper calculationHelper = new CalculationHelper(baseScript)

        BigDecimal riskBasePremium = baseScript.getRiskBasePremiumData().find({ it.RISK_TYPE == RISK_TYPE }).PREMIUM
        BigDecimal ageCountFactor= calculationHelper.calculateAgeCountFactor(insuredObject)
        BigDecimal sumInsuredFactor = calculationHelper.calculateSumInsuredFactor(insuredObject)

        return (riskBasePremium * sumInsuredFactor * ageCountFactor).setScale(SCALE, RoundingMode.HALF_EVEN)
    }


    @Override
    Object run() {
        return null
    }
}
