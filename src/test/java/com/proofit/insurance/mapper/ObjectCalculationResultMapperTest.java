package com.proofit.insurance.mapper;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskCalculationResult;
import com.proofit.insurance.view.ObjectCalculationResult;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

import static com.proofit.insurance.calculator.BaseRiskTypes.*;
import static org.assertj.core.api.Assertions.assertThat;

class ObjectCalculationResultMapperTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    void whenConvertObjectCalculationResultToCalculationResult_thenCorrect() {
        // given
        ObjectCalculationResultMapper mapper = new ObjectCalculationResultMapper(modelMapper);

        InsuredObject insuredObject = new InsuredObject("Canyon", "Model", "STANDARD", 2023, new BigDecimal("100"));

        RiskCalculationResult damageRisk = new RiskCalculationResult(DAMAGE, new BigDecimal(50), new BigDecimal(20));
        RiskCalculationResult theftRisk = new RiskCalculationResult(THEFT, new BigDecimal(100), new BigDecimal(30));
        RiskCalculationResult thirdPartyRisk = new RiskCalculationResult(THIRD_PARTY_DAMAGE, new BigDecimal(100), new BigDecimal(25));

        // when
        ObjectCalculationResult calculationResult = mapper.convertToCalculationResult(insuredObject, List.of(damageRisk, theftRisk, thirdPartyRisk));


        // then
        assertThat(calculationResult.getSumInsured()).isEqualTo(insuredObject.getSumInsured());

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
