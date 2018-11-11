package my.flipside.application.controllers;

import my.flipside.application.model.FlipUser;
import my.flipside.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView signup(ModelAndView view, @RequestParam String cancel) {
        if (cancel != null) {
            view.setViewName("login");
            return view;
        }
        view.setViewName("signup");
        return view;
    }

    @PostMapping
    public ModelAndView signup(ModelAndView view, @ModelAttribute FlipUser user) {
        Integer id = userService.createUser(user);
        if (id != null) {
            view.setViewName("login");
            view.addObject("success", "You signed up (#" + id + ")");
            return view;
        } else {
            view.addObject("error", "Registration failed! Try one more time");
            view.setViewName("signup");
            return view;
        }
    }
}
