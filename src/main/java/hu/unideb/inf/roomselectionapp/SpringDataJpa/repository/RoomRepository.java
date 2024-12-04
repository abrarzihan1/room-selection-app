package hu.unideb.inf.roomselectionapp.SpringDataJpa.repository;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Room;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, String> {
    List<Room> findByHasComputers(Boolean hasComputers);
    List<Room> findByRoomType(RoomType roomType);

}
