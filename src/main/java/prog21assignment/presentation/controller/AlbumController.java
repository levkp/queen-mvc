package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prog21assignment.domain.Album;
import prog21assignment.presentation.dto.AlbumDTO;
import prog21assignment.service.AlbumService;
import prog21assignment.service.SongService;

import java.util.Optional;

@Controller // Todo: what does the controller annotation do, really?
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
        m.addAttribute("albums", albumService.read());
        log.debug("Returning albums view");
        return "albums_table";
    }

    @GetMapping("/add")
    public String addAlbum(Model m) {
        m.addAttribute("songs", songService.read());
        log.debug("Returning add_album view");
        return "add_album";
    }

    @PostMapping("/add")
    public String handleNewAlbum(AlbumDTO dto) {

        Album a = albumService.create(dto.getTitle(), dto.getParsedRelease());
        dto.getSongIds().forEach(id -> a.addSong(songService.findById(id)));



        return "redirect:/albums";
    }

    @GetMapping("/{id}")
    public String readAlbum(Model m, @PathVariable int id) {

        log.debug("Searching for album with id " + id);

        Optional<Album> o = albumService.read().stream()
                .filter(a -> a.getId() == id)
                .findFirst();

        if (o.isEmpty()) {
            log.debug("Album with id " + id + " not found, returning 404");
            return "404";
        }

        m.addAttribute("album", o.get());
        log.debug("Returning album_detailed view");
        return "album_detailed";
    }
}
