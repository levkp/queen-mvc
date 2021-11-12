package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import prog21assignment.domain.Album;
import prog21assignment.domain.Genre;
import prog21assignment.domain.Song;
import prog21assignment.presentation.dto.SongDTO;
import prog21assignment.service.AlbumService;
import prog21assignment.service.SongService;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
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
        m.addAttribute("songs", songService.read());
        log.debug("Returning songs view");
        return "songs_table";
    }

    @GetMapping("/add")
    public String addSong(Model m) {
        m.addAttribute("genres", Genre.values());
        m.addAttribute("albums", albumService.read());
        log.debug("Returning add_song view");
        return "add_song";
    }

    @PostMapping("/add")
    public String handleNewSong(SongDTO dto) {
        Album a = albumService.findById(dto.getAlbumId());
        log.debug("New song's album: " + a.getTitle());
        Song s = songService.create(
                dto.getTitle(),
                dto.getLength(),
                parseGenreOrdinals(dto.getGenreOrdinals()),
                parseRecordedDate(dto.getRecorded()),
                a
        );
        a.addSong(s);
        return "redirect:/songs";
    }

    @GetMapping("/{id}")
    public String readSong(Model m, @PathVariable int id) {

        log.debug("Searching for song with id " + id);

        Song a = songService.findById(id);

        if (a == null) {
            return "404";
        }

        m.addAttribute("song", a);
        log.debug("Returning song_detailed view");
        return "song_detailed";
    }

    private YearMonth parseRecordedDate(String recorded) {
        log.debug(String.format("Parsing %s to YearMonth...", recorded));
        return YearMonth.parse(recorded);
    }

    private List<Genre> parseGenreOrdinals(List<Integer> ordinals) {
        log.debug("Parsing genre ordinals...");
        return ordinals.stream()
                .map(o -> Genre.values()[o])
                .collect(Collectors.toList());
    }
}
