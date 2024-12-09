package hu.unideb.inf.roomselectionapp.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.controller.RoomController;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.Room;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.RoomSearchCriteria;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.model.RoomType;
import hu.unideb.inf.roomselectionapp.SpringDataJpa.service.RoomService;
import hu.unideb.inf.roomselectionapp.security.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
@TestPropertySource(properties = "security.jwt.enabled=false")
@AutoConfigureMockMvc(addFilters = false)
class RoomControllerTest {

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    @Test
    void testGetRoom() throws Exception {
        Room mockRoom = new Room("1", 50L, true, true, true, RoomType.CONFERENCE);

        Mockito.when(roomService.getRoom("1")).thenReturn(mockRoom);

        mockMvc.perform(get("/api/private/room/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value("1"))
                .andExpect(jsonPath("$.capacity").value(50))
                .andExpect(jsonPath("$.hasComputers").value(true))
                .andExpect(jsonPath("$.roomType").value("CONFERENCE"));
    }

    @Test
    void testCreateRoom() throws Exception {
        Room mockRoom = new Room("2", 30L, false, true, false, RoomType.CLASSROOM);

        // Correctly stubbing the `createRoom` method
        Mockito.doAnswer(invocation -> null).when(roomService).createRoom(any(Room.class));

        mockMvc.perform(post("/api/private/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "roomId": "2",
                                "capacity": 30,
                                "hasComputers": false,
                                "hasProjectors": true,
                                "hasWhiteBoard": false,
                                "roomType": "CLASSROOM"
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(content().string("Room Added Successfully"));
    }

    @Test
    void testSearchRooms() throws Exception {
        Room mockRoom1 = new Room("1", 50L, true, true, true, RoomType.CONFERENCE);
        Room mockRoom2 = new Room("2", 30L, false, true, false, RoomType.CLASSROOM);
        List<Room> mockRooms = Arrays.asList(mockRoom1, mockRoom2);

        Mockito.when(roomService.findRooms(any(RoomSearchCriteria.class))).thenReturn(mockRooms);

        mockMvc.perform(post("/api/private/room/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "capacity": 20,
                                    "hasComputers": false,
                                    "hasProjectors": true,
                                    "hasWhiteBoard": false,
                                    "roomType": "CLASSROOM"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].roomId").value("1"))
                .andExpect(jsonPath("$[1].roomId").value("2"));
    }

    @Test
    void testGetAllRooms() throws Exception {
        Room mockRoom1 = new Room("1", 50L, true, true, true, RoomType.CONFERENCE);
        Room mockRoom2 = new Room("2", 30L, false, true, false, RoomType.CLASSROOM);
        List<Room> mockRooms = Arrays.asList(mockRoom1, mockRoom2);

        Mockito.when(roomService.getAllRooms()).thenReturn(mockRooms);

        mockMvc.perform(get("/api/private/room/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].roomId").value("1"))
                .andExpect(jsonPath("$[1].roomId").value("2"));
    }

}