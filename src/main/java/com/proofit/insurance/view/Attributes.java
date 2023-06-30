package com.proofit.insurance.view;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attributes {
    @JsonProperty("MANUFACTURE_YEAR")
    private int manufactureYear;

    @JsonProperty("MODEL")
    private String model;

    @JsonProperty("MAKE")
    private String make;

    public int getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
