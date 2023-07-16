package com.proofit.insurance.supplier;


import com.proofit.insurance.calculator.Formula;

import java.util.Optional;

public interface FormulasSupplier {
    Optional<Formula> getPremiumFormula(String riskType);
    Optional<Formula> getSumInsuredFormula(String riskType);
}
