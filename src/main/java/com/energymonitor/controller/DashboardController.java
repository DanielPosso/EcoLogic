package com.energymonitor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    // Will handle:
    // - Summary statistics
    // - Charts data
    // - Cost analysis
}
