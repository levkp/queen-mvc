package queenapp.presentation.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import queenapp.presentation.mvc.viewmodels.LoginViewModel;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public ModelAndView login(ModelAndView mav) {
        mav.addObject("user", new LoginViewModel());
        mav.setViewName("login");
        return mav;
    }
}
