package queenapp.presentation.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import queenapp.domain.Album;
import queenapp.domain.Song;
import queenapp.presentation.dto.AlbumDto;
import queenapp.presentation.dto.QueenEntityDtoMapper;
import queenapp.service.AlbumService;
import queenapp.service.QueenEntityService;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class  AlbumController {

    private final AlbumService service;
    private final QueenEntityService<Song> songService;
    private final QueenEntityDtoMapper<AlbumDto, Album> albumMapper;

    // Todo: replace dto services

    @Autowired
    public AlbumController(AlbumService service,
                           QueenEntityDtoMapper<AlbumDto, Album> albumMapper,
                           QueenEntityService<Song> songService) {
        this.albumMapper = albumMapper;
        this.service = service;
        this.songService = songService;
    }

    @GetMapping
    public String showAll(@AuthenticationPrincipal UserDetails user, Model m) {

        List<AlbumDto> albums = service.findAll()
                .stream()
                .map(albumMapper::toDto)
                .toList();


        m.addAttribute("albums", albums);
//        m.addAttribute("songs", songs);
        m.addAttribute("ownerName", user.getUsername());
        return "albums";
    }
}
