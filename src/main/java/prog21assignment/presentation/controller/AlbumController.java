package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import prog21assignment.domain.Album;
import prog21assignment.domain.Song;
import prog21assignment.exceptions.DatabaseException;
import prog21assignment.presentation.dto.AlbumDTO;
import prog21assignment.service.QueenEntityService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/albums")
public class AlbumController {
    private final QueenEntityService<Album> albumService;
    private final QueenEntityService<Song> songService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public AlbumController(QueenEntityService<Album> albumService, QueenEntityService<Song> songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping
    public String showAlbums(Model m) {
        m.addAttribute("albums", albumService.read());
        return "all_albums";
    }

    @GetMapping("/add")
    public String addAlbum(Model m) {
//        List<Song> songsWithNoAlbum = songService.read()
//                .stream()
//                .filter(a -> a.getAlbum() == null)
//                .collect(Collectors.toList());
//        m.addAttribute("songs", songsWithNoAlbum);

        m.addAttribute("songs", songService.read());
        m.addAttribute("album", new AlbumDTO());

        return "add_album";
    }

    @PostMapping("/add")
    public String handleNewAlbum(@Valid @ModelAttribute("album") AlbumDTO dto, BindingResult br, Model m) {
        if (br.hasErrors()) {
            log.error("Error while adding album");
            br.getAllErrors().forEach(e -> log.error(e.toString()));
            m.addAttribute("songs", songService.read());
            return "add_album";
        }

        Album a = albumService.create(new Album(dto.getTitle(), dto.getParsedRelease(), dto.getDescription()));
        List<Integer> songIds = dto.getSongIds();

        if (!songIds.isEmpty()) {
            for (Integer id : songIds) {
                Song s = songService.findById(id);
                a.addSong(s);
                s.setAlbum(a);
            }
        }

        return "redirect:/albums";
    }

    @GetMapping("/{id}")
    public String readAlbum(Model m, @PathVariable int id) {
        log.debug("Searching for album with id " + id);

        m.addAttribute("album", albumService.findById(id));

        log.debug("Returning album_detailed view");
        return "album_detailed";
    }

    @ExceptionHandler(DatabaseException.class)
    public ModelAndView handleDatabaseException(HttpServletRequest request, DatabaseException de) {
        log.error(de.getMessage() + ", id: " + de.getId());

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", de);
        mav.setViewName("error_database");

        return mav;
    }

//    @GetMapping("/{id}/delete")
//    public String delete(Model m, @PathVariable int id) {
//        Album a = albumService.findById(id);
//        albumService.delete(a);
//
//        return "redirect:/albums";
//    }

}
