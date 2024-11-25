package hu.unideb.inf.roomselectionapp.SpringDataJpa.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomSearchCriteria {
    private RoomType roomType;
    private Long capacity;
    private boolean hasComputers;
    private boolean hasProjectors;
    private boolean hasWhiteBoard;
    private LocalDate date;
}
