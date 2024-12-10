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
import java.util.Optional;

@RestController
@RequestMapping("/api/private/booking")
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

    @GetMapping("/booking/{bookingId}")
    public Optional<Booking> getBookingById(@PathVariable long bookingId) {

        return bookingService.findById(bookingId);
    }

    @PostMapping("/available-times")
    public List<LocalTime> getAvailableTimes(@RequestBody TimeAvailabilityRequest request) {
        return bookingService.findAvailableTimes(request);
    }

    @DeleteMapping("/booking/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable long bookingId) {
        try {
            boolean isDeleted = bookingService.deleteBookingByBookingId(bookingId);
            if (isDeleted) {
                return ResponseEntity.ok("Booking deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting booking");
        }
    }

    @PutMapping("/booking/{bookingId}")
    public ResponseEntity<String> updateBooking(@PathVariable long bookingId, @RequestBody Booking booking) {
        try {
            Optional<Booking> existingBookingOptional = bookingService.findById(bookingId);

            if (!existingBookingOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
            }

            Booking existingBooking = existingBookingOptional.get();

            boolean isConflict = bookingService.isBookingConflict(
                    existingBooking.getRoomId(),
                    booking.getDate(),
                    booking.getStartTime(),
                    bookingId
            );

            if (isConflict) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("A booking already exists for the selected room, date, and time.");
            }

            existingBooking.setDate(booking.getDate());
            existingBooking.setStartTime(booking.getStartTime());
            bookingService.save(existingBooking);
            return ResponseEntity.ok("Booking updated successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating booking");
        }
    }
}
