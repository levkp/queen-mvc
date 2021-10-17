package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prog21assignment.domain.Genre;
import prog21assignment.presentation.dto.SongDTO;
import prog21assignment.service.AlbumService;
import prog21assignment.service.SongService;

@Component
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;
    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String showAllSongs(Model m) {
        m.addAttribute("songs", songService.getAllSongs());
        log.debug("Returning songs view");
        return "songs_table";
    }

    @GetMapping("/add")
    public String addSong(Model m) {
        m.addAttribute("genres", Genre.values());
        m.addAttribute("albums", albumService.getAllAlbums());
        log.debug("Returning add_song view");
        return "add_song";
    }

    @PostMapping("/add")
    public String handleNewSong(SongDTO dto) {
        songService.addSong(dto.title, dto.length, dto.genres, dto.getParsedRecordedDate(), dto.album);
        return "redirect:/songs";
    }
}
