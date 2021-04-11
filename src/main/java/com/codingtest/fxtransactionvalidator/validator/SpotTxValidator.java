package com.codingtest.fxtransactionvalidator.validator;

import com.codingtest.fxtransactionvalidator.config.properties.ValidationProperties;
import com.codingtest.fxtransactionvalidator.model.SpotFxTransaction;
import com.codingtest.fxtransactionvalidator.model.constant.FxTransactionType;
import com.codingtest.fxtransactionvalidator.validator.check.CurrencyPairFormatCheck;
import com.codingtest.fxtransactionvalidator.validator.check.SupportedCustomerCheck;
import com.codingtest.fxtransactionvalidator.validator.check.IsValueDateAfterTradeDateCheck;
import com.codingtest.fxtransactionvalidator.validator.check.IsValueDateWorkingDayCheck;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(FxTransactionType.SPOT)
public class SpotTxValidator extends Validator<SpotFxTransaction> {

    public SpotTxValidator(ValidationProperties properties) {
        super(List.of(
                new CurrencyPairFormatCheck<>(),
                new IsValueDateAfterTradeDateCheck<>(),
                new IsValueDateWorkingDayCheck<>(),
                new SupportedCustomerCheck<>(properties.getSupportedCustomers())));
    }

}
