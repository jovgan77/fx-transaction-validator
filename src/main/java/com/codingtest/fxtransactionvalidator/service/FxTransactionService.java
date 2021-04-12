package com.codingtest.fxtransactionvalidator.service;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.validator.FxTransactionAbstractValidator;
import com.codingtest.fxtransactionvalidator.validator.result.CumulativeResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FxTransactionService {

    private final Map<String, FxTransactionAbstractValidator<?>> validators;

    public Flux<CumulativeResult> validate(Flux<FxTransaction> transactions) {
        return transactions.map(tx -> {
            //noinspection unchecked
            FxTransactionAbstractValidator<FxTransaction> validator = (FxTransactionAbstractValidator<FxTransaction>) validators.get(tx.getType());
            if (null == validator) {
                log.warn("Validator for {} transaction type is not found!", tx.getType());
                return new CumulativeResult(tx);
            }
            return validator.validate(tx);
        }).filter(CumulativeResult::hasErrors);
    }

}
