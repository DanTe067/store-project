package my.flipside.application.controllers;

import my.flipside.application.model.FlipUser;
import my.flipside.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("/fraction")
@EnableTransactionManagement
public class FractionController {

    @Autowired
    UserService userService;

    @GetMapping
    public ModelAndView loadFractionPage(ModelAndView view, @RequestParam String side) {
        if (!side.equals("jedy") && !side.equals("sith")) {
            view.setViewName("main");
            return view;
        }
        view.addObject("background", side.equals("jedy") ? "https://images2.alphacoders.com/664/664399.jpg"
                : "https://cdn.shazoo.ru/199968_9eitLqZIis_y5axj9xdutuexokymrrb.jpg");
        view.addObject("result", "The battle is in full swing!");
        view.addObject("side", side);
        view.setViewName("fraction");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @PostMapping
    public ModelAndView loadBattleResult(ModelAndView view, HttpServletRequest request, @RequestParam int bet,
                                         @ModelAttribute String side) {
        view.addObject("result",
                result(side, (FlipUser) request.getSession(false).getAttribute("user"), bet));
        view.setViewName("fraction");
        return view;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String result(String side, FlipUser user, int bet) {
        if (bet > user.getStat().getAccount()) {
            return "You bet more than you have!";
        }

        if (new Random().nextBoolean()) {
            if (side.equals("jedy")) {
                user.getStat().setAccount(user.getStat().getAccount() + bet);
                user.getStat().setScore(user.getStat().getScore() + 2);
                return "You won! Glory to the Rebellion";
            } else {
                user.getStat().setAccount(user.getStat().getAccount() + bet);
                user.getStat().setScore(user.getStat().getScore() - 2);
                return "You won! Glory to the Empire";
            }
        } else {
            user.getStat().setAccount(user.getStat().getAccount() - bet);
            return "You lost!";
        }
    }

}
