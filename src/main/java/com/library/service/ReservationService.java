package com.library.service;

import com.library.model.Reservation;
import com.library.model.User;
import com.library.repository.ReservationRepository;
import com.library.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public Reservation save(Reservation reservation, String email) {

        // 🟢 1. نجيب user من JWT
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🟢 2. نربط user بالreservation
        reservation.setUser(user);

        // 🟢 3. validation
        if (reservation.getBook() == null || reservation.getBook().getId() == null) {
            throw new RuntimeException("Book is required");
        }

        if (reservation.getStartDate() == null || reservation.getEndDate() == null) {
            throw new RuntimeException("Dates are required");
        }

        // 🟢 4. check conflict
        List<Reservation> reservations =
                reservationRepository.findByBook_Id(reservation.getBook().getId());

        for (Reservation r : reservations) {
            if (!r.getEndDate().isBefore(reservation.getStartDate())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "This book is already reserved for this period"
                );
            }
        }

        // 🟢 5. save
        return reservationRepository.save(reservation);
    }
}