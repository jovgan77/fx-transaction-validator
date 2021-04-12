package com.codingtest.fxtransactionvalidator.validator.result;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class CumulativeResult {

    @JsonProperty("validatedObject")
    private final Object object;

    @JsonProperty("constrainViolation")
    private final Set<ConstraintViolation> constraintViolations = new HashSet<>();

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CumulativeResult(Object object) {
        this.object = object;
    }

    public void addConstrainViolation(ConstraintViolation violation) {
        if (null != violation) {
            constraintViolations.add(violation);
        }
    }

    public boolean hasErrors() {
        return !constraintViolations.isEmpty();
    }

}
