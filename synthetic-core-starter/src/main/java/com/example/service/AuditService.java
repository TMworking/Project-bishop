package com.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${audit.mode:console}")
    private String auditMode;

    @Value("${audit.kafka.topic:android-audit-topic}")
    private String kafkaTopic;

    public void publish(String method, Object[] args, Object result, long duration) {
        String message = String.format(
                "Method: %s | Args: %s | Result: %s | Time: %dms",
                method, Arrays.toString(args), result, duration
        );

        if ("kafka".equals(auditMode)) {
            kafkaTemplate.send(kafkaTopic, message);
        } else {
            log.info("[AUDIT] {}", message);
        }
    }
}
