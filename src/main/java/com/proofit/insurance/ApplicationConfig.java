package com.proofit.insurance;

import com.proofit.insurance.calculator.Calculator;
import com.proofit.insurance.calculator.InsuranceCalculator;
import com.proofit.insurance.supplier.FormulasSupplier;
import com.proofit.insurance.supplier.GroovyFormulasSupplier;
import com.proofit.insurance.validator.InsuredObjectValidator;
import com.proofit.insurance.validator.Validator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Value("${groovyScriptDirectory}")
    private String groovyScript;


    @Bean
    public FormulasSupplier getGroovyFormulaSupplier() throws Exception {
        GroovyFormulasSupplier groovyFormulasSupplier = new GroovyFormulasSupplier(groovyScript);
        groovyFormulasSupplier.loadFormulas();

        return groovyFormulasSupplier;
    }

    @Bean
    Validator bicycleValidator() {
        return new InsuredObjectValidator();
    }

    @Bean
    public Calculator insuranceCalculator(FormulasSupplier groovyFormulaSupplier,
                                          Validator validator) {
        return new InsuranceCalculator(groovyFormulaSupplier, validator);
    }



    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
