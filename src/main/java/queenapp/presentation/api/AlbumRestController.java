package queenapp.presentation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import queenapp.domain.Album;
import queenapp.exception.InvalidDtoException;
import queenapp.presentation.dto.AlbumDto;
import queenapp.service.QueenEntityDtoService;
import queenapp.service.QueenEntityService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {
    private final QueenEntityDtoService<AlbumDto> albumDtoService;
    private final QueenEntityService<Album> albumService;

    @Autowired
    public AlbumRestController(QueenEntityDtoService<AlbumDto> albumDtoService,
                               QueenEntityService<Album> albumService) {
        this.albumDtoService = albumDtoService;
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDto>> readAll() {
        List<AlbumDto> albums = albumDtoService.read();
        return albums.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(albums);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(albumDtoService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(HttpServletRequest request,
                                           @PathVariable int id,
                                           @AuthenticationPrincipal UserDetails user) {
        AlbumDto dto = albumDtoService.findById(id);
        if (dto.getOwnerName().equals(user.getUsername()) || request.isUserInRole("ROLE_ADMIN")) {
            albumDtoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            albumService.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateById(@PathVariable int id, @RequestBody @Valid AlbumDto dto, BindingResult br) {
        if (br.hasErrors()) throw new InvalidDtoException(br);
        albumDtoService.updateById(id, dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumDto> create(@RequestBody @Valid AlbumDto dto, BindingResult br,
                                           @AuthenticationPrincipal UserDetails user) {
        if (br.hasErrors()) throw new InvalidDtoException(br);
        albumDtoService.create(dto, user.getUsername());
        return ResponseEntity.ok(dto);
    }
}
