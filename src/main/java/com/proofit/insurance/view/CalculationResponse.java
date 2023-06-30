package com.proofit.insurance.view;

import java.math.BigDecimal;
import java.util.List;

public class CalculationResponse {
    public CalculationResponse() {
    }

    public CalculationResponse(List<CalculationResult> objects) {
        this.objects = objects;
    }

    private List<CalculationResult> objects;

    public List<CalculationResult> getObjects() {
        return objects;
    }

    public void setObjects(List<CalculationResult> objects) {
        this.objects = objects;
    }

    public BigDecimal getPremium() {
        return objects.stream().
                map(CalculationResult::getPremium).
                reduce(new BigDecimal("0.00"), BigDecimal::add);
    }


}
