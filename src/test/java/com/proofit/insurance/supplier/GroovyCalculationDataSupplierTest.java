package com.proofit.insurance.supplier;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.calculator.RiskType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class GroovyCalculationDataSupplierTest {

    CalculationDataSupplier calculationDataSupplier;

    public GroovyCalculationDataSupplierTest() throws Exception {
        calculationDataSupplier = new GroovyCalculationDataSupplier(new File("src/test/resources", "BaseScript.groovy"));
    }

    @ParameterizedTest
    @CsvSource({
            "DAMAGE,10",
            "THIRD_PARTY_DAMAGE,20",
            "THEFT,30"
            })
    void getPremiumValueByRiskType_whenRiskTypeProvided_shouldReturnPremiumValue(RiskType riskType, BigDecimal expectedResult) {
        // when
        BigDecimal premium = calculationDataSupplier.getRiskDataPremiumByRiskType(riskType);
        // then
        assertThat(premium).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({"Canyon,CF 5,2012,0.9,1.1",
                "Canyon,Lux Trial,2010,0.95,1.6",
                "Pearl,Gravel SL EVO,2020,1.5,2",
                "Pearl,Gravel SL EVO 2,2020,0.99,1.8",
                "Megamo,Model 1,2020,1.1,2.3",
                "Cube,Analog,2022,1,3"})
    void getAgeFactor_whenInsuredObjectIsProvided_shouldReturnAgeFactorData(String make, String model, Integer manufacturedYear, BigDecimal expectedFactorMin, BigDecimal expectedFactorMax) {
        // given
        InsuredObject insuredObject = new InsuredObject(make, model, "", manufacturedYear, BigDecimal.valueOf(100));
        // when
        FactorData factorData = calculationDataSupplier.getAgeFactorDataByObject(insuredObject);
        // then
        assertThat(factorData.getFactorMin()).isEqualTo(expectedFactorMin);
        assertThat(factorData.getFactorMax()).isEqualTo(expectedFactorMax);
    }

    @ParameterizedTest
    @CsvSource({"0,1.3",
                "1,1.3",
                "2,1.2",
                "3,1.2", // is it right?
                "4,1.1",
                "5,1",
                "10,1"})
    void getRiskFactor_whenRiskCountIsProvided_shouldReturnRiskCountFactorData(Integer riskCount, BigDecimal expectedRiskFactor) {
        // given riskCount

        // when
        FactorData factorData = calculationDataSupplier.getRiskCountFactorData(riskCount);

        // then
        assertThat(factorData.getFactorMin()).isEqualTo(expectedRiskFactor);
    }

    @ParameterizedTest
    @CsvSource({"100,0.5,1",
                "500,0.5,1",
                "1000,0.5,1",
                "3001,2,3"
    })
    void getSumRiskIsnuredFactor_whenRiskSumInsuredIsProvided_shouldReturnRiskSumInsuredFactorData(BigDecimal sumRiskInsured, BigDecimal expectedFactorMin, BigDecimal expectedFactorMax) {
        // given sumRiskInsured

        // when
        FactorData factorData = calculationDataSupplier.getSumInsuredFactorDataBySum(sumRiskInsured);

        // then
        assertThat(factorData.getFactorMin()).isEqualTo(expectedFactorMin);
        assertThat(factorData.getFactorMax()).isEqualTo(expectedFactorMax);
    }
}
