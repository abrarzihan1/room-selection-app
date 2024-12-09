package hu.unideb.inf.roomselectionapp.SpringDataJpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class Room {
    @Id
    private String roomId;
    private Long capacity;
    private boolean hasComputers;
    private boolean hasProjectors;
    private boolean hasWhiteBoard;
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    public void setId(String number) {
    }
}


