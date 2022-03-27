package queenapp.presentation.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import queenapp.domain.Album;
import queenapp.domain.Genre;
import queenapp.domain.Song;
import queenapp.presentation.dto.SongDto;
import queenapp.service.QueenEntityService;

import javax.validation.Valid;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final QueenEntityService<Song> songService;
    private final QueenEntityService<Album> albumService;
    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    public SongController(QueenEntityService<Song> songService, QueenEntityService<Album> albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String showAllSongs(Model m) {
        m.addAttribute("songs", songService.read());
        log.debug("Returning songs view");
        return "songs";
    }

    @GetMapping("/add")
    public String addSong(Model m) {
        m.addAttribute("genres", Genre.values());
        m.addAttribute("albums", albumService.read());
        m.addAttribute("song", new SongDto());

        return "add_song";
    }

    @PostMapping("/add")
    public String handleNewSong(@Valid @ModelAttribute("song") SongDto dto, BindingResult br, Model m) {
        if (br.hasErrors()) {
            log.error("Error while adding song");
            br.getAllErrors().forEach(e -> log.error(e.toString()));
            m.addAttribute("genres", Genre.values());
            m.addAttribute("albums", albumService.read());
            return "add_song";
        }

        Album a = dto.albumId != -1 ? albumService.findById(dto.getAlbumId()) : null;

        Song s = new Song(
                dto.getTitle(),
                dto.getLength(),
                parseGenreOrdinals(dto.getGenreOrdinals()),
                parseRecordedDate(dto.getRecorded()),
                a);

        if (a != null) {
            a.addSong(s);
            albumService.create(a);
        }

        songService.create(s);

        return "redirect:/songs";
    }

    @GetMapping("/{id}")
    public String readSong(Model m, @PathVariable int id) {

        log.debug("Searching for song with id " + id);

        Song a = songService.findById(id);

        if (a == null) {
            return "error_404";
        }

        m.addAttribute("song", a);
        log.debug("Returning song_detailed view");
        return "song_detailed";
    }

    private YearMonth parseRecordedDate(String recorded) {
        log.debug(String.format("Parsing %s to YearMonth...", recorded));
        return YearMonth.parse(recorded);
    }

    private Set<Genre> parseGenreOrdinals(List<Integer> ordinals) {
        log.debug("Parsing genre ordinals...");
        return ordinals.stream()
                .map(o -> Genre.values()[o])
                .collect(Collectors.toSet());
    }
}
