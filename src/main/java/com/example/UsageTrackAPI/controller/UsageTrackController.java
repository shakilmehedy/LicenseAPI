package com.example.UsageTrackAPI.controller;

import ch.qos.logback.core.status.Status;
import com.example.UsageTrackAPI.service.UsageLogService;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usage-track")
public class UsageTrackController {
    private final UsageLogService usageLogService;

    public UsageTrackController(UsageLogService usageLogService) {
        this.usageLogService = usageLogService;
    }
    @PostMapping("/log")
    public ResponseEntity<String> logUsage(
            @RequestParam String binNumber,
            @RequestParam String license,
            @RequestParam String activityDescription) {

        boolean isValid = usageLogService.trackUsage(binNumber, license, activityDescription);

        if (isValid) {
            return ResponseEntity.ok("Usage logged and license is valid.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("License is invalid or expired.");
        }}
}
