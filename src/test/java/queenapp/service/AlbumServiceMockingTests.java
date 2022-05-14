package queenapp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import queenapp.domain.Album;
import queenapp.exception.EntityNotFoundException;
import queenapp.repository.QueenEntityRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ActiveProfiles("test")
public class AlbumServiceMockingTests {
    @Autowired
    AlbumService sut;

    @MockBean
    QueenEntityRepository<Album> albumRepository;

    @Test
    @DisplayName("Retrieving by existing id returns album")
    void findByExistingId() {
        // Arrange
        int id = 1;
        var album = new Album();
        given(albumRepository.findById(id)).willReturn(Optional.of(album));

        // Act
        var returned = sut.findById(id);

        // Assert
        assertEquals(id, returned.getId());
    }

    @Test
    @DisplayName("Retrieving by non-existing id throws EntityNotFoundException")
    void findByNonExistingId() {
        // Arrange
        int id = 1;
        given(albumRepository.findById(id)).willReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> sut.findById(id));
    }
}
