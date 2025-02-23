package com.energymonitor.service;

import com.energymonitor.entities.EnergyConsumption;
import java.time.LocalDateTime;
import java.util.List;

public interface EnergyService {
    EnergyConsumption recordConsumption(EnergyConsumption consumption);
    List<EnergyConsumption> getConsumptionHistory(Long userId, LocalDateTime start, LocalDateTime end);
    // More methods to be implemented
}
