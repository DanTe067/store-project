package my.flipside.application.controllers;

import my.flipside.application.model.FlipUser;
import my.flipside.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@RequestMapping("/fraction")
@EnableTransactionManagement
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
public class FractionController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loadFractionPage(ModelAndView view, @RequestParam String side) {
        if (!side.equals("jedi") && !side.equals("sith")) {
            view.setViewName("main");
            return view;
        }
        view.addObject("fractionalBackground", side.equals("jedi") ? "https://images2.alphacoders.com/664/664399.jpg"
                : "https://cdn.shazoo.ru/199968_9eitLqZIis_y5axj9xdutuexokymrrb.jpg")
                .addObject("result", "The battle is in full swing!")
                .addObject("side", side)
                .setViewName("fraction");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView loadBattleResult(ModelAndView view, HttpSession session, @RequestParam int bet,
                                         @RequestParam String side) {
        view.addObject("result",
                result(session, side, (FlipUser) session.getAttribute("user"), bet))
                .addObject("fractionalBackground", "https://curiousmedia.com/assets/content/starwars-3.jpg")
                .setViewName("fraction");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String result(HttpSession session, String side, FlipUser user, int bet) {
        if (bet > user.getStat().getAccount()) {
            return "You bet more than you have!";
        }

        if (new Random().nextBoolean()) {
            user.getStat().setAccount(user.getStat().getAccount() + bet);
            user.getStat().setScore(user.getStat().getScore() + (side.equals("jedi") ? 2 : -2));
            userService.updateUser(user);
            session.setAttribute("user", user);
            return "You won!";
        } else {
            user.getStat().setAccount(user.getStat().getAccount() - bet);
            userService.updateUser(user);
            session.setAttribute("user", user);
            return "You lost!";
        }
    }

}
