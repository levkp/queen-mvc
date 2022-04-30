package queenapp.presentation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queenapp.domain.Song;
import queenapp.presentation.dto.QueenEntityDtoMapper;
import queenapp.presentation.dto.SongDto;
import queenapp.service.SongService;

@RestController
@RequestMapping("/api/songs")
public class SongRestController {
    private final SongService service;
    private final QueenEntityDtoMapper<SongDto, Song> mapper;

    @Autowired
    public SongRestController(SongService service, QueenEntityDtoMapper<SongDto, Song> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("{id}")
    public ResponseEntity<SongDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }
}
