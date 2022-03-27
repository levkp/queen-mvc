package queenapp.presentation.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public ModelAndView admin(HttpServletRequest request, ModelAndView mav, @AuthenticationPrincipal UserDetails user) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            mav.setViewName("admin/admin");
        } else {
            // Todo
            mav.setStatus(HttpStatus.FORBIDDEN);
            mav.setViewName("home/home");
        }

        return mav;
    }


}
