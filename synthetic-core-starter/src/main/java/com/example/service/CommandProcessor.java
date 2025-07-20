package com.example.service;

import com.example.model.CommandRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommandProcessor {

    @SneakyThrows
    public void processCommand(CommandRequest request) {
        log.debug("Executing command: {}", request.getDescription());
        Thread.sleep(5000L);
    }
}
