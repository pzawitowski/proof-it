package com.proofit.insurance.mapper;

import com.proofit.insurance.model.InsuredObjectCalculationResult;
import com.proofit.insurance.view.Attributes;
import com.proofit.insurance.view.CalculationResult;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ObjectCalculationResultMapper {
    ModelMapper modelMapper = new ModelMapper();

    public ObjectCalculationResultMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CalculationResult convertToCalculationResult(InsuredObjectCalculationResult in) {
        CalculationResult result = modelMapper.typeMap(InsuredObjectCalculationResult.class, CalculationResult.class).
                addMappings(mapper -> mapper.map(src -> src.getRiskCalculationResults(), CalculationResult::setRisks)).map(in);

        result.setAttributes(new Attributes());
        result.getAttributes().setMake(in.getInsuredObject().getMake());
        result.getAttributes().setModel(in.getInsuredObject().getModel());
        result.getAttributes().setManufactureYear(in.getInsuredObject().getManufactureYear());
        result.setCoverageType(in.getInsuredObject().getCoverage());

        return result;
    }
}
