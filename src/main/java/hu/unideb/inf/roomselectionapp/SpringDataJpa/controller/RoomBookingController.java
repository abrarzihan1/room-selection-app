package hu.unideb.inf.roomselectionapp.SpringDataJpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class RoomBookingController {
    @GetMapping("/api/available-times")
    public List<String> getAvailableTimes(@RequestParam("date") String date) {
        LocalDate selectedDate = LocalDate.parse(date);

        // Logic to check if rooms are booked for the selected date
        // For simplicity, we’re using a static list. In a real application,
        // you’d query your database to find available slots for this date.
        if (selectedDate.equals(LocalDate.of(2024, 11, 27))) {
            return Arrays.asList("9:15", "10:00", "10:15");
        } else {
            return Arrays.asList("8:00", "9:00", "11:00", "14:00");
        }
    }
}
