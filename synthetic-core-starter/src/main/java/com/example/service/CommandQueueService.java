package com.example.service;

import com.example.exception.QueueFullException;
import com.example.model.CommandRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;

@Service
@RequiredArgsConstructor
public class CommandQueueService {
    private final BlockingQueue<CommandRequest> commandQueue;
    private final ThreadPoolTaskExecutor commandExecutor;
    private final CommandProcessor commandProcessor;

    public void addToQueue(CommandRequest command) {
        if (commandQueue.remainingCapacity() == 0) {
            throw new QueueFullException("Command queue overflow");
        }
        commandQueue.add(command);
    }

    @PostConstruct
    public void startCommandProcessing() {
        commandExecutor.execute(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    CommandRequest command = commandQueue.take();
                    commandProcessor.processCommand(command);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }
}
