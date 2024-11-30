package hu.unideb.inf.roomselectionapp.SpringDataJpa.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Booking;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.TimeAvailabilityRequest;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/public/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<String> saveBooking(@RequestBody Booking booking) {
        String result = bookingService.save(booking);
        if ("success".equals(result)) {
            return ResponseEntity.ok("Booking saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving booking");
        }
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Booking> getBookingByTeacher(@PathVariable String teacherId) {
        return bookingService.findByTeacherId(teacherId);
    }

    @PostMapping("/available-times")
    public List<LocalTime> getAvailableTimes(@RequestBody TimeAvailabilityRequest request) {
        return bookingService.findAvailableTimes(request);
    }
}
