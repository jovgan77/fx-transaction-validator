package com.codingtest.fxtransactionvalidator.validator;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.validator.check.Check;

import java.util.Set;

public abstract class FxTransactionAbstractValidator<T extends FxTransaction> extends AbstractValidator<T> {
    public FxTransactionAbstractValidator(Set<Check<T>> checks) {
        super(checks);
    }
}
