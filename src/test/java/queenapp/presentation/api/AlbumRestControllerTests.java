package queenapp.presentation.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import queenapp.domain.Album;
import queenapp.domain.Genre;
import queenapp.domain.Song;
import queenapp.repository.QueenEntityRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumRestControllerTests {
    private final String TEST_TITLE = "Test Album";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    QueenEntityRepository<Album> albumRepository;

    @Test
    public void fetchingAlbumsDoesNotFetchFullSongs() throws Exception {
        Album a = new Album("My Album", LocalDate.now());
        a.addSongs(new Song("My Song", 3.2, Set.of(Genre.HARD_ROCK), YearMonth.now(), a));
        albumRepository.create(a);

        mockMvc.perform(get("/api/albums").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()))
            .andExpect(jsonPath("$.songs").doesNotExist())
            .andExpect(jsonPath("$..songIds").isArray())
            .andExpect(jsonPath("$..songIds").isNotEmpty());
    }

    @Test
    void fetchingAlbumsGetsNoContentIfThereAreNone() throws Exception {
        mockMvc.perform(get("/api/albums").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }



}
