package hu.unideb.inf.roomselectionapp.service;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Room;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.RoomSearchCriteria;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.RoomType;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.RoomRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.TeacherRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.RoomService;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation.RoomImpl;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation.TeacherServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
public class RoomImplTest {
    @Mock
    private RoomRepository roomRepository;
    private RoomService roomService;
    AutoCloseable autoCloseable;
    private Room room;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        roomService = new RoomImpl(roomRepository);
        room = new Room("R100", 30L, true, true, true, RoomType.CONFERENCE);
    }

    @AfterEach
    void tearDown() throws Exception {
            autoCloseable.close();
    }

    @Test
    void testCreateRoom() {
        mock(Room.class);
        mock(RoomRepository.class);
        when(roomRepository.save(room)).thenReturn(room);
        assertThat(roomService.createRoom(room)).isEqualTo("Success");
    }

    @Test
    void testUpdateRoom() {
        when(roomRepository.save(room)).thenReturn(room);
        assertThat(roomService.updateRoom(room)).isEqualTo("Success");
    }

    @Test
    void testDeleteRoom() {
        mock(Room.class);
        mock(RoomRepository.class, Mockito.CALLS_REAL_METHODS);

        doNothing().when(roomRepository).deleteById(any());
        assertThat(roomService.deleteRoom("R100")).isEqualTo("Success");
        verify(roomRepository, times(1)).deleteById(any());
    }

    @Test
    void testGetAllRooms() {
        when(roomRepository.findAll()).thenReturn(new ArrayList<Room>(Collections.singletonList(room)));
        assertThat(roomService.getAllRooms().get(0).getRoomType()).isEqualTo(RoomType.CONFERENCE);
    }

    @Test
    void testGetRoomById() {
        when(roomRepository.findById("R100")).thenReturn(Optional.ofNullable(room));
        assertThat(roomService.getRoom("R100").getRoomType()).isEqualTo(RoomType.CONFERENCE);

    }

    @Test
    void testFindRooms(){
        RoomSearchCriteria criteria = new RoomSearchCriteria();
        criteria.setRoomType(RoomType.CONFERENCE);
        criteria.setCapacity(30L);
        criteria.setHasComputers(true);
        criteria.setHasProjectors(true);
        criteria.setHasWhiteBoard(true);
        when(roomRepository.findAll()).thenReturn(Arrays.asList(room));
        List<Room> result = roomService.findRooms(criteria);

        assertThat(result).containsExactly(room);

    }

}
