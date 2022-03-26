package queenapp.presentation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import queenapp.exception.InvalidDtoException;
import queenapp.presentation.dto.AlbumDto;
import queenapp.service.QueenEntityDtoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {
    private final QueenEntityDtoService<AlbumDto> service;

    @Autowired
    public AlbumRestController(QueenEntityDtoService<AlbumDto> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDto>> readAll() {
        List<AlbumDto> albums = service.read();
        return albums.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(albums);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable int id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateById(@PathVariable int id, @RequestBody @Valid AlbumDto dto, BindingResult br) {
        if (br.hasErrors()) throw new InvalidDtoException(br);
        service.updateById(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumDto> create(@RequestBody @Valid AlbumDto dto, BindingResult br,
                                           @AuthenticationPrincipal UserDetails user) {
        if (br.hasErrors()) throw new InvalidDtoException(br);
        service.create(dto, user.getUsername());
        return ResponseEntity.ok(dto);
    }
}
