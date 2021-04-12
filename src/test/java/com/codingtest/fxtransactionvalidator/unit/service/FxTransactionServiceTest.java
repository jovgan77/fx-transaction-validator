package com.codingtest.fxtransactionvalidator.unit.service;

import com.codingtest.fxtransactionvalidator.model.FxTransaction;
import com.codingtest.fxtransactionvalidator.service.FxTransactionService;
import com.codingtest.fxtransactionvalidator.validator.result.CumulativeResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FxTransactionServiceTest {

    @Autowired
    private FxTransactionService transactionService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void TestValidateShouldReturnEmptyResultWhenDataIsValid() throws IOException {
        File file = ResourceUtils.getFile("classpath:data/valid-transactions.json");
        List<FxTransaction> transactions = mapper.readValue(file, new TypeReference<>() {});

        List<CumulativeResult> actualResult = transactionService.validate(Flux.fromIterable(transactions))
                .toStream().collect(Collectors.toList());

        assertEquals(0, actualResult.size());
    }

    @Test
    public void TestValidateShouldReturnViolationsWhenDataIsInvalid() throws IOException {
        File file = ResourceUtils.getFile("classpath:data/invalid-transactions.json");
        List<FxTransaction> transactions = mapper.readValue(file, new TypeReference<>() {});

        List<CumulativeResult> actualResult = transactionService.validate(Flux.fromIterable(transactions))
                .toStream().collect(Collectors.toList());

        assertTrue(actualResult.size() > 0);
        assertEquals(4,actualResult.size());
    }

    @Test
    public void TestValidateShouldReturnAllViolationsWhenDataIsInvalid() throws IOException {
        File file = ResourceUtils.getFile("classpath:data/two-violations-per-transaction.json");
        List<FxTransaction> transactions = mapper.readValue(file, new TypeReference<>() {});

        List<CumulativeResult> actualResult = transactionService.validate(Flux.fromIterable(transactions))
                .toStream().collect(Collectors.toList());

        assertTrue(actualResult.size() > 0);
        actualResult.forEach(result -> assertEquals(2, result.getConstraintViolations().size()));
    }

}
