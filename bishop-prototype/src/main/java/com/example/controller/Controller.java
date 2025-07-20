package com.example.controller;

import com.example.annotation.WeylandWatchingYou;
import com.example.model.CommandRequest;
import com.example.service.CommandExecutionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/android")
@RequiredArgsConstructor
public class Controller {

    private final CommandExecutionService commandExecutionService;

    @WeylandWatchingYou
    @PostMapping("/command")
    public void submitCommand(@Valid @RequestBody CommandRequest command) {
        commandExecutionService.executeCommand(command);
    }
}
