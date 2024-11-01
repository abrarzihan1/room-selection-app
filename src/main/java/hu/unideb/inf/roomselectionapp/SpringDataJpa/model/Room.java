package hu.unideb.inf.roomselectionapp.SpringDataJpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room {
    @Id
    private String roomId;
    private Long capacity;
    private boolean hasComputers;
    private boolean hasProjectors;
    private boolean hasWhiteBoard;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;






}
