package queenapp.presentation.api;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import queenapp.domain.Album;
import queenapp.domain.Genre;
import queenapp.domain.Song;
import queenapp.repository.QueenEntityRepository;
import queenapp.service.QueenUserService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlbumRestControllerIntegrationTests {
    static final String ADMIN = "adminTester";
    static final String STANDARD = "standardTester";
    int albumId;
    int numberOfAlbumsBefore;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    QueenEntityRepository<Album> albumRepository;

    @Autowired
    QueenUserService userService;

    @BeforeAll
    void beforeAll() {
        userService.create(ADMIN, "secret", true);
        userService.create(STANDARD, "secret", false);
    }

    @BeforeEach
    void beforeEach() {
        // Arrange
        var album = new Album("My Album", LocalDate.now(), userService.findByUsername(ADMIN));
        album.addSong(new Song("My Song", 3.2, Set.of(Genre.HARD_ROCK), YearMonth.now(), album));
        albumRepository.create(album);
        albumId = album.getId();

        numberOfAlbumsBefore = 1;
    }

    @AfterEach
    void afterEach() {
        albumRepository.deleteAll();
    }

    @Test
    @DisplayName("Fetching an album does not fetch its songs")
    public void fetchingAlbumsDoesNotFetchFullSongs() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/albums/{id}", albumId).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()))
            .andExpect(jsonPath("$.songs").doesNotExist())
            .andExpect(jsonPath("$..songIds").isArray())
            .andExpect(jsonPath("$..songIds").isNotEmpty());
    }

    @Test
    @WithUserDetails(ADMIN)
    @DisplayName("Deleting all as admin gets no content and the number of albums is 0")
    void deletingAllGetsNoContentIfUserIsAdmin() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/albums"))
                .andExpect(status().isNoContent());
        assertEquals(albumRepository.findAll().size(), 0);
    }

    @Test
    @WithUserDetails(STANDARD)
    @DisplayName("Deleting all as standard gets forbidden and the number of albums does not change")
    void deletingAllStandard() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/albums"))
                .andExpect(status().isForbidden());
        assertTrue(albumRepository.findAll().size() > 0);
    }


    @Test
    @DisplayName("Deleting all as unauthenticated gets forbidden and the number of albums does not change")
    void deletingAllNoAuth() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/albums"))
                .andExpect(status().isForbidden());
        assertEquals(albumRepository.findAll().size(), numberOfAlbumsBefore);
    }



//    @Test
//    void fetchingAlbumsGetsNoContentIfThereAreNone() throws Exception {
//        mockMvc.perform(get("/api/albums").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
}
