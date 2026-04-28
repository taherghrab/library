package com.library.repository;
import java.util.List;
import com.library.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByBook_Id(Long bookId);
    @Query("SELECT DAY(r.startDate), COUNT(r) " +
            "FROM Reservation r " +
            "WHERE YEAR(r.startDate) = :year AND MONTH(r.startDate) = :month " +
            "GROUP BY DAY(r.startDate) " +
            "ORDER BY DAY(r.startDate)")
    List<Object[]> countByMonth(int year, int month);
}
