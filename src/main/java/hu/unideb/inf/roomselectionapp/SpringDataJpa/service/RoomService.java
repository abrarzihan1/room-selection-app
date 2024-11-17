package hu.unideb.inf.roomselectionapp.SpringDataJpa.service;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Room;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.RoomSearchCriteria;

import java.util.List;

public interface RoomService {
    public String createRoom(Room room);
    public String updateRoom(Room room);
    public String deleteRoom(String id);
    public Room getRoom(String id);
    public List<Room> getAllRooms();
    List<Room> findRooms(RoomSearchCriteria criteria);
}
