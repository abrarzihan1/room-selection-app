package hu.unideb.inf.roomselectionapp.SpringDataJpa.repository;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByTeacherId(String teacherId);
    List<Booking> findByRoomIdAndDate(String roomId, LocalDate date);
}
