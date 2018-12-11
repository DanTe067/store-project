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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadProfilePage(ModelAndView view, HttpSession session) {
        session.setAttribute("user", userService.getUser(
                ((FlipUser) session.getAttribute("user")).getUserId()));
        view.setViewName("profile");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView loadRefreshing(ModelAndView view, HttpSession session) {
        FlipStat stat = refreshStat(session);
        if (stat != null) {
            Integer id = ((FlipUser) session.getAttribute("user")).getUserId();
            session.removeAttribute("user");
            session.setAttribute("user", userService.getUser(id));
            view.addObject("success", "You've successfully refreshed your profile");
        } else {
            view.addObject("error",
                    "There was an error in refreshing your profile. Try again later");
        }
        view.setViewName("profile");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public FlipStat refreshStat(HttpSession session) {
        Integer statId = ((FlipUser) session.getAttribute("user")).getStat().getStatId();
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
