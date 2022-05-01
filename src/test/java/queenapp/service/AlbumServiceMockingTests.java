package queenapp.service;

import queenapp.domain.Album;
import queenapp.domain.Song;
import queenapp.presentation.dto.AlbumDto;
import queenapp.repository.QueenEntityRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.YearMonth;
import java.util.HashSet;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AlbumServiceMockingTests {
    @Autowired
    private AlbumService sut;

    @MockBean
    private QueenEntityRepository<Album> albumRepository;

    @MockBean
    private QueenEntityService<Song> songService;


    @Test
    void createFromDtoShouldCreateAlbum() {
        // Arrange
        int songId = 1;
        var song = new Song("My Song", 1.0, new HashSet<>(), YearMonth.now(), null);
        var dto = new AlbumDto("My Album", "Test description", "2022-04-30", List.of(songId));
        given(songService.findById(songId)).willReturn(song);

        // Act
//        sut.createFromDto(dto, )

        // Assert


    }
}
