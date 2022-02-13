package prog21assignment.presentation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prog21assignment.domain.Album;
import prog21assignment.service.QueenEntityService;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {
    private final QueenEntityService<Album> service;

    public AlbumRestController(QueenEntityService<Album> service) {
        this.service = service;
    }

//    @GetMapping("{id}")
//    public ResponseEntity<AlbumDTO> getById(@PathVariable int id) {
//        Album album = service.findById(id);
//
//        if (album != null) {
//            return ResponseEntity.ok(new AlbumDTO())
//        }
//
//
//    }
}
