package queenapp.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import queenapp.domain.Album;
import queenapp.domain.QueenUser;
import queenapp.service.QueenUserService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueenEntityRepositoryTests {
    private final String TEST_TITLE = "Test Album";
    private final String TEST_UNAME = "test";

    @Autowired
    QueenUserService userService;

    @Autowired
    QueenEntityRepository<Album> albumRepository;

    @BeforeAll
    void beforeAll() {
        userService.create(TEST_UNAME, new BCryptPasswordEncoder().encode("test"), false);
    }

    @BeforeEach
    void beforeEach() {
        QueenUser owner = userService.findByUsername(TEST_UNAME);
        albumRepository.create(new Album(TEST_TITLE, LocalDate.now(), owner));
    }

    @Test
    void albumTitleIsUnique() {
        assertThrows(DataIntegrityViolationException.class, () ->
            albumRepository.create(new Album(TEST_TITLE, LocalDate.now()))
        );
    }

    @Test
    void albumReleaseIsNotNullable() {
        assertThrows(DataIntegrityViolationException.class, () ->
                albumRepository.create(new Album("My Album", null))
        );
    }

    @Test
    void creatingAlbumCanSetOwner() {
        assertNotNull(albumRepository.findByTitle(TEST_TITLE).get().getOwner());
    }

    @Test
    void deletingAlbumDoesNotDeleteOwner() {
        albumRepository.delete(albumRepository.findByTitle(TEST_TITLE).get());
        assertNotNull(userService.findByUsername(TEST_UNAME));
    }
}
