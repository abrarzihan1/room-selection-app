package hu.unideb.inf.roomselectionapp.repository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Room;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.RoomType;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.RoomRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;
    private Room room1, room2, room3;


    @BeforeEach
    void setUp() {
        room1 = new Room("R001", 100L, true, true, true, RoomType.LABROOM);
        room2 = new Room("R002", 20L, false, false, true, RoomType.CONFERENCE);
        room3 = new Room("R003", 50L, true, true, false, RoomType.CONFERENCE);

        roomRepository.saveAll(Arrays.asList(room1, room2, room3));
    }

    @AfterEach
    void tearDown() {
        roomRepository.deleteAll();

    }

    @Test
    void testFindById(){
        Optional<Room> room = roomRepository.findById("R001");
        assertThat(room).isNotNull();
        assertThat(room.get().getRoomId()).isEqualTo("R001");


    }

    @Test
    void testFindRoomWithComputers(){
        List<Room> roomHasComputers = roomRepository.findByHasComputers(true);
        assertThat(roomHasComputers).contains(room1, room3);

    }


    @Test
    void  testFindByRoomType(){
        List<Room> roomWithSameRoomType = roomRepository.findByRoomType(RoomType.CONFERENCE);
        assertThat(roomWithSameRoomType).contains(room2, room3);
    }







}
