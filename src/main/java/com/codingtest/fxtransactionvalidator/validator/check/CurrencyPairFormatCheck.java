package com.codingtest.fxtransactionvalidator.validator.check;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Currency;

@Slf4j
public class CurrencyPairFormatCheck<T extends FxTransaction> implements Check<T> {

    @Override
    public ConstraintViolation check(T tx) {
        log.debug("Try to check a currency pair. Transaction: {}", tx);

        if (null == tx) {
            throw new IllegalArgumentException("Unable to validate. Transaction is null.");
        }

        String currencyPair = tx.getCcyPair();
        if (!StringUtils.hasText(currencyPair) || currencyPair.length() != 6) {
            throw new IllegalArgumentException(String.format("Currency pair string is invalid or empty: %s", currencyPair));
        }

        try {
            Currency.getInstance(currencyPair.substring(0,3));
            Currency.getInstance(currencyPair.substring(3));
        } catch (IllegalArgumentException ex) {
            return new ConstraintViolation("Invalid currency pair.", currencyPair);
        }

        log.debug("Successful check. Currencies meet the ISO 4217. Transaction: {}", tx);
        return null;
    }

}
