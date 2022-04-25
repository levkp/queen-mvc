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
import queenapp.presentation.dto.QueenEntityDtoMapper;
import queenapp.service.QueenEntityDtoService;
import queenapp.service.QueenEntityService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {
    private final QueenEntityService<Album> service;
    private final QueenEntityDtoService<AlbumDto> albumDtoService;
    private final QueenEntityDtoMapper<AlbumDto, Album> mapper;

    @Autowired
    public AlbumRestController(QueenEntityDtoService<AlbumDto> albumDtoService,
                               QueenEntityService<Album> service,
                               QueenEntityDtoMapper<AlbumDto, Album> mapper) {
        this.albumDtoService = albumDtoService;
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDto>> findAll() {
        List<AlbumDto> albums = service.read()
                .stream()
                .map(mapper::toDto)
                .toList();
        return albums.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(albums);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlbumDto> findById(@PathVariable int id) {
        return ResponseEntity.ok(mapper.toDto(service.findById(id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(HttpServletRequest request, @PathVariable int id,
                                           @AuthenticationPrincipal UserDetails user) {
        Album a = service.findById(id);
        if (a.getOwner().getUsername().equals(user.getUsername()) || request.isUserInRole("ROLE_ADMIN")) {
            service.delete(a);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            service.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    // Todo: remove DtoService here
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
