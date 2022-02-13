package prog21assignment.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prog21assignment.domain.Album;
import prog21assignment.exceptions.EntityNotFoundException;
import prog21assignment.presentation.dto.AlbumDTO;
import prog21assignment.service.QueenEntityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {
    private final QueenEntityService<Album> service;

    public AlbumRestController(QueenEntityService<Album> service) {
        this.service = service;
    }


    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAll() {
        List<AlbumDTO> dtos = service
                .read()
                .stream()
                .map(AlbumDTO::fromAlbum)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(AlbumDTO.fromAlbum(service.findById(id)));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
