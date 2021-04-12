package com.codingtest.fxtransactionvalidator.unit.check;

import com.codingtest.fxtransactionvalidator.model.VanillaOptionFxTransaction;
import com.codingtest.fxtransactionvalidator.validator.check.ExcerciseStartDateCheck;
import com.codingtest.fxtransactionvalidator.validator.result.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ExcerciseStartDateCheckTest {
    
    private final ExcerciseStartDateCheck check = new ExcerciseStartDateCheck();

    @Test
    public void TestExcerciseStartDateCheckShouldReturnNullWhenDataIsValid() {
        VanillaOptionFxTransaction tx = new VanillaOptionFxTransaction();
        LocalDate excerciseStartDate = LocalDate.of(2021, 11, 11);
        tx.setExcerciseStartDate(excerciseStartDate);
        tx.setTradeDate(excerciseStartDate.minusDays(1));
        tx.setExpiryDate(excerciseStartDate.plusDays(1));
        tx.setStyle("AMERICAN");
        
        ConstraintViolation actual = check.check(tx);

        assertNull(actual);
    }

    @Test
    public void TestExcerciseStartDateCheckShouldReturnNullWhenStyleIsNotAmerican() {
        VanillaOptionFxTransaction tx = new VanillaOptionFxTransaction();
        LocalDate excerciseStartDate = LocalDate.of(2021, 11, 11);
        tx.setExcerciseStartDate(excerciseStartDate);
        tx.setTradeDate(excerciseStartDate.minusDays(1));
        tx.setExpiryDate(excerciseStartDate.plusDays(1));
        tx.setStyle("EUROPEAN");

        ConstraintViolation actual = check.check(tx);

        assertNull(actual);
    }

    @Test
    public void TestExcerciseStartDateCheckShouldReturnViolationWhenExcerciseStartDateIsBeforeTradeDate() {
        VanillaOptionFxTransaction tx = new VanillaOptionFxTransaction();
        LocalDate excerciseStartDate = LocalDate.of(2021, 11, 11);
        tx.setExcerciseStartDate(excerciseStartDate);
        tx.setTradeDate(excerciseStartDate.plusDays(1));
        tx.setExpiryDate(excerciseStartDate.plusDays(1));
        tx.setStyle("AMERICAN");

        ConstraintViolation actual = check.check(tx);

        assertNotNull(actual);
        assertEquals(excerciseStartDate, actual.getInvalidValue());
    }

    @Test
    public void TestExcerciseStartDateCheckShouldReturnViolationWhenExcerciseStartDateIsAfterExpiryDate() {
        VanillaOptionFxTransaction tx = new VanillaOptionFxTransaction();
        LocalDate excerciseStartDate = LocalDate.of(2021, 11, 11);
        tx.setExcerciseStartDate(excerciseStartDate);
        tx.setTradeDate(excerciseStartDate.minusDays(1));
        tx.setExpiryDate(excerciseStartDate.minusDays(1));
        tx.setStyle("AMERICAN");

        ConstraintViolation actual = check.check(tx);

        assertNotNull(actual);
        assertEquals(excerciseStartDate, actual.getInvalidValue());
    }

    @Test
    public void TestExcerciseStartDateCheckShouldThrowExceptionWhenTxIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> check.check(null));
    }

    @Test
    public void TestExcerciseStartDateCheckShouldThrowExceptionWhenExcerciseStartDateIsNull() {
        VanillaOptionFxTransaction tx = new VanillaOptionFxTransaction();
        LocalDate excerciseStartDate = LocalDate.of(2021, 11, 11);
        tx.setExcerciseStartDate(null);
        tx.setTradeDate(excerciseStartDate.plusDays(1));
        tx.setExpiryDate(excerciseStartDate.minusDays(1));
        tx.setStyle("AMERICAN");

        Assertions.assertThrows(IllegalArgumentException.class, () -> check.check(tx));
    }

    @Test
    public void TestExcerciseStartDateCheckShouldThrowExceptionWhenTradeDateIsNull() {
        VanillaOptionFxTransaction tx = new VanillaOptionFxTransaction();
        LocalDate excerciseStartDate = LocalDate.of(2021, 11, 11);
        tx.setExcerciseStartDate(excerciseStartDate);
        tx.setTradeDate(null);
        tx.setExpiryDate(excerciseStartDate.minusDays(1));
        tx.setStyle("AMERICAN");

        Assertions.assertThrows(IllegalArgumentException.class, () -> check.check(tx));
    }

    @Test
    public void TestExcerciseStartDateCheckShouldThrowExceptionWhenExpiryDateIsNull() {
        VanillaOptionFxTransaction tx = new VanillaOptionFxTransaction();
        LocalDate excerciseStartDate = LocalDate.of(2021, 11, 11);
        tx.setExcerciseStartDate(excerciseStartDate);
        tx.setTradeDate(excerciseStartDate.plusDays(1));
        tx.setExpiryDate(null);
        tx.setStyle("AMERICAN");

        Assertions.assertThrows(IllegalArgumentException.class, () -> check.check(tx));
    }

}
