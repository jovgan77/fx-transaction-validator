package com.codingtest.fxtransactionvalidator.config;

import com.codingtest.fxtransactionvalidator.config.properties.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final ApplicationProperties properties;

    @Bean
    public TaskExecutor taskExecutor() {
        int poolSize = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(poolSize);
        taskExecutor.setMaxPoolSize(poolSize);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(properties.getAwaitTerminationSeconds());
        taskExecutor.initialize();
        return taskExecutor;
    }

}
