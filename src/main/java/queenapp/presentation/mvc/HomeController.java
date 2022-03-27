package queenapp.presentation.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import queenapp.presentation.mvc.viewmodels.LoginViewModel;
import queenapp.presentation.mvc.viewmodels.RegisterViewModel;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping
    public String home() {
        return "home/home";
    }

    @GetMapping("/register")
    public ModelAndView register(ModelAndView mav) {
        mav.addObject("registerInfo", new RegisterViewModel());
        mav.setViewName("home/register");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login(ModelAndView mav) {
        mav.addObject("loginInfo", new LoginViewModel());
        mav.setViewName("home/login");
        return mav;
    }
}
