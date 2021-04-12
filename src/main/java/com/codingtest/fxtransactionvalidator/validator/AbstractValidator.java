package com.codingtest.fxtransactionvalidator.validator;

import com.codingtest.fxtransactionvalidator.validator.check.Check;
import com.codingtest.fxtransactionvalidator.validator.result.CumulativeResult;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public abstract class AbstractValidator<T> {

    private final Set<Check<T>> checks;

    public CumulativeResult validate(T object) {
        final CumulativeResult result = new CumulativeResult(object);
        checks.forEach(check -> result.addConstrainViolation(check.check((object))));
        return result;
    }

}
