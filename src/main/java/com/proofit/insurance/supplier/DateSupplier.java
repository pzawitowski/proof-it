package com.proofit.insurance.supplier;

import java.time.LocalDate;

public interface DateSupplier {
    Integer getCurrentYear();
    LocalDate getCurrentDate();
}
