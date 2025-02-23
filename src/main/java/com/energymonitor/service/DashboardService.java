package com.energymonitor.service;

import java.time.LocalDateTime;
import java.util.Map;

public interface DashboardService {
    Map<String, Object> getDashboardData(Long userId);
    Map<String, Object> getConsumptionTrends(Long userId, LocalDateTime start, LocalDateTime end);
    // More methods to be implemented
}
