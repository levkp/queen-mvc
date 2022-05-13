package queenapp.presentation.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import queenapp.domain.Album;
import queenapp.domain.Genre;
import queenapp.presentation.dto.SongDto;
import queenapp.service.QueenEntityService;
import queenapp.service.SongService;

import javax.validation.Valid;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final QueenEntityService<Album> albumService;

    @Autowired
    public SongController(SongService songService, QueenEntityService<Album> albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String showAll(Model m) {
        m.addAttribute("songs", songService.findAll());
        return "songs";
    }

    @GetMapping("/{id}")
    public String showSong(Model m, @PathVariable int id) {
        m.addAttribute("song", songService.findById(id));
        return "song_detailed";
    }

    @GetMapping("/add")
    public String showCreateNew(Model m) {
        m.addAttribute("genres", Genre.values());
        m.addAttribute("albums", albumService.findAll());
        m.addAttribute("song", new SongDto());
        return "add_song";
    }

    @PostMapping("/add")
    public String create(@ModelAttribute("song") @Valid SongDto dto, BindingResult br, Model m,
                         @AuthenticationPrincipal UserDetails user) {
        if (br.hasErrors()) {
            m.addAttribute("genres", Genre.values());
            m.addAttribute("albums", albumService.findAll());
            m.addAttribute("song", new SongDto());
            return "add_song";
        }

        songService.createFromDto(dto, user.getUsername());
        return "redirect:/songs";
    }
}
