package com.codingtest.fxtransactionvalidator.controller;

import com.codingtest.fxtransactionvalidator.handler.FxTransactionValidationHandler;
import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.validator.result.CumulativeResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class FxTransactionController {

    private final FxTransactionValidationHandler fxTransactionValidationHandler;

    @PostMapping
    public Flux<CumulativeResult> getErrors(@RequestBody List<FxTransaction> transactions) {
        if (CollectionUtils.isEmpty(transactions)) {
            log.info("Empty Request");
            return Flux.empty();
        }

        Set<FxTransaction> transactionSet = new HashSet<>(transactions);
        log.info("Received transactions: total - {}, unique - {}.", transactions.size(), transactionSet.size());
        return fxTransactionValidationHandler.getErrors(transactionSet);
    }

}
