package com.proofit.insurance;

import com.proofit.insurance.calculator.InsuranceCalculator;
import com.proofit.insurance.calculator.PremiumInsuranceCalculator;
import com.proofit.insurance.calculator.formulas.*;
import com.proofit.insurance.supplier.CalculationDataSupplier;
import com.proofit.insurance.supplier.GroovyCalculationDataSupplier;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Configuration
public class ApplicationConfig {
    @Bean
    public CalculationDataSupplier getCalculationDataSupplier() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return new GroovyCalculationDataSupplier(ResourceUtils.getFile("classpath:groovy/BaseScript.groovy"));
    }

    @Bean
    List<CalculationFormula> premiumFormulas(CalculationDataSupplier dataSupplier) {
        return List.of(
                new DamagePremiumFormula(dataSupplier),
                new ThirdPartyDamagePremiumFormula(dataSupplier),
                new TheftPremiumFormula(dataSupplier));
    }

    @Bean
    List<CalculationFormula> insuranceSumFormulas(CalculationDataSupplier dataSupplier) {
        return List.of(
                new DamageSumInsuredFormula(),
                new ThirdPartyDamageSumInsuredFormula(),
                new TheftSumInsuredFormula());
    }

    @Bean
    public InsuranceCalculator insuranceCalculator(List<CalculationFormula> premiumFormulas, List<CalculationFormula> insuranceSumFormulas) {
        return new PremiumInsuranceCalculator(premiumFormulas);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
