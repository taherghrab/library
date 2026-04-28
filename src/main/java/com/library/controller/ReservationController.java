package com.library.controller;

import com.library.model.Reservation;
import com.library.model.User;
import com.library.service.ReservationService;
import com.library.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public Reservation add(@RequestBody Reservation reservation,
                           java.security.Principal principal) {

        // 🔥 نجيب email من JWT
        String email = principal.getName();

        System.out.println("🔥 USER FROM TOKEN: " + email);

        return reservationService.save(reservation, email);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public List<Reservation> getAll() {
        return reservationService.getAll();
    }
}