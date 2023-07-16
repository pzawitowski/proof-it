import com.proofit.insurance.model.InsuredObject
import com.proofit.insurance.calculator.Formula

import java.math.RoundingMode


class ThirdPartyPremiumFormula extends Script implements Formula {

    BaseScript baseScript = new BaseScript()
    private CalculationHelper calculationHelper = new CalculationHelper(baseScript)


    @Override
    String getRiskType() {
        return 'THIRD_PARTY_DAMAGE'
    }

    @Override
    FormulaType getFormulaType() {
        return FormulaType.PREMIUM;
    }

    @Override
    BigDecimal calculate(InsuredObject insuredObject) {
        BigDecimal riskBasePremium = baseScript.getRiskBasePremiumData()
                .find({ it.RISK_TYPE == getRiskType() }).PREMIUM
        BigDecimal riskCountFactor = baseScript.getRiskCountFactorData()
                .find({ it.VALUE_FROM <= insuredObject.getRisks().size() && it.VALUE_TO >= insuredObject.getRisks().size()}).FACTOR_MIN
        BigDecimal sumInsuredFactor = calculationHelper.calculateSumInsuredFactor(insuredObject)

        return (riskBasePremium * sumInsuredFactor * riskCountFactor).setScale(2, RoundingMode.HALF_EVEN)
    }

    @Override
    Object run() {
        return null
    }
}
