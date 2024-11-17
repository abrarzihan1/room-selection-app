package hu.unideb.inf.roomselectionapp.SpringDataJpa.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Room;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.RoomSearchCriteria;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/get/{roomId}")
    public Room getRoom(@PathVariable String roomId) {
        return roomService.getRoom(roomId);
    }

    @PostMapping
    public String createRoom(@RequestBody Room room) {
        roomService.createRoom(room);
        return "Room Added Successfully";
    }

    @PostMapping("/search")
    public List<Room> searchRooms(@RequestBody RoomSearchCriteria criteria) {
        return roomService.findRooms(criteria);
    }
}
