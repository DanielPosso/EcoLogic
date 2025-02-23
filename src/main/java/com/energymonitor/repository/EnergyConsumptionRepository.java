// EnergyConsumptionRepository - Handles database operations for EnergyConsumption entity
package com.energymonitor.repository;

import com.energymonitor.entities.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, Long> {
    //Find consumption records for a user within a date range
    List<EnergyConsumption> findByUserIdAndTimestampBetween(
            Long userId,
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    //Find consumption records for a user by device type
    List<EnergyConsumption> findByUserIdAndDeviceType(
            Long userId,
            String deviceType
    );
}
