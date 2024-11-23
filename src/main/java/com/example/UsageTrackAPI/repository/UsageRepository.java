package com.example.UsageTrackAPI.repository;

import com.example.UsageTrackAPI.model.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageRepository extends JpaRepository<UsageLog,Integer> {
}

