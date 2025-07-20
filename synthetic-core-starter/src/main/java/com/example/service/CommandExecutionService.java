package com.example.service;

import com.example.metrics.CommandMetrics;
import com.example.model.CommandPriority;
import com.example.model.CommandRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandExecutionService {
    private final CommandQueueService queueManager;
    private final CommandProcessor commandProcessor;
    private final CommandMetrics commandMetrics;

    public void executeCommand(CommandRequest request) {
        if (request.getCommandPriority() == CommandPriority.CRITICAL) {
            commandProcessor.processCommand(request);
        } else {
            queueManager.addToQueue(request);
        }
        commandMetrics.incrementAuthorCounter(request.getAuthor());
    }
}
