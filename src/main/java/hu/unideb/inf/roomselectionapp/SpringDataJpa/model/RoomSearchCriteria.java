package hu.unideb.inf.roomselectionapp.SpringDataJpa.model;

import lombok.Data;

@Data
public class RoomSearchCriteria {
    private RoomType roomType;
    private Long capacity;
    private boolean hasComputers;
    private boolean hasProjectors;
    private boolean hasWhiteBoard;
}
