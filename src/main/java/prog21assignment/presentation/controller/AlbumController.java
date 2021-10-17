package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prog21assignment.presentation.dto.AlbumDTO;
import prog21assignment.service.AlbumService;
import prog21assignment.service.SongService;

@Component
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final SongService songService;
    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    public AlbumController(AlbumService albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping
    public String showAlbums(Model m) {
        m.addAttribute("albums", albumService.getAllAlbums());
        log.debug("Returning albums view");
        return "albums_table";
    }

    @GetMapping("/add")
    public String addAlbum(Model m) {
        m.addAttribute("songs", songService.getAllSongs());
        log.debug("Returning addalbum view");
        return "addalbum";
    }

    @PostMapping("/add")
    public String processNewAlbum(AlbumDTO dto) {
        albumService.addAlbum(dto.title, dto.getParsedRelease());
        return "redirect:/albums";
    }
}
