package queenapp.presentation.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import queenapp.domain.Album;
import queenapp.exception.EntityNotFoundException;
import queenapp.service.QueenEntityService;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AlbumRestControllerMockTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private QueenEntityService<Album> service;

    @Test
    void fetchingExistingAlbumReturnsOk() throws Exception {
        LocalDate release = LocalDate.now();
        Album a = new Album("Test Album", release);
        given(service.findById(398)).willReturn(a);

        mvc.perform(get("/api/albums/{id}", 398)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(header().string(CONTENT_TYPE, MediaType.APPLICATION_JSON.toString()))
                .andExpect(jsonPath("$.body").value(release));
    }

    @Test
    void fetchingNonExistingAlbumReturnsNotFound() throws Exception {
        given(service.findById(398)).willThrow(EntityNotFoundException.class);

        mvc.perform(get("/api/albums/{id}", 398)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void fetchingAlbumsReturnsNoContentIfThereAreNone() throws Exception {
        given(service.read()).willReturn(new ArrayList<>());

        mvc.perform(get("/api/albums")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
