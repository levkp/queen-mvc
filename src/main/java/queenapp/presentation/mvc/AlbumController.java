package queenapp.presentation.mvc;

import queenapp.domain.Album;
import queenapp.domain.Song;
import queenapp.presentation.dto.AlbumDto;
import queenapp.presentation.dto.QueenEntityDtoMapper;
import queenapp.presentation.dto.SongDto;
import queenapp.service.AlbumService;
import queenapp.service.QueenEntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class  AlbumController {
    private final AlbumService albumService;
    private final QueenEntityService<Song> songService;
    private final QueenEntityDtoMapper<AlbumDto, Album> albumMapper;
    private final QueenEntityDtoMapper<SongDto, Song> songMapper;

    @Autowired
    public AlbumController(AlbumService albumService,
                           QueenEntityService<Song> songService,
                           QueenEntityDtoMapper<AlbumDto, Album> albumMapper,
                           QueenEntityDtoMapper<SongDto, Song> songMapper) {
        this.albumService = albumService;
        this.songService = songService;
        this.albumMapper = albumMapper;
        this.songMapper = songMapper;
    }

    @GetMapping
    public String showAll(@AuthenticationPrincipal UserDetails user, Model m) {
        List<AlbumDto> albums = albumService.findAll()
                .stream()
                .map(albumMapper::toDto)
                .toList();
        // Todo: no need to retrieve all songs for this view
        List<SongDto> songs = songService.findAll()
                .stream()
                .filter(s -> s.getAlbum() == null)
                .map(songMapper::toDto)
                .toList();

        m.addAttribute("albums", albums);
        m.addAttribute("songs", songs);
        m.addAttribute("ownerName", user.getUsername());
        return "albums";
    }
}
