package hu.unideb.inf.roomselectionapp.SpringDataJpa.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    private String roomId;
    private String teacherId;
    private LocalDate date;
    private LocalTime startTime;
    private String name;
}
