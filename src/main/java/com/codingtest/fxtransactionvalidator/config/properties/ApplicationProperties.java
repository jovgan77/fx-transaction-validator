package com.codingtest.fxtransactionvalidator.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
@Getter
@Setter
public class ApplicationProperties {

    private Integer awaitTerminationSeconds;

}
