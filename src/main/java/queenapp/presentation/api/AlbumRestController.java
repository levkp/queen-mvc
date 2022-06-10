package queenapp.presentation.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import queenapp.service.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {
    private final AlbumService service;
    private final QueenEntityDtoMapper<AlbumDto, Album> mapper;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public AlbumRestController(AlbumService service, QueenEntityDtoMapper<AlbumDto, Album> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<AlbumDto>> findAll() {
        List<AlbumDto> albums = service.findAll()
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(HttpServletRequest request) {
        logger.debug("Received delete all request");
        logger.debug(request.getUserPrincipal().toString());
        logger.debug(String.valueOf(request.isUserInRole("ROLE_ADMIN")));


        if (request.isUserInRole("ROLE_ADMIN")) {
            service.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("{id}")
    public ResponseEntity<AlbumDto> updateById(@PathVariable int id, @RequestBody @Valid AlbumDto dto, BindingResult br,
                                               @AuthenticationPrincipal UserDetails user) {
        if (br.hasErrors()) throw new InvalidDtoException(br);
        dto.setId(id);
        dto.setOwnerName(user.getUsername());
        service.updateFromDto(dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AlbumDto> create(@RequestBody @Valid AlbumDto dto, BindingResult br,
                                           @AuthenticationPrincipal UserDetails user) {
        if (br.hasErrors()) throw new InvalidDtoException(br);
        dto.setOwnerName(user.getUsername());
        service.createFromDto(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
