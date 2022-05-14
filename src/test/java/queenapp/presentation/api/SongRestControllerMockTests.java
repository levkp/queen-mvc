package queenapp.presentation.api;

import java.util.List;
import java.util.ArrayList;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import queenapp.domain.Song;
import queenapp.service.SongService;
import queenapp.exception.EntityNotFoundException;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SongRestControllerMockTests {
    @Autowired
    MockMvc mvc;

    @MockBean
    SongService service;

    @Test
    @DisplayName("Fetching songs returns 204 No Content if there are none")
    void fetchingSongsNoContent() throws Exception {
        // Arrange
        given(service.findAll()).willReturn(new ArrayList<>());

        // Act & Assert
        mvc.perform(get("/api/songs")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Fetching songs returns 200 OK and array of songs")
    void fetchingSongsOk() throws Exception {
        // Arrange
        int id = 1;
        var song = new Song("My Song", 3.0, YearMonth.now());
        song.setId(id);
        given(service.findAll()).willReturn(List.of(song));

        // Act & Assert
        mvc.perform(get("/api/songs")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(id));
    }

    @Test
    @DisplayName("Fetching a non-existing song returns 404 Not Found")
    void fetchingSongNotFound() throws Exception {
        // Arrange
        int id = 123;
        given(service.findById(id)).willThrow(EntityNotFoundException.class);

        // Act & Assert
        mvc.perform(get("/api/songs/{id}", id)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Fetching an existing song returns 200 OK and the song data")
    void fetchingSongOk() throws Exception {
        // Arrange
        int id = 1;
        var song = new Song("My Song", 3.0, YearMonth.now());
        song.setId(id);
        given(service.findById(id)).willReturn(song);

        // Act & Assert
        mvc.perform(get("/api/songs/{id}", id)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()))
                .andExpect(jsonPath("$.id").value(id));
    }
}
