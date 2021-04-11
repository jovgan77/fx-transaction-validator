package com.codingtest.fxtransactionvalidator.validator.check;

import com.codingtest.fxtransactionvalidator.model.VanillaOptionFxTransaction;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class IsPremiumDateBeforeDeliveryDateCheck implements Check<VanillaOptionFxTransaction> {

    @Override
    public ConstraintViolation check(VanillaOptionFxTransaction tx) {
        log.debug("Try to check that premium date is before value (delivery) date. Transaction: {}", tx);

        if (null == tx) {
            throw new IllegalArgumentException("Unable to validate. Transaction is null.");
        }

        LocalDate premiumDate = tx.getPremiumDate();
        if (null == premiumDate) {
            throw new IllegalArgumentException("Premium date is null.");
        }

        LocalDate valueDate = tx.getValueDate();
        if (null == valueDate) {
            throw new IllegalArgumentException("Value (delivery) date is null.");
        }

        if (premiumDate.isAfter(valueDate)) {
            return new ConstraintViolation("Premium date is before value (delivery) date.", premiumDate);
        }

        log.debug("Successful check. Premium date is before a value (delivery) date. Transaction: {}", tx);
        return null;
    }

}
