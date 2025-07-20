package com.example.config;

import com.example.model.CommandRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class CommandConfig {

    @Autowired
    private Environment env;

    @Bean
    public BlockingQueue<CommandRequest> commandQueue() {
        return new LinkedBlockingQueue<>(env.getProperty("command.queue.capacity", Integer.class, 10));
    }

    @Bean
    public ThreadPoolTaskExecutor commandExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(env.getProperty("command.thread-pool.core-size", Integer.class, 5));
        executor.setMaxPoolSize(env.getProperty("command.thread-pool.max-size", Integer.class, 10));
        executor.setThreadNamePrefix("command-exec-");
        executor.initialize();
        return executor;
    }
}
