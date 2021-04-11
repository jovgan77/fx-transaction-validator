package com.codingtest.fxtransactionvalidator.validator.check;

import com.codingtest.fxtransactionvalidator.model.VanillaOptionFxTransaction;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class IsExpiryDateBeforeDeliveryDateCheck implements Check<VanillaOptionFxTransaction> {

    @Override
    public ConstraintViolation check(VanillaOptionFxTransaction tx) {
        log.debug("Try to check that expiry date is before delivery date. Transaction: {}", tx);

        if (null == tx) {
            throw new IllegalArgumentException("Unable to validate. Transaction is null.");
        }

        LocalDate expiryDate = tx.getExpiryDate();
        if (null == expiryDate) {
            throw new IllegalArgumentException("Expiry date is null.");
        }

        LocalDate valueDate = tx.getValueDate();
        if (null == valueDate) {
            throw new IllegalArgumentException("Value (delivery) date is null.");
        }

        if (expiryDate.isAfter(valueDate)) {
            return new ConstraintViolation("Expiry date is before value (delivery) date.", expiryDate);
        }

        log.debug("Successful check. Expiry date is before a value (delivery) date. Transaction: {}", tx);
        return null;
    }

}
