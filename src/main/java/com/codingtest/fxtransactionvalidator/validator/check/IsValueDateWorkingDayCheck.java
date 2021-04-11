package com.codingtest.fxtransactionvalidator.validator.check;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Slf4j
public class IsValueDateWorkingDayCheck<T extends FxTransaction> implements Check<T> {

    @Override
    public ConstraintViolation check(T tx) {
        log.debug("Try to check that value date is a working day. Transaction: {}", tx);

        if (null == tx) {
            throw new IllegalArgumentException("Unable to validate. Transaction is null.");
        }

        LocalDate valueDate = tx.getValueDate();
        if (null == valueDate) {
            throw new IllegalArgumentException("Value date is null.");
        }

        DayOfWeek dayOfWeek = valueDate.getDayOfWeek();
        if (DayOfWeek.SATURDAY == dayOfWeek || DayOfWeek.SUNDAY == dayOfWeek) {
            return new ConstraintViolation("Value date should be a working day.", valueDate);
        }

        log.debug("Successful check. Value date is a working day. Transaction: {}", tx);
        return null;
    }

}
