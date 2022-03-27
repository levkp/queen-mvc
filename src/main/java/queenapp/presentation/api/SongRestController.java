package queenapp.presentation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import queenapp.presentation.dto.SongDto;
import queenapp.service.QueenEntityDtoService;

@RestController
@RequestMapping("/api/songs")
public class SongRestController {
    private final QueenEntityDtoService<SongDto> service;

    @Autowired
    public SongRestController(QueenEntityDtoService<SongDto> service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<SongDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }
}