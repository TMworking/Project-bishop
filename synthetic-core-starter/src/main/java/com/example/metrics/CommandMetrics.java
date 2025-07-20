package com.example.metrics;

import com.example.model.CommandRequest;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandMetrics {
    private final MeterRegistry meterRegistry;
    private final BlockingQueue<CommandRequest> commandQueue;
    private final ConcurrentMap<String, Counter> authorCounters = new ConcurrentHashMap<>();

    @PostConstruct
    public void initMetrics() {
        log.debug("Initializing command metrics...");
        Gauge.builder("android.command.queue.size", commandQueue::size)
                .description("Current command queue size")
                .register(meterRegistry);

        Gauge.builder("android.command.queue.remaining", commandQueue::remainingCapacity)
                .description("Remaining queue capacity")
                .register(meterRegistry);
    }

    public void incrementAuthorCounter(String author) {
        authorCounters.computeIfAbsent(author,
                key -> Counter.builder("android.commands.by.author")
                        .tag("author", author)
                        .description("Commands processed by author")
                        .register(meterRegistry)
        ).increment();
    }
}
