package com.codingtest.fxtransactionvalidator.handler;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.service.FxTransactionService;
import com.codingtest.fxtransactionvalidator.validator.result.CumulativeResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FxTransactionHandler {

    private final FxTransactionService transactionService;

    public Mono<ServerResponse> post(ServerRequest request) {
        Flux<FxTransaction> transactionFlux = request.bodyToFlux(FxTransaction.class);
        Flux<CumulativeResult> result = transactionService.validate(transactionFlux);
        return ServerResponse.ok().body(BodyInserters.fromPublisher(result, CumulativeResult.class));
    }

}
