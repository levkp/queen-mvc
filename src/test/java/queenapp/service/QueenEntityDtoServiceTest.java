package queenapp.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import queenapp.presentation.dto.AlbumDto;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueenEntityDtoServiceTest {
    @Autowired
    QueenUserService userService;

    @Autowired
    QueenEntityDtoService<AlbumDto> albumService;

    @BeforeAll
    public void setup() {
        userService.create("test", new BCryptPasswordEncoder().encode("test"), false);
    }

    @Test
    void albumCreationFromDtoSucceedsAndSetsIdOfDto() {
        AlbumDto dto = new AlbumDto("My Album", LocalDate.now().toString());
        albumService.create(dto,"test");

        assertNotNull(dto.getId());
        assertDoesNotThrow(() -> albumService.findById(dto.getId()));
    }

    @Test
    void readReturnsEmptyListIfThereAreNoAlbums() {
        assertEquals(0, albumService.read().size());
    }
}
