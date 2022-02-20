package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import prog21assignment.domain.Album;
import prog21assignment.domain.Song;
import prog21assignment.exceptions.EntityNotFoundException;
import prog21assignment.exceptions.InvalidDtoException;
import prog21assignment.exceptions.NoContentException;
import prog21assignment.presentation.dto.AlbumDTO;
import prog21assignment.service.QueenEntityService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final QueenEntityService<Album> albumService;
    private final QueenEntityService<Song> songService;

    @Autowired
    public AlbumRestController(QueenEntityService<Album> service, QueenEntityService<Song> songService) {
        this.albumService = service;
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> readAll() {
        return ResponseEntity.ok(albumService
                .read()
                .stream()
                .map(AlbumDTO::fromAlbum)
                .toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDTO> findById(@PathVariable int id) {
        return ResponseEntity.ok(AlbumDTO.fromAlbum(albumService.findById(id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        albumService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateById(@PathVariable int id, @RequestBody @Valid AlbumDTO dto, BindingResult br) {
        if (br.hasErrors()) throw new InvalidDtoException(br);

        Album a = albumService.findById(id);
        a.setTitle(dto.getTitle());
        a.setRelease(dto.getParsedRelease());
        a.setDescription(dto.getDescription());

        List<Song> songs = new ArrayList<>();

        dto.getSongIds().forEach(songId -> {
            Song s = songService.findById(songId);
            songs.add(s);
            s.setAlbum(a);
            a.addSong(s);
        });

        albumService.update(a);
        songs.forEach(songService::update);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumDTO> create(@RequestBody @Valid AlbumDTO dto, BindingResult br) {
        if (br.hasErrors()) throw new InvalidDtoException(br);

        Album a = new Album(dto.getTitle(), dto.getParsedRelease(), dto.getDescription());

        List<Song> songs = new ArrayList<>();

        dto.getSongIds().forEach(songId -> {
            Song s = songService.findById(songId);
            songs.add(s);
            s.setAlbum(a);
            a.addSong(s);
        });

        albumService.create(a);
        songs.forEach(songService::update);

        dto.setId(a.getId());
        return ResponseEntity.ok(dto);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<String> handleNoContentException(NoContentException e) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
    }

    @ExceptionHandler(InvalidDtoException.class)
    public ResponseEntity<List<String>> handleInvalidDtoException(InvalidDtoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessages());
    }
}
