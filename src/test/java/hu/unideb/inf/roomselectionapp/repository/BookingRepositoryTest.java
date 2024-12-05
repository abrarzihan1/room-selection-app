package hu.unideb.inf.roomselectionapp.repository;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.BookingRepository;
import lombok.Builder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class  BookingRepositoryTest {
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
        bookingRepository.save(new Booking(6L, "R001", "T002", LocalDate.of(2024, 12, 5), LocalTime.of(12, 0), "Physics Seminar"));
        bookingRepository.save(new Booking(7L, "R001", "T003", LocalDate.of(2024, 12, 5), LocalTime.of(14, 0), "Chemistry Workshop"));

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        assertThat(bookings).hasSize(7);
    }

    @Test
    void findBookingById() {
        Optional<Booking> booking = bookingRepository.findById(1L);
        assertThat(booking).isPresent();

    }

    @Test
    void findByTeacherId(){
        List<Booking> bookingByTeacherId = bookingRepository.findByTeacherId("T001");
        assertThat(bookingByTeacherId).hasSize(2);

        assertThat(bookingByTeacherId).extracting(Booking::getRoomId)
                .containsExactlyInAnyOrder("R001", "R004");

        assertThat(bookingByTeacherId).extracting(Booking::getDate)
                .contains(LocalDate.of(2024, 12, 5),
                        LocalDate.of(2024,12,8));

        assertThat(bookingByTeacherId).extracting(Booking::getStartTime)
                .containsExactlyInAnyOrder(LocalTime.of(10,0),
                        LocalTime.of(16,0));

    }

    @Test
    void findByRoomIdAndDate(){
        List<Booking> bookingByRoomIdAndDate = bookingRepository.findByRoomIdAndDate("R001", LocalDate.of(2024, 12, 5));
        assertThat(bookingByRoomIdAndDate).hasSize(3);

        assertThat(bookingByRoomIdAndDate).extracting(Booking::getTeacherId)
                .containsExactlyInAnyOrder("T001", "T002", "T003");

        assertThat(bookingByRoomIdAndDate).extracting(Booking::getStartTime)
                .containsExactlyInAnyOrder(
                        LocalTime.of(10, 0),
                        LocalTime.of(12, 0),
                        LocalTime.of(14, 0)

                );
    }

    @Test
    void findByExistsByRoomIdAndDateAndStartTime(){
        String roomId = "R001";
        LocalDate date = LocalDate.of(2024, 12, 5);
        LocalTime startTime = LocalTime.of(10, 0);

        boolean exists = bookingRepository.existsByRoomIdAndDateAndStartTime(roomId, date, startTime);

        assertThat(exists).isTrue();


    }

    @Test
    void testFindByDate(){
        List<Booking> bookingByDate = bookingRepository.findByDate(LocalDate.of(2024, 12, 5));
        assertThat(bookingByDate).hasSize(3);
        assertThat(bookingByDate).extracting(Booking::getStartTime)
                .containsExactlyInAnyOrder(LocalTime.of(10,0),
                        LocalTime.of(14,0),
                        LocalTime.of(12,0));
    }



}
