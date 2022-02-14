package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prog21assignment.domain.Album;
import prog21assignment.exceptions.EntityNotFoundException;
import prog21assignment.exceptions.NoContentException;
import prog21assignment.presentation.dto.AlbumDTO;
import prog21assignment.service.QueenEntityService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final QueenEntityService<Album> service;

    @Autowired
    public AlbumRestController(QueenEntityService<Album> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAll() {
        return ResponseEntity.ok(service
                .read()
                .stream()
                .map(AlbumDTO::fromAlbum)
                .toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(AlbumDTO.fromAlbum(service.findById(id)));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Void> handleNoContentException(HttpServletRequest request, NoContentException e) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
