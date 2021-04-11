package com.codingtest.fxtransactionvalidator.validator.check;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class SupportedCustomerCheck<T extends FxTransaction> implements Check<T> {

    private final List<String> supportedCustomers;

    @Override
    public ConstraintViolation check(T tx) {
        log.debug("Try to check a customer. Transaction: {}", tx);

        if (null == tx) {
            throw new IllegalArgumentException("Unable to validate. Transaction is null.");
        }

        String customer = tx.getCustomer();
        if (null == customer) {
            throw new IllegalArgumentException("Customer is null.");
        }

        if (!supportedCustomers.contains(customer)) {
            return new ConstraintViolation("Unsupported counterparty", customer);
        }

        log.debug("Customer successfully validated. Transaction: {}", tx);
        return null;
    }

}
