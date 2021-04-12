package com.codingtest.fxtransactionvalidator.unit.check;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.validator.check.CurrencyPairFormatCheck;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyPairFormatCheckTest {

    private final CurrencyPairFormatCheck<FxTransaction> check = new CurrencyPairFormatCheck<>();

    @Test
    public void TestCurrencyPairFormatCheckShouldReturnNullWhenDataIsValid() {
        FxTransaction tx = new FxTransaction();
        tx.setCcyPair("USDEUR");

        ConstraintViolation actual = check.check(tx);

        assertNull(actual);
    }

    @Test
    public void TestCurrencyPairFormatCheckShouldReturnViolationWhenCcyPairIsInvalid() {
        String expectedInvalidValue = "ABCDEF";
        FxTransaction tx = new FxTransaction();
        tx.setCcyPair(expectedInvalidValue);

        ConstraintViolation actual = check.check(tx);

        assertNotNull(actual);
        assertEquals(expectedInvalidValue, actual.getInvalidValue());
    }

    @Test
    public void TestCurrencyPairFormatCheckShouldThrowExceptionWhenTxIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> check.check(null));
    }

    @Test
    public void TestCurrencyPairFormatCheckShouldThrowExceptionWhenCcyPairIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> check.check(new FxTransaction()));
    }

    @Test
    public void TestCurrencyPairFormatCheckShouldThrowExceptionWhenCcyPairHasInvalidLength() {
        FxTransaction tx = new FxTransaction();
        tx.setCcyPair("ABCD");

        Assertions.assertThrows(IllegalArgumentException.class, () -> check.check(tx));
    }

}