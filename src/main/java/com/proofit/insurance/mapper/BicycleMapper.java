package com.proofit.insurance.mapper;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.view.Bicycle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BicycleMapper {
    private ModelMapper modelMapper;
    public BicycleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public InsuredObject convertToInsuredObject(Bicycle bicycle) {
        return modelMapper.map(bicycle, InsuredObject.class);
    }
}
