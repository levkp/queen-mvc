package queenapp.service;

import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import queenapp.domain.Album;
import queenapp.domain.QueenUser;
import queenapp.domain.Song;
import queenapp.presentation.dto.AlbumDto;
import queenapp.repository.QueenEntityRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
public class AlbumServiceMockingTests {
    @Autowired
    AlbumService sut;

    // Has to be a real service, because there is a foreign key constraint between the album and app_user tables
    @Autowired
    QueenUserService userService;

    @MockBean
    QueenEntityService<Song> songService;

    @MockBean
    QueenEntityRepository<Album> albumRepository;

    @Test
    void createFromDto() {
        // Arrange
        var dto = new AlbumDto("My Album", "My description", YearMonth.now().toString(), List.of(1));

//        given(songService.findById(1)).wil?

        // Act
        sut.createFromDto(dto, "standard");

        // Assert
    }
}
