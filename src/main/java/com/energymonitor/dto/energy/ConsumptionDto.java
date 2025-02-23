package com.energymonitor.dto.energy;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ConsumptionDto {
    private Double consumption;
    private LocalDateTime timestamp;
    private String deviceType;
    private String location;
}
