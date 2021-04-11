package com.codingtest.fxtransactionvalidator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class VanillaOptionFxTransaction extends FxTransaction {

    @JsonProperty("style")
    private String style;

    @JsonProperty("strategy")
    private String strategy;

    @JsonProperty("deliveryDate")
    private LocalDate valueDate;

    @JsonProperty("excerciseStartDate")
    private LocalDate excerciseStartDate;

    @JsonProperty("expiryDate")
    private LocalDate expiryDate;

    @JsonProperty("payCcy")
    private String payCcy;

    @JsonProperty("premium")
    private BigDecimal premium;

    @JsonProperty("premiumCcy")
    private String premiumCcy;

    @JsonProperty("premiumType")
    private String premiumType;

    @JsonProperty("premiumDate")
    private LocalDate premiumDate;

}
