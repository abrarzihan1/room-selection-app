package hu.unideb.inf.roomselectionapp.repository;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.BookingRepository;
import lombok.Builder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class BookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;
    private Booking booking;

    @BeforeEach
    void setUp() {
        bookingRepository.deleteAll();
        bookingRepository.save(new Booking(1L, "R001", "T001", LocalDate.of(2024, 12, 5), LocalTime.of(10, 0), "Math Lecture"));
        bookingRepository.save(new Booking(2L, "R002", "T002", LocalDate.of(2024, 12, 6), LocalTime.of(12, 0), "Physics Lab"));
        bookingRepository.save(new Booking(3L, "R003", "T003", LocalDate.of(2024, 12, 7), LocalTime.of(14, 0), "Chemistry Lecture"));
        bookingRepository.save(new Booking(4L, "R004", "T001", LocalDate.of(2024, 12, 8), LocalTime.of(16, 0), "Computer Science Class"));
        bookingRepository.save(new Booking(5L, "R005", "T004", LocalDate.of(2024, 12, 9), LocalTime.of(9, 0), "Biology Seminar"));
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        assertThat(bookings).hasSize(5);
    }
}
