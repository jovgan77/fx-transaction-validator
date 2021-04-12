package com.codingtest.fxtransactionvalidator.validator;

import com.codingtest.fxtransactionvalidator.config.properties.ValidationProperties;
import com.codingtest.fxtransactionvalidator.model.VanillaOptionFxTransaction;
import com.codingtest.fxtransactionvalidator.model.constant.FxTransactionType;
import com.codingtest.fxtransactionvalidator.validator.check.CurrencyPairFormatCheck;
import com.codingtest.fxtransactionvalidator.validator.check.ExcerciseStartDateCheck;
import com.codingtest.fxtransactionvalidator.validator.check.IsExpiryDateBeforeDeliveryDateCheck;
import com.codingtest.fxtransactionvalidator.validator.check.IsPremiumDateBeforeDeliveryDateCheck;
import com.codingtest.fxtransactionvalidator.validator.check.IsValueDateAfterTradeDateCheck;
import com.codingtest.fxtransactionvalidator.validator.check.IsValueDateWorkingDayCheck;
import com.codingtest.fxtransactionvalidator.validator.check.SupportedCustomerCheck;
import com.codingtest.fxtransactionvalidator.validator.check.VanillaOptionStyleCheck;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component(FxTransactionType.VANILLA_OPTION)
public class VanillaOptionTxAbstractValidator extends FxTransactionAbstractValidator<VanillaOptionFxTransaction> {

    public VanillaOptionTxAbstractValidator(ValidationProperties properties) {
        super( Set.of(
                new CurrencyPairFormatCheck<>(),
                new IsValueDateAfterTradeDateCheck<>(),
                new IsValueDateWorkingDayCheck<>(),
                new SupportedCustomerCheck<>(properties.getSupportedCustomers()),
                new VanillaOptionStyleCheck(),
                new ExcerciseStartDateCheck(),
                new IsExpiryDateBeforeDeliveryDateCheck(),
                new IsPremiumDateBeforeDeliveryDateCheck()
        ));
    }

}
