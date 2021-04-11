package com.codingtest.fxtransactionvalidator.validator.check;

import com.codingtest.fxtransactionvalidator.model.VanillaOptionFxTransaction;
import com.codingtest.fxtransactionvalidator.model.constant.VanillaOptionStyle;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class ExcerciseStartDateCheck implements Check<VanillaOptionFxTransaction> {

    @Override
    public ConstraintViolation check(VanillaOptionFxTransaction tx) {
        log.debug("Try to check an excercise start date. Transaction: {}", tx);

        if (null == tx) {
            throw new IllegalArgumentException("Unable to validate. Transaction is null.");
        }

        if (!VanillaOptionStyle.AMERICAN.equalsIgnoreCase(tx.getStyle())) {
            return null;
        }

        LocalDate tradeDate = tx.getTradeDate();
        if (null == tradeDate) {
            throw new IllegalArgumentException("Trade date is null.");
        }

        LocalDate excerciseStartDate = tx.getExcerciseStartDate();
        if (null == excerciseStartDate) {
            throw new IllegalArgumentException("Excercise date is null.");
        }

        LocalDate expiryDate = tx.getExpiryDate();
        if (null == expiryDate) {
            throw new IllegalArgumentException("Expiry date is null.");
        }

        if (excerciseStartDate.isBefore(tradeDate) || excerciseStartDate.isAfter(expiryDate)) {
            return new ConstraintViolation("ExcerciseStartDate has to be after the trade date but before the expiry date",
                    excerciseStartDate);
        }

        log.debug("Excercise start date was successfully validated. Transaction: {}", tx);
        return null;
    }

}
