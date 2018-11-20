package my.flipside.application.controllers;

import my.flipside.application.model.FlipStat;
import my.flipside.application.model.FlipUser;
import my.flipside.application.service.StatService;
import my.flipside.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/profile")
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    StatService statService;

    @GetMapping
    public ModelAndView loadProfilePage(ModelAndView view) {
        view.setViewName("profile");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping
    public ModelAndView changeCreds(ModelAndView view, HttpServletRequest request) {
        FlipStat stat = refreshStat(request);
        if (stat != null) {
            HttpSession session = request.getSession(false);
            Integer id = ((FlipUser) session.getAttribute("user")).getUserId();
            session.removeAttribute("user");
            session.setAttribute("user", userService.getUser(id));
            view.addObject("success", "You've successfully refreshed your profile");
        } else {
            view.addObject("error", "There was an error in refreshing your profile. Try one more time later");
        }
        view.setViewName("profile");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public FlipStat refreshStat(HttpServletRequest request) {
        Integer statId = ((FlipUser) request.getSession(false).getAttribute("user")).getStat().getStatId();
        if (statId != null) {
            FlipStat newStat = statService.getStat(statId);
            newStat.setScore(0);
            newStat.setAccount(1000);
            return statService.updateStat(newStat);
        } else {
            return null;
        }
    }

}
