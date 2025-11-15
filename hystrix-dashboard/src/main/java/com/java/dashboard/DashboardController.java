package com.java.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {

    @GetMapping("/")
    public String home() {
        return "Monitoring Dashboard is running on port 9411";
    }

    @GetMapping("/health")
    public String health() {
        return "Dashboard is healthy";
    }
}