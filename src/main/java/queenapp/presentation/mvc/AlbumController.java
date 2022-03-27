package queenapp.presentation.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import queenapp.domain.Album;
import queenapp.domain.Song;
import queenapp.service.QueenEntityService;

@Controller
@RequestMapping("/albums")
public class  AlbumController {
    private final QueenEntityService<Album> albumService;
    private final QueenEntityService<Song> songService;

    @Autowired
    public AlbumController(QueenEntityService<Album> albumService, QueenEntityService<Song> songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping
    public String showAll(Model m) {
        m.addAttribute("albums", albumService.read());
        m.addAttribute("songs", songService.read());
        return "albums";
    }
}
