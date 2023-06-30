package com.proofit.insurance.supplier;

import java.time.LocalDate;

public class DefaultDateSupplier implements DateSupplier {

    @Override
    public Integer getCurrentYear() {
        return LocalDate.now().getYear();
    }

    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
