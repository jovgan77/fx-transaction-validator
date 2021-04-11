package com.codingtest.fxtransactionvalidator.validator;

import com.codingtest.fxtransactionvalidator.validator.check.Check;
import com.codingtest.fxtransactionvalidator.validator.result.CumulativeResult;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public abstract class Validator<T> {

    private final List<Check<T>> checks;

    public CumulativeResult validate(T object) {
        final CumulativeResult result = new CumulativeResult(object);
        checks.forEach(check -> result.addConstrainViolation(check.check((object))));
        return result;
    }

}
