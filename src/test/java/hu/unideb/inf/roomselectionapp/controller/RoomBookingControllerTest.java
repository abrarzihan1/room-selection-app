package hu.unideb.inf.roomselectionapp.controller;

import hu.unideb.inf.roomselectionapp.SpringDataJpa.controller.RoomBookingController;
import hu.unideb.inf.roomselectionapp.security.util.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomBookingController.class)
@TestPropertySource(properties = "security.jwt.enabled=false")
@AutoConfigureMockMvc(addFilters = false)
public class RoomBookingControllerTest {

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetAvailableTimes_withSpecificDate() throws Exception {
        // Test with the specific date "2024-11-27"
        mockMvc.perform(get("/api/available-times")
                        .param("date", "2024-11-27"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"9:15\", \"10:00\", \"10:15\"]"));
    }

    @Test
    void testGetAvailableTimes_withDifferentDate() throws Exception {
        // Test with a different date, e.g., "2024-11-28"
        mockMvc.perform(get("/api/available-times")
                        .param("date", "2024-11-28"))
                .andExpect(status().isOk())
                .andExpect(content().json("[\"8:00\", \"9:00\", \"11:00\", \"14:00\"]"));
    }
}

