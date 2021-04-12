package com.codingtest.fxtransactionvalidator.integration;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.service.FxTransactionService;
import com.codingtest.fxtransactionvalidator.validator.result.CumulativeResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.BodyInserters;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(FxTransactionService.class)
class TransactionRouterTests {

    @Autowired
    private WebTestClient webClient;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void testValidFxTransaction() throws IOException {

        File file = ResourceUtils.getFile("classpath:data/invalid-transactions.json");
        List<FxTransaction> transactions = mapper.readValue(file, new TypeReference<>() {});

        webClient.post()
                .uri("/transactions/validate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(transactions))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CumulativeResult.class)
                .consumeWith(response -> Assertions.assertNotNull(response.getResponseBody()))
                .consumeWith(response -> Assertions.assertEquals(4, Objects.requireNonNull(response.getResponseBody()).size()));
    }

}
