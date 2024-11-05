package hu.unideb.inf.roomselectionapp.SpringDataJpa.repository;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, String> {
}
