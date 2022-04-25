package queenapp.presentation.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import queenapp.presentation.dto.AlbumDto;
import queenapp.presentation.dto.SongDto;
import queenapp.service.QueenEntityDtoService;

import java.util.List;

@Controller
@RequestMapping("/albums")
public class  AlbumController {

    // Todo: replace dto services
    private final QueenEntityDtoService<AlbumDto> albumDtoService;
    private final QueenEntityDtoService<SongDto> songDtoService;

    @Autowired
    public AlbumController(QueenEntityDtoService<AlbumDto> albumDtoService,
                           QueenEntityDtoService<SongDto> songDtoService) {
        this.albumDtoService = albumDtoService;
        this.songDtoService = songDtoService;
    }

    @GetMapping
    public String showAll(@AuthenticationPrincipal UserDetails user, Model m) {

        List<SongDto> songs = songDtoService.read()
                .stream()
                .filter(dto -> dto.getAlbumId() == -1)
                .toList();


        m.addAttribute("albums", albumDtoService.read());
        m.addAttribute("songs", songs);
        m.addAttribute("ownerName", user.getUsername());
        return "albums";
    }
}
