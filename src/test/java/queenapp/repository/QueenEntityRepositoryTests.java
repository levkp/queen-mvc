package queenapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import queenapp.domain.Album;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class QueenEntityRepositoryTests {
    @Autowired
    private QueenEntityRepository<Album> albumRepository;

    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
    }

    @Test
    void albumTitleIsUnique() {
        Album a = new Album("My Album", LocalDate.now());
        albumRepository.create(a);

        assertThrows(DataIntegrityViolationException.class, () -> {
            Album a2 = new Album("My Album", LocalDate.now());
            albumRepository.create(a2);
        });
    }
}
