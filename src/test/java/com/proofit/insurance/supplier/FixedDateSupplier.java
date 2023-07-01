package com.proofit.insurance.supplier;

import java.time.LocalDate;

public class FixedDateSupplier implements DateSupplier {

    private LocalDate date;

    public FixedDateSupplier(String dateString) {
        this.date = LocalDate.parse(dateString);
    }

    @Override
    public Integer getCurrentYear() {
        return date.getYear();
    }

    @Override
    public LocalDate getCurrentDate() {
        return date;
    }
}
