package my.flipside.application.controllers;

import my.flipside.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

//import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/main")
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadMainPage(HttpServletRequest request, ModelAndView view, Principal principal) {
        request.getSession(false).setAttribute("user", userService.getUserByUsername(principal.getName()));
        view.setViewName("main");
        return view;
    }
}
