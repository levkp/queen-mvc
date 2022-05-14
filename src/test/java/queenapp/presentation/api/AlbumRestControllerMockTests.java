package queenapp.presentation.api;

import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import queenapp.presentation.dto.AlbumDto;
import queenapp.service.AlbumService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AlbumRestControllerMockTests {
    @Autowired
    MockMvc mvc;

    @MockBean
    AlbumService service;

    // Todo: why does this call return 400 when authenticated, and why 200 when not?! Expected is 201
    @Test
    @DisplayName("Creating new album with valid DTO returns 201 Created and the album data")
    @WithMockUser("tester")
    void createFromDto() throws Exception {
        // Arrange
        var title = "My album";
        var dto = new AlbumDto(title, "1979-06");
        String requestBody = new GsonBuilder().create().toJson(dto);

        // Act & Assert
        mvc.perform(post("/api/albums", dto)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.title").value(title));

        ArgumentCaptor<AlbumDto> captor = ArgumentCaptor.forClass(AlbumDto.class);
        verify(service).createFromDto(captor.capture());

        var capturedDto = captor.getValue();
        assertEquals(capturedDto.getOwnerName(), "tester");
    }
}
