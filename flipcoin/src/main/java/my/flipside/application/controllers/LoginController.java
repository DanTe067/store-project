package my.flipside.application.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/", "/login"})
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadLogInPage(ModelAndView view) {
        view.setViewName("login");
        return view;
    }
}
