package com.proofit.insurance.validator;

import com.proofit.insurance.model.InsuredObject;

public interface Validator {
    void validate(InsuredObject insuredObject);
}
