package hu.unideb.inf.roomselectionapp.SpringDataJpa.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimeAvailabilityRequest {
    private String roomId;
    private LocalDate date;

    public TimeAvailabilityRequest(String roomId, LocalDate date) {
        this.roomId = roomId;
        this.date = date;
    }
}
