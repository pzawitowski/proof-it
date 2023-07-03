package com.proofit.insurance.calculator.formulas;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.supplier.CalculationDataSupplier;
import com.proofit.insurance.supplier.DateSupplier;
import com.proofit.insurance.supplier.FixedDateSupplier;
import com.proofit.insurance.supplier.GroovyCalculationDataSupplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static com.proofit.insurance.calculator.RiskType.*;
import static java.math.BigDecimal.valueOf;
import static java.util.List.of;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseFormulaTest {
    protected List<InsuredObject> insuredObjects = new ArrayList<>();
    protected CalculationDataSupplier dataSupplier;
    protected DateSupplier dateSupplier = new FixedDateSupplier("2022-01-01");

    @BeforeAll
    public void initInsuredObjectsList() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        insuredObjects.add(new InsuredObject("Pearl", "Gravel SL EVO", "EXTRA", 2015, valueOf(1000), of(THEFT, DAMAGE, THIRD_PARTY_DAMAGE)));
        insuredObjects.add(new InsuredObject("Sensa", "V2", "STANDARD", 2020, valueOf(225), of(DAMAGE)));
        insuredObjects.add(new InsuredObject("OTHER", "OTHER", "EXTRA", 2019, valueOf(100), of(DAMAGE, THIRD_PARTY_DAMAGE)));

        dataSupplier = new GroovyCalculationDataSupplier(new File("src/test/resources", "BaseScript.groovy"), dateSupplier);
    }

    public List<InsuredObject> getInsuredObjects() {
        return insuredObjects;
    }
}
