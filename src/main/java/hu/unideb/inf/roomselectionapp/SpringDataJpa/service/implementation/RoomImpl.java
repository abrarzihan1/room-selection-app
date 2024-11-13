package hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Room;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.RoomRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;


    @Override
    public String createRoom(Room room) {
        roomRepository.save(room);
        return "Success";
    }

    @Override
    public String updateRoom(Room room) {
        roomRepository.save(room);
        return "Success";
    }

    @Override
    public String deleteRoom(String id) {
        roomRepository.deleteById(id);
        return "Success";
    }

    @Override
    public Room getRoom(String id) {
       return roomRepository.findById(id).get();

    }

    @Override
    public List<Room> getAllRooms() {
       return   roomRepository.findAll();

    }


}
