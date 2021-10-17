package prog21assignment.presentation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/navbar")
public class QueenController {

    private static final Logger log = LoggerFactory.getLogger(QueenController.class);

    @GetMapping
    public String showIndex(Model m) {
        return "navbar";
    }

}
