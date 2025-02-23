package com.energymonitor.repository;

import com.energymonitor.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUserId(Long userId);
    List<Device> findByUserIdAndType(Long userId, String type);
}
