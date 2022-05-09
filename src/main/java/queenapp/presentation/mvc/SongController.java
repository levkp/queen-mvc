package queenapp.presentation.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import queenapp.domain.Album;
import queenapp.domain.Genre;
import queenapp.domain.Song;
import queenapp.presentation.dto.QueenEntityDtoMapper;
import queenapp.presentation.dto.SongDto;
import queenapp.service.QueenEntityService;
import queenapp.service.SongService;

import javax.validation.Valid;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final QueenEntityService<Album> albumService;
    private final QueenEntityDtoMapper<SongDto, Song> songMapper;

    @Autowired
    public SongController(
            SongService songService,
            QueenEntityService<Album> albumService,
            QueenEntityDtoMapper<SongDto, Song> songMapper) {
        this.songService = songService;
        this.albumService = albumService;
        this.songMapper = songMapper;
    }

    @GetMapping
    public String showAll(Model m) {
        List<SongDto> songs = songService.findAll()
                .stream()
                .map(songMapper::toDto)
                .toList();

        m.addAttribute("songs", songs);
        return "songs";
    }

    @GetMapping("/add")
    public String create(Model m) {
        m.addAttribute("genres", Genre.values());
        m.addAttribute("albums", albumService.findAll());
        m.addAttribute("song", new SongDto());

        return "add_song";
    }

    @PostMapping("/add")
    public String handleNewSong(@Valid @ModelAttribute("song") SongDto dto, BindingResult br, Model m) {
        if (br.hasErrors()) {
            return "redirect:/add";

//            m.addAttribute("genres", Genre.values());
//            m.addAttribute("albums", albumService.findAll());
//            return "add_song";
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
            // Todo
//            albumService.create(a, null);
        }

        // Todo
//        songService.create(s, null);

        return "redirect:/songs";
    }

    @GetMapping("/{id}")
    public String readSong(Model m, @PathVariable int id) {
        Song a = songService.findById(id);

        m.addAttribute("song", a);
        return "song_detailed";
    }

    private YearMonth parseRecordedDate(String recorded) {
        return YearMonth.parse(recorded);
    }

    private Set<Genre> parseGenreOrdinals(List<Integer> ordinals) {
        return ordinals.stream()
                .map(o -> Genre.values()[o])
                .collect(Collectors.toSet());
    }
}
