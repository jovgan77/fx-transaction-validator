package com.codingtest.fxtransactionvalidator.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "validation")
@Getter
@Setter
public class ValidationProperties {

    private List<String> supportedCustomers;

}
