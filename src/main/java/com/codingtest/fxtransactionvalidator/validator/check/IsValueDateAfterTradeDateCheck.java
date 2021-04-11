package com.codingtest.fxtransactionvalidator.validator.check;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@Slf4j
public class IsValueDateAfterTradeDateCheck<T extends FxTransaction> implements Check<T> {

    @Override
    public ConstraintViolation check(T tx) {
        log.debug("Try to check a value date against a trade date. Transaction: {}", tx);

        if (null == tx) {
            throw new IllegalArgumentException("Unable to validate. Transaction is null.");
        }

        LocalDate valueDate = tx.getValueDate();
        if (null == valueDate) {
            throw new IllegalArgumentException("Value date is null");
        }

        LocalDate tradeDate = tx.getTradeDate();
        if (null == tradeDate) {
            throw new IllegalArgumentException("Trade date is null");
        }

        if (valueDate.isBefore(tradeDate)) {
            String message = String.format("Value date should be after trade date: %s", tradeDate);
            return new ConstraintViolation(message, valueDate);
        }

        log.debug("Successful check. Value date is after a trade date. Transaction: {}", tx);
        return null;
    }

}
