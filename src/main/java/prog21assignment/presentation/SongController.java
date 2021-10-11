package prog21assignment.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import prog21assignment.service.SongService;

public class SongController {
    private final SongService service;

    @Autowired
    public SongController(SongService service) {
        this.service = service;
    }

    @GetMapping
    public String showAllSongs(Model m) {
        m.addAttribute("songs", service.getAllSongs());
        return "songs";
    }
}
