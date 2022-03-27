package queenapp.presentation.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import queenapp.presentation.dto.AlbumDto;
import queenapp.presentation.dto.SongDto;
import queenapp.service.QueenEntityDtoService;

@Controller
@RequestMapping("/albums")
public class  AlbumController {
    private final QueenEntityDtoService<AlbumDto> albumService;
    private final QueenEntityDtoService<SongDto> songService;

    @Autowired
    public AlbumController(QueenEntityDtoService<AlbumDto> albumService, QueenEntityDtoService<SongDto> songService) {
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
