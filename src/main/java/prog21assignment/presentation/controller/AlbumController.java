package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import prog21assignment.domain.Album;
import prog21assignment.domain.Song;
import prog21assignment.presentation.dto.AlbumDTO;
import prog21assignment.service.QueenEntityService;
import prog21assignment.service.SongService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller // Todo: what does the controller annotation do, really?
@Component
@RequestMapping("/albums")
public class AlbumController {
    private final QueenEntityService<Album> albumService;
    private final SongService songService;
    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    public AlbumController(QueenEntityService<Album> albumService, SongService songService) {
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping
    public String showAlbums(Model m) {
        m.addAttribute("albums", albumService.read());
        return "albums_table";
    }

    @GetMapping("/add")
    public String addAlbum(Model m) {
//        List<Song> songsWithNoAlbum = songService.read()
//                .stream()
//                .filter(a -> a.getAlbum() == null)
//                .collect(Collectors.toList());

        m.addAttribute("album", new AlbumDTO());
        m.addAttribute("songs", songService.read());
//        m.addAttribute("songs", songsWithNoAlbum);

        return "add_album";
    }


    @PostMapping("/add")
    public String addAlbum(@Valid @ModelAttribute("album") AlbumDTO dto, BindingResult br) {
        if (br.hasErrors()) {
            log.error("Error while adding album");
            br.getAllErrors().forEach(error -> log.error(br.toString()));
            return "add_album";
        } else {
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
    }

    @GetMapping("/{id}")
    public String readAlbum(Model m, @PathVariable int id) {

        log.debug("Searching for album with id " + id);

        Optional<Album> o = albumService.read().stream()
                .filter(a -> a.getId() == id)
                .findFirst();

        if (o.isEmpty()) {
            log.debug("Album with id " + id + " not found, returning 404");
            return "error_404";
        }

        m.addAttribute("album", o.get());
        log.debug("Returning album_detailed view");
        return "album_detailed";
    }

    @GetMapping("/{id}/delete")
    public String delete(Model m, @PathVariable int id) {
        Album a = albumService.findById(id);




        return "redirect:/albums";
    }

}
