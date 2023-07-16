import com.proofit.insurance.model.InsuredObject

class CalculationHelper extends Script {
    BaseScript baseScript;

    CalculationHelper() {
    }

    CalculationHelper(BaseScript baseScript) {
        this.baseScript = baseScript
    }

    BaseScript getBaseScript() {
        return baseScript
    }

    void setBaseScript(BaseScript baseScript) {
        this.baseScript = baseScript
    }

    BigDecimal calculateAgeCountFactor(InsuredObject insuredObject) {
        var ageData = getAgeData(insuredObject)

        return ageData.FACTOR_MAX - (ageData.FACTOR_MAX - ageData.FACTOR_MIN) * (ageData.VALUE_TO - insuredObject.getAge()) / (ageData.VALUE_TO - ageData.VALUE_FROM)
    }

    LinkedHashMap<String, Serializable> getAgeData(InsuredObject insuredObject) {
        def ageData = baseScript.getAgeFactorData().find({
            it.MAKE == insuredObject.getMake() &&
                    it.MODEL == insuredObject.getModel() &&
                    it.VALUE_FROM <= insuredObject.getAge() && it.VALUE_TO >= insuredObject.getAge()
        })

        if (ageData == null) {
            ageData = baseScript.getAgeFactorData().find({
                it.MODEL == null &&
                        it.MAKE == insuredObject.getMake() &&
                        it.VALUE_FROM <= insuredObject.getAge() && it.VALUE_TO >= insuredObject.getAge()
            })
        }

        if (ageData == null) {
            ageData = baseScript.getAgeFactorData().find({
                it.MODEL == null &&
                        it.MAKE == null &&
                        it.VALUE_FROM <= insuredObject.getAge() && it.VALUE_TO >= insuredObject.getAge()
            })
        }

        return ageData;
    }

    BigDecimal calculateSumInsuredFactor( InsuredObject insuredObject) {
        var sumInsuredFactorData =
                baseScript.getSumInsuredFactorData().find({ it.VALUE_FROM <= insuredObject.getSumInsured() && it.VALUE_TO >= insuredObject.getSumInsured() });
        var ageData = getAgeData(insuredObject);

        return sumInsuredFactorData.FACTOR_MAX - (sumInsuredFactorData.FACTOR_MAX - sumInsuredFactorData.FACTOR_MIN) * (ageData.VALUE_TO - insuredObject.getAge()) / (ageData.VALUE_TO - ageData.VALUE_FROM)
    }

    @Override
    Object run() {
        return null
    }
}
