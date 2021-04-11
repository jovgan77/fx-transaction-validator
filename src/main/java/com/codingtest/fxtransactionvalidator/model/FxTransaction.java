package com.codingtest.fxtransactionvalidator.model;

import com.codingtest.fxtransactionvalidator.model.constant.FxTransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SpotFxTransaction.class, name = FxTransactionType.SPOT),
        @JsonSubTypes.Type(value = ForwardFxTransaction.class, name = FxTransactionType.FORWARD),
        @JsonSubTypes.Type(value = VanillaOptionFxTransaction.class, name = FxTransactionType.VANILLA_OPTION)
})
public class FxTransaction {

    @JsonProperty("type")
    private String type;

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("ccyPair")
    private String ccyPair;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("tradeDate")
    private LocalDate tradeDate;

    @JsonProperty("amount1")
    private BigDecimal amount1;

    @JsonProperty("amount2")
    private BigDecimal amount2;

    @JsonProperty("rate")
    private BigDecimal rate;

    @JsonProperty("valueDate")
    private LocalDate valueDate;

    @JsonProperty("legalEntity")
    private String legalEntity;

    @JsonProperty("trader")
    private String trader;

}
