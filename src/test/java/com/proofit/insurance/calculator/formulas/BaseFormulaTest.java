package com.proofit.insurance.calculator.formulas;

import com.proofit.insurance.model.InsuredObject;
import com.proofit.insurance.supplier.GroovyFormulasSupplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static com.proofit.insurance.calculator.BaseRiskTypes.*;
import static java.math.BigDecimal.valueOf;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseFormulaTest {
    protected List<InsuredObject> insuredObjects = new ArrayList<>();
    protected GroovyFormulasSupplier groovyFormulasSupplier;

    protected BaseFormulaTest() throws Exception {
        groovyFormulasSupplier = new GroovyFormulasSupplier("src/main/groovy");
        groovyFormulasSupplier.loadFormulas();
    }


    @BeforeAll
    public void initInsuredObjectsList() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        insuredObjects.add(new InsuredObject("Pearl", "Gravel SL EVO", "EXTRA", 2015, valueOf(1000), List.of(THEFT, DAMAGE, THIRD_PARTY_DAMAGE), 7));
        insuredObjects.add(new InsuredObject("Sensa", "V2", "STANDARD", 2020, valueOf(225), List.of(DAMAGE), 2));
        insuredObjects.add(new InsuredObject("OTHER", "OTHER", "EXTRA", 2019, valueOf(100), List.of(DAMAGE, THIRD_PARTY_DAMAGE), 3));
    }

    public List<InsuredObject> getInsuredObjects() {
        return insuredObjects;
    }
}
