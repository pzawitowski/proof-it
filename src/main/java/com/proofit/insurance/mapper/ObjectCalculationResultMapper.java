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
    ModelMapper modelMapper;

    public ObjectCalculationResultMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ObjectCalculationResult convertToCalculationResult(InsuredObject insuredObject, List<RiskCalculationResult> riskCalculationResults) {
        ObjectCalculationResult result = modelMapper.map(insuredObject, ObjectCalculationResult.class);

        result.setRisks(
            riskCalculationResults
                    .stream()
                    .map(calculation -> modelMapper.map(calculation, Risk.class))
                    .toList());

        Attributes attributes = new Attributes();
        attributes.setMake(insuredObject.getMake());
        attributes.setModel(insuredObject.getModel());
        attributes.setManufactureYear(insuredObject.getManufactureYear());

        result.setAttributes(attributes);
        result.setCoverageType(insuredObject.getCoverage());

        return result;
    }
}
