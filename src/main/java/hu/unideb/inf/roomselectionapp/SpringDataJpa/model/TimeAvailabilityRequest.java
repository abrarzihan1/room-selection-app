package hu.unideb.inf.roomselectionapp.SpringDataJpa.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimeAvailabilityRequest {
    private String roomId;
    private LocalDate date;
}
