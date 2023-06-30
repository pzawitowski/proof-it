package com.proofit.insurance.mapper;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.view.Bicycle;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

import static com.proofit.insurance.model.RiskType.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BicycleMapperTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertBicycleToInsuredObject_thenCorrect() {
        // given
        BicycleMapper bicycleMapper = new BicycleMapper(modelMapper);
        Bicycle bicycle = new Bicycle();
        bicycle.setMake("Canyon");
        bicycle.setModel("Model 1");
        bicycle.setSumInsured(new BigDecimal("100.00"));
        bicycle.setRisks(List.of("DAMAGE", "THEFT", "THIRD_PARTY_DAMAGE"));
        bicycle.setManufactureYear(2023);

        // when
        InsuredObject insuredObject = bicycleMapper.convertToInsuredObject(bicycle);

        // then
        assertThat(insuredObject.getMake()).isEqualTo(bicycle.getMake());
        assertThat(insuredObject.getModel()).isEqualTo(bicycle.getModel());
        assertThat(insuredObject.getSumInsured()).isEqualTo(bicycle.getSumInsured());
        assertThat(insuredObject.getManufactureYear()).isEqualTo(bicycle.getManufactureYear());
        assertThat(insuredObject.getRisks()).isEqualTo(List.of(DAMAGE, THEFT, THIRD_PARTY_DAMAGE));
    }
}
