package com.proofit.insurance.mapper;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.InsuredObjectCalculationResult;
import com.proofit.insurance.model.RiskCalculationResult;
import com.proofit.insurance.model.RiskType;
import com.proofit.insurance.view.CalculationResult;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ObjectCalculationResultMapperTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    void whenConvertObjectCalculationResultToCalculationResult_thenCorrect() {
        // given
        ObjectCalculationResultMapper mapper = new ObjectCalculationResultMapper(modelMapper);

        InsuredObjectCalculationResult objectCalculationResult = new InsuredObjectCalculationResult();
        InsuredObject insuredObject = new InsuredObject("Canyon", "Model", "STANDARD", 2023, new BigDecimal("100"));

        RiskCalculationResult damageRisk = new RiskCalculationResult(RiskType.DAMAGE, new BigDecimal(50), new BigDecimal(20));
        RiskCalculationResult theftRisk = new RiskCalculationResult(RiskType.THEFT, new BigDecimal(100), new BigDecimal(30));
        RiskCalculationResult thirdPartyRisk = new RiskCalculationResult(RiskType.THIRD_PARTY_DAMAGE, new BigDecimal(100), new BigDecimal(25));

        objectCalculationResult.setInsuredObject(insuredObject);
        objectCalculationResult.setRiskCalculationResults(List.of(damageRisk, theftRisk, thirdPartyRisk));

        // when
        CalculationResult calculationResult = mapper.convertToCalculationResult(objectCalculationResult);


        // then
        assertThat(calculationResult.getSumInsured()).isEqualTo(objectCalculationResult.getInsuredObject().getSumInsured());

        assertThat(calculationResult.getRisks().get(0).getPremium()).isEqualTo(new BigDecimal(20));
        assertThat(calculationResult.getRisks().get(1).getPremium()).isEqualTo(new BigDecimal(30));
        assertThat(calculationResult.getRisks().get(2).getPremium()).isEqualTo(new BigDecimal(25));

        assertThat(calculationResult.getRisks().get(0).getRiskType()).isEqualTo("DAMAGE");
        assertThat(calculationResult.getRisks().get(1).getRiskType()).isEqualTo("THEFT");
        assertThat(calculationResult.getRisks().get(2).getRiskType()).isEqualTo("THIRD_PARTY_DAMAGE");

        assertThat(calculationResult.getAttributes().getMake()).isEqualTo("Canyon");
        assertThat(calculationResult.getAttributes().getModel()).isEqualTo("Model");
        assertThat(calculationResult.getAttributes().getManufactureYear()).isEqualTo(2023);
        assertThat(calculationResult.getCoverageType()).isEqualTo("STANDARD");
    }
}
