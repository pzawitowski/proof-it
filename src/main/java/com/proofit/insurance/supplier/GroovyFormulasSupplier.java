package com.proofit.insurance.supplier;

import com.proofit.insurance.calculator.Formula;
import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class GroovyFormulasSupplier implements FormulasSupplier {
    public static final String GET_RISK_TYPE_METHOD = "getRiskType";
    private Map<String, GroovyObject> premiumFormulas = new HashMap<>();
    private Map<String, GroovyObject> sumInsuredFormulas = new HashMap<>();
    private String scriptsDirectory;

    public GroovyFormulasSupplier(String scriptsDirectory) throws Exception {
       this.scriptsDirectory = scriptsDirectory;
    }

    public void loadFormulas() throws Exception {
        GroovyScriptEngine engine = new GroovyScriptEngine(scriptsDirectory);

        File scriptDirectory = new File(scriptsDirectory);
        for (File file : scriptDirectory.listFiles()) {
            Class aClass = engine.loadScriptByName(file.getName());
            if (Formula.class.isAssignableFrom(aClass)) {
                addFormulaToMap(aClass);
            }
        }
    }

    private void addFormulaToMap(Class aClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        GroovyObject groovyScript = (GroovyObject) aClass.getConstructor().newInstance();
        if (groovyScript.invokeMethod("getFormulaType", new Object[] {}) == Formula.FormulaType.PREMIUM)  {
            premiumFormulas.put((String) groovyScript.invokeMethod(GET_RISK_TYPE_METHOD, new Object[]{}), groovyScript);
        } else {
            sumInsuredFormulas.put((String) groovyScript.invokeMethod(GET_RISK_TYPE_METHOD, new Object[]{}), groovyScript);
        }
    }

    @Override
    public Optional<Formula> getPremiumFormula(String riskType) {
        return ofNullable((Formula) premiumFormulas.get(riskType));
    }

    @Override
    public Optional<Formula> getSumInsuredFormula(String riskType) {
        return ofNullable((Formula) sumInsuredFormulas.get(riskType));
    }
}
