package com.energymonitor.dto.energy;

import lombok.Data;
import java.util.Map;

@Data
public class DashboardDto {
    private Double totalConsumption;
    private Double totalCost;
    private Map<String, Double> consumptionByDevice;
    private Map<String, Double> consumptionByLocation;
}