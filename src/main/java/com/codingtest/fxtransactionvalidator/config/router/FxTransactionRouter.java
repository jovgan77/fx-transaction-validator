package com.codingtest.fxtransactionvalidator.config.router;

import com.codingtest.fxtransactionvalidator.handler.FxTransactionHandler;
import com.codingtest.fxtransactionvalidator.service.FxTransactionService;
import org.springdoc.webflux.core.fn.SpringdocRouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class FxTransactionRouter {

    @Bean
    public RouterFunction<ServerResponse> route(FxTransactionHandler handler) {
        return SpringdocRouteBuilder.route()
                .POST("/transactions/validate", handler::post, ops -> ops.beanClass(FxTransactionService.class).beanMethod("validate"))
                .build();
    }

}
