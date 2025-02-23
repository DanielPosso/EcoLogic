package com.energymonitor.service;

import com.energymonitor.entities.Device;
import java.util.List;

public interface DeviceService {
    Device addDevice(Device device);
    List<Device> getUserDevices(Long userId);
    void removeDevice(Long deviceId);
    // More methods to be implemented
}
