package prog21assignment.presentation.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prog21assignment.domain.Album;
import prog21assignment.presentation.dto.AlbumDTO;
import prog21assignment.service.AlbumService;

@Component
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService service;
    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @GetMapping
    public String showAlbums(Model m) {
        m.addAttribute("albums", service.getAllAlbums());
        log.debug("Returning albums view");
        return "albums";
    }

    @GetMapping("/add")
    public String addAlbum() {
        log.debug("Returning addalbum view");
        return "addalbum";
    }

    @PostMapping("/add")
    public String processNewAlbum(AlbumDTO dto) {
        service.addAlbum(dto.title, dto.getParsedRelease());
        return "redirect:/albums";
    }
}
