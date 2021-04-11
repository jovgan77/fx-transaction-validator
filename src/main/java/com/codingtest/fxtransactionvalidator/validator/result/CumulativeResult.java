package com.codingtest.fxtransactionvalidator.validator.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public class CumulativeResult {

    private final Object object;
    private final Set<ConstraintViolation> constraintViolations = new HashSet<>();

    public void addConstrainViolation(ConstraintViolation violation) {
        if (null != violation) {
            constraintViolations.add(violation);
        }
    }

    public boolean hasErrors() {
        return !constraintViolations.isEmpty();
    }

}
