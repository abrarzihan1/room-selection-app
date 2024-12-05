package hu.unideb.inf.roomselectionapp.SpringDataJpa.repository;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByTeacherId(String teacherId);
    List<Booking> findByRoomIdAndDate(String roomId, LocalDate date);
    boolean existsByRoomIdAndDateAndStartTime(String roomId, LocalDate date, LocalTime startTime);
    boolean existsByRoomIdAndDateAndStartTimeAndBookingIdNot(String roomId, LocalDate date, LocalTime startTime, long bookingId);
    List<Booking> findByDate(LocalDate date);
}
