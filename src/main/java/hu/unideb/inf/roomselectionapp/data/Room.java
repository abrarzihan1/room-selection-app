package hu.unideb.inf.roomselectionapp.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Room {
    @Id
    @Column(name = "Room Id")
    private String room_id;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "location")
    private String location;



    public Room(String room_id, int capacity, String location) {
        this.room_id = room_id;
        this.capacity = capacity;
        this.location = location;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String  room_id) {
        this.room_id = room_id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return room_id == room.room_id && capacity == room.capacity && Objects.equals(location, room.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room_id, capacity, location);
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                '}';
    }
}
