package com.library.controller;

import com.library.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") // 🔥 مهم جداً
    public Map<String, Long> stats() {
        return dashboardService.getStats();
    }
    @GetMapping("/reservations-by-month")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<Integer, Long> byMonth(
            @RequestParam int year,
            @RequestParam int month) {

        return dashboardService.reservationsByMonth(year, month);
    }
}