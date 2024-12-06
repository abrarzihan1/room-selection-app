package hu.unideb.inf.roomselectionapp.service;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
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

    


}

