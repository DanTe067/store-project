package my.flipside.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/", "/login"})
public class LoginController {

    @GetMapping
    public ModelAndView loadLogInPage(ModelAndView view, @RequestParam(required = false) String signup) {
        if (signup != null) {
            view.setViewName("signup");
            return view;
        }
        view.setViewName("login");
        return view;
    }
}
