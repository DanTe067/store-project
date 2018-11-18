package my.flipside.application.controllers;

import my.flipside.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView loadMainPage(HttpServletRequest request, ModelAndView view, Principal principal) {
        request.getSession().setAttribute("user", userService.getUserByUsername(principal.getName()));
        view.setViewName("main");
        return view;
    }
}
