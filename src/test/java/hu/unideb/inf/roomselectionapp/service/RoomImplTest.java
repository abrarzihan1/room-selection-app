package hu.unideb.inf.roomselectionapp.service;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Room;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.repository.RoomRepository;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.RoomService;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.implementation.RoomImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    }

    @AfterEach
    void tearDown() throws Exception {
            autoCloseable.close();
    }

    @Test
    void testCreateRoom() {

    }
}
