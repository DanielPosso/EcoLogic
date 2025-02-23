package com.energymonitor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/energy")
public class EnergyController {
    // Will handle:
    // - Record energy consumption
    // - Get consumption history
    // - Get consumption analytics
    // - Device management
}
