package com.proofit.insurance.view;

import java.math.BigDecimal;
import java.util.List;

public class CalculationResponse {
    public CalculationResponse() {
    }

    public CalculationResponse(List<ObjectCalculationResult> objects) {
        this.objects = objects;
    }

    private List<ObjectCalculationResult> objects;

    public List<ObjectCalculationResult> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectCalculationResult> objects) {
        this.objects = objects;
    }

    public BigDecimal getPremium() {
        return objects.stream().
                map(ObjectCalculationResult::getPremium).
                reduce(new BigDecimal("0.00"), BigDecimal::add);
    }


}
