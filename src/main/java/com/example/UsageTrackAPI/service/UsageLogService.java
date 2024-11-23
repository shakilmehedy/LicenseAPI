package com.example.UsageTrackAPI.service;

import ch.qos.logback.core.status.Status;
import com.example.UsageTrackAPI.model.UsageLog;
import com.example.UsageTrackAPI.repository.UsageRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class UsageLogService {

    private final UsageRepository usageLogRepository;
    private final RestTemplate restTemplate;

    public UsageLogService (UsageRepository usageLogRepository, RestTemplate restTemplate) {
        this.usageLogRepository = usageLogRepository;
        this.restTemplate = restTemplate;
    }

    public boolean trackUsage(String binNumber, String license, String activityDescription) {
        // Call LicenseAPI to validate the license
        String validateUrl = "http://localhost:8081/license/validate?binNumber="
                + binNumber + "&license=" + license;

        Boolean isValid = restTemplate.getForObject(validateUrl, Boolean.class);

        // Log the usage
        UsageLog usageLog = new UsageLog();
        usageLog.setBinNumber(binNumber);
        usageLog.setLicense(license);
        usageLog.setActivityDescription(activityDescription);
        usageLog.setTimestamp(LocalDateTime.now());

        usageLogRepository.save(usageLog);

        // Return validation result
        return Boolean.TRUE.equals(isValid);
    }
}