package com.codingtest.fxtransactionvalidator.validator.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ConstraintViolation {

    private final String message;
    private final Object invalidValue;

}
