package com.library.service;

import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import com.library.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public Map<String, Long> getStats() {

        Map<String, Long> data = new HashMap<>();

        data.put("books", bookRepository.count());
        data.put("users", userRepository.count());
        data.put("reservations", reservationRepository.count());

        return data;
    }
    public Map<Integer, Long> reservationsByMonth(int year, int month) {

        List<Object[]> data = reservationRepository.countByMonth(year, month);

        Map<Integer, Long> result = new LinkedHashMap<>();

        for (Object[] row : data) {
            Integer day = (Integer) row[0];
            Long count = (Long) row[1];
            result.put(day, count);
        }

        return result;
    }
}