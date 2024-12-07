package hu.unideb.inf.roomselectionapp.service;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.TimeAvailabilityRequest;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.BookingRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.BookingService;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation.BookingServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class BookingServiceImplTest {
    @Mock
    private BookingRepository bookingRepository;
    private Booking booking;
    AutoCloseable autoCloseable;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        bookingService = new BookingServiceImpl(bookingRepository);
        booking = new Booking(1L, "R101", "T001", LocalDate.of(2024, 12, 5), LocalTime.of(10, 0), "Math Class");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testSaveBooking() {

    }

    @Test
    void testFindByTeacherId(){
        when(bookingRepository.findByTeacherId("T001")).thenReturn(new ArrayList<Booking>(Collections.singletonList(booking)));
        assertThat(bookingService.findByTeacherId("T001").get(0).getName()).isEqualTo("Math Class");
    }

    @Test
    void testFindById(){
        when(bookingRepository.findById(1L)).thenReturn(Optional.ofNullable(booking));
        assertThat(bookingService.findById(1L).get().getStartTime()).isEqualTo(LocalTime.of(10,0));

    }


    @Test
    void testDeleteBookingByBookingId() {

        when(bookingRepository.findById(1L)).thenReturn(Optional.of(booking));
        boolean result = bookingService.deleteBookingByBookingId(1L);
        Assertions.assertTrue(result);
    }

    @Test
    void testDeleteBookingByBookingId_NotFound() {

        when(bookingRepository.findById(1L)).thenReturn(Optional.empty());
        boolean result = bookingService.deleteBookingByBookingId(1L);
        Assertions.assertFalse(result);
    }

    @Test
    void testFindAvailableTimes() {
        // Arrange
        TimeAvailabilityRequest request = new TimeAvailabilityRequest("R101", LocalDate.of(2024, 12, 5));
        List<LocalTime> bookedTimes = List.of(LocalTime.of(10, 0), LocalTime.of(12, 0));
        when(bookingRepository.findByRoomIdAndDate("R101", LocalDate.of(2024, 12, 5)))
                .thenReturn(List.of(
                        new Booking(2L, "R101", "T002", LocalDate.of(2024, 12, 5), LocalTime.of(10, 0), "Physics Class"),
                        new Booking(3L, "R101", "T003", LocalDate.of(2024, 12, 5), LocalTime.of(12, 0), "Chemistry Class")
                ));

        // Act
        List<LocalTime> availableTimes = bookingService.findAvailableTimes(request);

        // Assert
        List<LocalTime> expectedTimes = List.of(
                LocalTime.of(8, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0)
        );
        Assertions.assertEquals(expectedTimes, availableTimes);
    }


    @Test
    void testIsBookingConflict() {

        when(bookingRepository.existsByRoomIdAndDateAndStartTimeAndBookingIdNot("R101", LocalDate.of(2024, 12, 5), LocalTime.of(10, 0), 2L))
                .thenReturn(true);

        boolean isConflict = bookingService.isBookingConflict("R101", LocalDate.of(2024, 12, 5), LocalTime.of(10, 0), 2L);

        Assertions.assertTrue(isConflict);
    }

    @Test
    void testIsBookingConflict_NoConflict() {

        when(bookingRepository.existsByRoomIdAndDateAndStartTimeAndBookingIdNot("R101", LocalDate.of(2024, 12, 5), LocalTime.of(10, 0), 2L))
                .thenReturn(false);

        boolean isConflict = bookingService.isBookingConflict("R101", LocalDate.of(2024, 12, 5), LocalTime.of(10, 0), 2L);

        Assertions.assertFalse(isConflict);
    }




}

