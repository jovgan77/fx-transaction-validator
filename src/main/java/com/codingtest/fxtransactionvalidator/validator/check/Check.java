package com.codingtest.fxtransactionvalidator.validator.check;

import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;

public interface Check<T> {

    ConstraintViolation check(T object);

}
