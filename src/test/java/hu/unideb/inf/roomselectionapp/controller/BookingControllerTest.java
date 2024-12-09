package hu.unideb.inf.roomselectionapp.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.controller.BookingController;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.TimeAvailabilityRequest;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private Booking booking;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();

        booking = new Booking();
        booking.setBookingId(1L);
        booking.setRoomId("Room1");
        booking.setTeacherId("T123");
        booking.setDate(LocalDate.now());
        booking.setStartTime(LocalTime.of(10, 0));
        booking.setName("Test Booking");
    }

    @Test
    public void testSaveBooking_Success() throws Exception {
        // Given
        when(bookingService.save(any(Booking.class))).thenReturn("success");

        // When & Then
        mockMvc.perform(post("/api/private/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"roomId\":\"Room1\", \"teacherId\":\"T123\", \"date\":\"2024-12-09\", \"startTime\":\"10:00\", \"name\":\"Test Booking\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Booking saved successfully"));

        verify(bookingService, times(1)).save(any(Booking.class));
    }
    @Test
    public void testSaveBooking_Error() throws Exception {
        // Given
        when(bookingService.save(any(Booking.class))).thenReturn("A booking already exists for the selected room, date, and time.");

        // When & Then
        mockMvc.perform(post("/api/private/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"roomId\":\"Room1\", \"teacherId\":\"T123\", \"date\":\"2024-12-09\", \"startTime\":\"10:00\", \"name\":\"Test Booking\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error saving booking"));

        verify(bookingService, times(1)).save(any(Booking.class));
    }

}