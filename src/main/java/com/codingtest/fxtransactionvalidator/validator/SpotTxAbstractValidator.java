package com.codingtest.fxtransactionvalidator.validator;

import com.codingtest.fxtransactionvalidator.config.properties.ValidationProperties;
import com.codingtest.fxtransactionvalidator.model.SpotFxTransaction;
import com.codingtest.fxtransactionvalidator.model.constant.FxTransactionType;
import com.codingtest.fxtransactionvalidator.validator.check.CurrencyPairFormatCheck;
import com.codingtest.fxtransactionvalidator.validator.check.IsValueDateAfterTradeDateCheck;
import com.codingtest.fxtransactionvalidator.validator.check.IsValueDateWorkingDayCheck;
import com.codingtest.fxtransactionvalidator.validator.check.SupportedCustomerCheck;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component(FxTransactionType.SPOT)
public class SpotTxAbstractValidator extends FxTransactionAbstractValidator<SpotFxTransaction> {

    public SpotTxAbstractValidator(ValidationProperties properties) {
        super(Set.of(
                new CurrencyPairFormatCheck<>(),
                new IsValueDateAfterTradeDateCheck<>(),
                new IsValueDateWorkingDayCheck<>(),
                new SupportedCustomerCheck<>(properties.getSupportedCustomers())));
    }

}
