package com.codingtest.fxtransactionvalidator.handler;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.validator.Validator;
import com.codingtest.fxtransactionvalidator.validator.result.CumulativeResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class FxTransactionValidationHandler {

    private final Map<String, Validator<?>> validators;

    public Flux<CumulativeResult> getErrors(Set<FxTransaction> transactions) {
        return Flux.fromIterable(transactions)
                .map(tx -> getValidator(tx).validate(tx))
                .filter(CumulativeResult::hasErrors);
    }

    private <T extends FxTransaction> Validator<T> getValidator(T transaction) {
        //noinspection unchecked
        return (Validator<T>) validators.get(transaction.getType());
    }

}
