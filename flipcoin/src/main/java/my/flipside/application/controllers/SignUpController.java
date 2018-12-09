package my.flipside.application.controllers;

import my.flipside.application.model.FlipUser;
import my.flipside.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class SignUpController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadSignUpPage(ModelAndView view) {
        view.setViewName("/signup");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView signUpByForm(ModelAndView view, @ModelAttribute FlipUser user, BindingResult result) {
        if (result.hasErrors()) {
            view.addObject("error", "Registration failed! Errors found in form you've filled")
                    .setViewName("signup");
        }
        Integer id = userService.createUser(user);
        if (id != null) {
            view.addObject("success", "You've successfully signed up (#" + id + ")")
                    .setViewName("login");
            return view;
        } else {
            view.addObject("error", "Registration failed! Try one more time")
                    .setViewName("signup");
            return view;
        }
    }
}
