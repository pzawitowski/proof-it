package com.proofit.insurance.mapper;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.model.RiskCalculationResult;
import com.proofit.insurance.view.Attributes;
import com.proofit.insurance.view.ObjectCalculationResult;
import com.proofit.insurance.view.Risk;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ObjectCalculationResultMapper {
    ModelMapper modelMapper = new ModelMapper();

    public ObjectCalculationResultMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ObjectCalculationResult convertToCalculationResult(InsuredObject in, List<RiskCalculationResult> riskCalculationResults) {
        ObjectCalculationResult result = modelMapper.map(in, ObjectCalculationResult.class);

        result.setRisks(
            riskCalculationResults
                    .stream()
                    .map(calculation -> modelMapper.map(calculation, Risk.class))
                    .toList());

        result.setAttributes(new Attributes());
        result.getAttributes().setMake(in.getMake());
        result.getAttributes().setModel(in.getModel());
        result.getAttributes().setManufactureYear(in.getManufactureYear());
        result.setCoverageType(in.getCoverage());

        return result;
    }
}
