package queenapp.presentation.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import queenapp.domain.Album;
import queenapp.domain.Song;
import queenapp.service.QueenEntityService;

@Controller
@RequestMapping("/albums")
public class  AlbumController {
    private final QueenEntityService<Album> albumService;
    private final QueenEntityService<Song> songService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public AlbumController(QueenEntityService<Album> albumService, QueenEntityService<Song> songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping
    public String showAll(Model m) {
        m.addAttribute("albums", albumService.read());
        m.addAttribute("songs", songService.read());
        return "albums";
    }



//    @GetMapping("/add")
//    public String addAlbum(Model m) {
////        List<Song> songsWithNoAlbum = songService.read()
////                .stream()
////                .filter(a -> a.getAlbum() == null)
////                .collect(Collectors.toList());
////        m.addAttribute("songs", songsWithNoAlbum);
//
//        m.addAttribute("songs", songService.read());
//        m.addAttribute("album", new AlbumDto());
//
//        return "add_album";
//    }
//
//    @PostMapping("/add")
//    public String handleNewAlbum(@Valid @ModelAttribute("album") AlbumDto dto, BindingResult br, Model m) {
//        if (br.hasErrors()) {
//            log.error("Error while adding album");
//            br.getAllErrors().forEach(e -> log.error(e.toString()));
//            m.addAttribute("songs", songService.read());
//            return "add_album";
//        }
//
//        Album a = albumService.create(new Album(dto.getTitle(), dto.getParsedRelease(), dto.getDescription()));
//        List<Integer> songIds = dto.getSongIds();
//
//            for (Integer id : songIds) {
//                Song s = songService.findById(id);
//                a.addSong(s);
//                s.setAlbum(a);
//            }
//
//        return "redirect:/albums";
//    }
//
//    @GetMapping("/{id}")
//    public String readAlbum(Model m, @PathVariable int id) {
//        log.debug("Searching for album with id " + id);
//
//        m.addAttribute("album", albumService.findById(id));
//
//        log.debug("Returning album_detailed view");
//        return "album_detailed";
//    }
}
