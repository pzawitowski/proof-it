package com.proofit.insurance;

import com.proofit.insurance.calculator.Calculator;
import com.proofit.insurance.calculator.InsuranceCalculator;
import com.proofit.insurance.calculator.formulas.CalculationFormula;
import com.proofit.insurance.calculator.formulas.premium.DamagePremiumFormula;
import com.proofit.insurance.calculator.formulas.premium.TheftPremiumFormula;
import com.proofit.insurance.calculator.formulas.premium.ThirdPartyDamagePremiumFormula;
import com.proofit.insurance.calculator.formulas.suminsured.DamageSumInsuredFormula;
import com.proofit.insurance.calculator.formulas.suminsured.TheftSumInsuredFormula;
import com.proofit.insurance.calculator.formulas.suminsured.ThirdPartyDamageSumInsuredFormula;
import com.proofit.insurance.supplier.CalculationDataSupplier;
import com.proofit.insurance.supplier.GroovyCalculationDataSupplier;
import com.proofit.insurance.validator.InsuredObjectValidator;
import com.proofit.insurance.validator.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Configuration
public class ApplicationConfig {
    @Value("${groovyScript}")
    private String groovyScript;

    @Bean
    public CalculationDataSupplier getCalculationDataSupplier() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return new GroovyCalculationDataSupplier(ResourceUtils.getFile("classpath:" + groovyScript));
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
    Validator bicycleValidator() {
        return new InsuredObjectValidator();
    }

    @Bean
    public Calculator insuranceCalculator(List<CalculationFormula> premiumFormulas,
                                          List<CalculationFormula> insuranceSumFormulas,
                                          Validator validator) {
        return new InsuranceCalculator(premiumFormulas, insuranceSumFormulas, validator);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
