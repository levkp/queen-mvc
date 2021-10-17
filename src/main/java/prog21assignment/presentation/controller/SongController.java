package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prog21assignment.service.SongService;

@Component
@RequestMapping("/songs")
public class SongController {
    private final SongService service;
    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    public SongController(SongService service) {
        this.service = service;
    }

    @GetMapping
    public String showAllSongs(Model m) {
        m.addAttribute("songs", service.getAllSongs());
        log.debug("Returning songs view");
        return "songs_table";
    }
}
