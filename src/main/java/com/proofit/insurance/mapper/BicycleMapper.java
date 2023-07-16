package com.proofit.insurance.mapper;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.supplier.DateSupplier;
import com.proofit.insurance.supplier.DefaultDateSupplier;
import com.proofit.insurance.view.Bicycle;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BicycleMapper {
    private ModelMapper modelMapper;
    private DateSupplier dateSupplier = new DefaultDateSupplier();
    public BicycleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public InsuredObject convertToInsuredObject(Bicycle bicycle) {
        InsuredObject insuredObject = modelMapper.map(bicycle, InsuredObject.class);
        insuredObject.setAge(dateSupplier.getCurrentYear() - insuredObject.getManufactureYear());

        return insuredObject;
    }
}
