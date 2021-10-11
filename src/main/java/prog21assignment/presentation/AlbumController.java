package prog21assignment.presentation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import prog21assignment.service.AlbumService;

@Component
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService service;

    @Autowired
    public AlbumController(AlbumService service) {
        this.service = service;
    }

    @GetMapping
    public String showAllAlbums(Model m) {
        m.addAttribute("albums", service.getAllAlbums());
        return "albums";
    }


}
